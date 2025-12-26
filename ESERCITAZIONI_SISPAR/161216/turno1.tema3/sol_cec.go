package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxSpettatatori = 15 //max spettatori nello stadio
const maxControllori = 3 //max controllori al varco
var maxLocali = 0
var maxOspiti = 0

const tipoLocale int = 0
const tipoOspite int = 1

var tipoSpettatore = [2]string{"Locale", "Ospite"}

var done = make(chan bool)
var terminaBiglietteria = make(chan bool)
var terminaVarco = make(chan bool)

//canali usati dagli spettatori per l'acquisto dei biglietti
var acquistoLocali = make(chan int, 100)
var acquistoOspiti = make(chan int, 100)

//canali usati dagli spettatori per il controllo della sicurezza
var controlloLocali = make(chan int, 100)
var controlloOspiti = make(chan int, 100)

//canali di ack
var ack_locali = make(chan int)   // canale di ack per il robot relativo ai locali
var ack_ospiti = make(chan int)   // canale di ack per il robot relativo agli ospiti

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Spettatore(tipo int) {
	var tt int
	fmt.Printf("[Spettatore %s]: partenza! \n", tipoSpettatore[tipo])
	var ris int

	if tipo == 0 {
		acquistoLocali <- 1
		ris = <-ack_locali
		if ris == -1 {
			fmt.Printf("[Spettatore %s]: termino! \n", tipoSpettatore[tipo])
			done <- true
			return
		}
		tt = (rand.Intn(2) + 1) //tempo di attesa biglietto
		time.Sleep(time.Duration(tt) * time.Second)
		fmt.Printf("[Spettatore %s]: preso biglietto! \n", tipoSpettatore[tipo])

		controlloLocali <- 1
		ris = <-ack_locali
		if ris == -1 {
			fmt.Printf("[Spettatore %s]: termino! \n", tipoSpettatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Spettatore %s]: effettuato controllo! \n", tipoSpettatore[tipo])
	} else {
		acquistoOspiti <- 1
		ris = <-ack_ospiti
		if ris == -1 {
			fmt.Printf("[Spettatore %s]: termino! \n", tipoSpettatore[tipo])
			done <- true
			return
		}
		tt = (rand.Intn(2) + 1)
		time.Sleep(time.Duration(tt) * time.Second) //tempo di attesa controllo
		fmt.Printf("[Spettatore %s]: preso biglietto! \n", tipoSpettatore[tipo])

		controlloOspiti <- 1
		ris = <-ack_ospiti
		if ris == -1 {
			fmt.Printf("[Spettatore %s]: termino! \n", tipoSpettatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Spettatore %s]: effettuato controllo! \n", tipoSpettatore[tipo])
	}
	fmt.Printf("[Spettatore %s]: è entrato!\n", tipoSpettatore[tipo])
	done <- true
}

func biglietteria() {
	var fine bool = false // diventa true quando sono stati dati tutti i biglietti
	var bigliettiDati int = 0

	for {
		select {
			case <-when(fine == false && bigliettiDati < maxSpettatatori, acquistoLocali):
				bigliettiDati++
				ack_locali <- 1
				fmt.Printf("[biglietti]: locale entrato in biglietteria, ci sono %d spettatori con biglietto\n", bigliettiDati)
			case <-when(fine == false && bigliettiDati < maxSpettatatori, acquistoOspiti):
				bigliettiDati++
				ack_ospiti <- 1
				fmt.Printf("[biglietti]: ospite entrato in biglietteria, ci sono %d spettatori con biglietto\n", bigliettiDati)

			//terminazione
			case <-when(fine == true, acquistoOspiti):
				ack_ospiti <- -1
			case <-when(fine == true, acquistoLocali):
				ack_locali <- -1
			
			case <-terminaBiglietteria:
				fmt.Printf("[biglietteria]: termino\n")
				done <- true
				return
		}

		if bigliettiDati == maxSpettatatori {
			fine = true
		}
	}
}

func controlloVarco() {
	var numOspitiEntrati int = 0;
	var numLocaliEntrati int = 0;
	var controlloriLiberi int = maxControllori;
	var fine bool = false // diventa true quando sono stati completati i controlli
	var tt int = 0;

	for {
		select {
		case <-when(fine == false && (numOspitiEntrati < maxOspiti) && (controlloriLiberi > 0) &&
			((numLocaliEntrati <= numOspitiEntrati) || (numLocaliEntrati > numOspitiEntrati && (numLocaliEntrati == maxLocali || len(controlloOspiti) == 0))), controlloOspiti):
			controlloriLiberi--
			tt = (rand.Intn(2) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo di attesa controllo
			controlloriLiberi++
			numOspitiEntrati++
			ack_ospiti <- 1
			fmt.Printf("[controllo varco]: controllato ospite, ci sono %d ospiti e %d locali entrati; tot controllori liberi = %d\n", numOspitiEntrati, numLocaliEntrati, controlloriLiberi)
		case <-when(fine == false && (numLocaliEntrati < maxLocali) && (controlloriLiberi > 0) &&
			((numOspitiEntrati <= numLocaliEntrati) || (numOspitiEntrati > numLocaliEntrati && (numOspitiEntrati == maxOspiti || len(controlloLocali) == 0))), controlloLocali):
			controlloriLiberi--
			tt = (rand.Intn(2) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo di attesa controllo
			controlloriLiberi++
			numLocaliEntrati++
			ack_locali <- 1
			fmt.Printf("[controllo varco]: controllato locale, ci sono %d ospiti e %d locali entrati; tot controllori liberi = %d\n", numOspitiEntrati, numLocaliEntrati, controlloriLiberi)

		//terminazione
		case <-when(fine == true, controlloOspiti):
			ack_ospiti <- -1
		case <-when(fine == true, controlloLocali):
			ack_locali <- -1
		
		case <-terminaVarco:
			fmt.Printf("[varco]: termino\n")
			done <- true
			return
		}

		if numOspitiEntrati + numLocaliEntrati == maxSpettatatori {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	maxLocali = rand.Intn(maxSpettatatori) + 1
	maxOspiti = maxSpettatatori - maxLocali
	fmt.Printf("[main] attivo programma\n")

	go biglietteria()
	go controlloVarco()

	for i := 0; i < maxLocali; i++ {
		go Spettatore(0)
	}

	for i := 0; i < maxOspiti; i++ {
		go Spettatore(1)
	}

	for i := 0; i < maxSpettatatori; i++ { //terminazione spettatori
		<-done
	}

	terminaVarco <- true
	<-done
	terminaBiglietteria <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
