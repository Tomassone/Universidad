package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxClienti = 15 
const maxPt = 5
const maxIterazioni = 20

const maxPosti = 10
const maxPesi = 6

var done = make(chan bool)
var terminaSmistamento = make(chan bool)

var entrataPesiCliente = make(chan int, 100)
var entrataCorsiCliente = make(chan int, 100)
var entrataCorsiPt = make(chan int, 100)

var uscitaPesiCliente = make(chan int, 100)
var uscitaCorsiCliente = make(chan int, 100)
var uscitaCorsiPt = make(chan int, 100)

var ack_pesi = make(chan int)  
var ack_corsi = make(chan int)  
var ack_pt = make(chan int) 

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Cliente() {
	var tt int
	fmt.Printf("[Cliente]: partenza! \n")
	var ris int

	for {
		choice := rand.Intn(2)

		if choice == 0 { //entro in area pesi
			entrataPesiCliente <- 1
			ris = <- ack_pesi
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: entrato in pesi! \n")

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella pesi

			uscitaPesiCliente <- 1
			ris = <- ack_pesi
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: uscito dalla pesi! \n")
		} else {
			entrataCorsiCliente <- 1
			ris = <- ack_corsi
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: entrato in corsi! \n")

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella corsi

			uscitaCorsiCliente <- 1
			ris = <- ack_corsi
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: uscito dalla corsi! \n")
		}
    }
}

func Pt() {
    fmt.Printf("[Pt]: partenza!\n")
    outside := false

    for {
		if !outside {
			uscitaCorsiPt <- 1
			if <- ack_pt == -1 {
				fmt.Printf("[Pt]: termino! \n")
				done <- true
				return
			}
			outside = true
		} else {
			entrataCorsiPt <- 1
			if <- ack_pt == -1 {
				fmt.Printf("[Pt]: termino! \n")
				done <- true
				return
			}
			outside = false
		}

		time.Sleep(time.Duration(rand.Intn(5)+1) * time.Second)  
    }
}

func gestioneIngressi() {
	var fine bool = false
	var chiusura bool = false
	var clientiDentroPesi int = 0
	var clientiEntratiPesi int = 0
	var clientiDentroCorsi int = 0
	var clientiEntratiCorsi int = 0
	var ptLiberiInCorsi int = maxPt

	for {
		select {
			case <-terminaSmistamento:
				fmt.Printf("[gestioneIngressi]: termino\n")
				done <- true
				return
			case <-when(chiusura == false && fine == false && clientiDentroPesi + clientiDentroCorsi < maxPosti && clientiDentroPesi < maxPesi &&
				len(entrataCorsiCliente) == 0, entrataPesiCliente):
				clientiDentroPesi++
				clientiEntratiPesi++
				ack_pesi <- 1
				fmt.Printf("[gestioneIngressi]: cliente entrato in pesi, ci sono %d clienti dentro alla pesi\n", clientiDentroPesi)
			case <-when(chiusura == false && fine == false && clientiDentroCorsi + clientiDentroPesi < maxPosti && ptLiberiInCorsi > 0 &&
			len(entrataCorsiPt) == 0, entrataCorsiCliente):
				clientiDentroCorsi++
				clientiEntratiCorsi++
				ptLiberiInCorsi--
				ack_corsi <- 1
				fmt.Printf("[gestioneIngressi]: cliente entrato in corsi, ci sono %d clienti dentro alla corsi\n", clientiDentroCorsi)
			case <-when(chiusura == false && fine == false, entrataCorsiPt):
				ptLiberiInCorsi++
				ack_pt <- 1
				fmt.Printf("[gestioneIngressi]: pt entrato in pesi, ci sono %d pt liberi dentro alla pesi\n", ptLiberiInCorsi)
				
			case <-when(fine == false && clientiDentroPesi > 0, uscitaPesiCliente):
				clientiDentroPesi--
				ack_pesi <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da pesi, ci sono %d clienti dentro\n", clientiDentroPesi)
			case <-when(fine == false && clientiDentroCorsi > 0, uscitaCorsiCliente):
				clientiDentroCorsi--
				ptLiberiInCorsi++
				ack_corsi <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da corsi, ci sono %d clienti dentro\n", clientiDentroCorsi)
			case <-when(fine == false && (ptLiberiInCorsi > 1 || clientiDentroCorsi == 0), uscitaCorsiPt):
				ptLiberiInCorsi--
				ack_pt <- 1
				fmt.Printf("[gestioneIngressi]: pt da pesi, ci sono %d pt liberi dentro alla corsi\n", ptLiberiInCorsi)

			//terminazione
			case <-when(fine == true, entrataPesiCliente):
				ack_pesi <- -1
			case <-when(fine == true, entrataCorsiCliente):
				ack_corsi <- -1
			case <-when(fine == true, uscitaPesiCliente):
				ack_pesi <- -1
			case <-when(fine == true, uscitaCorsiCliente):
				ack_corsi <- -1
			case <-when(fine == true, entrataCorsiPt):
				ack_pt <- -1
			case <-when(fine == true, uscitaCorsiPt):
				ack_pt <- -1
		}

		if clientiEntratiPesi + clientiEntratiCorsi >= 2 * maxIterazioni { //condizione abritraria di terminazione
			chiusura = true
		}

		if (chiusura == true && clientiDentroCorsi + clientiDentroPesi == 0) {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	fmt.Printf("[main] attivo programma\n")

	go gestioneIngressi()

	for i := 0; i < maxPt; i++ {
		go Pt()
	}

	for i := 0; i < maxClienti; i++ {
		go Cliente()
	}

	for i := 0; i < maxClienti; i++ { 
		<- done
	}

	for i := 0; i < maxPt; i++ {
		<- done
	}

	terminaSmistamento <- true
	<- done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
