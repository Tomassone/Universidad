package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxVisitatori = 15 //max studenti
const maxPosti = 3 //max posti disponibili
var maxVisitatoriDestri = 0
var maxVisitatoriSinistri = 0

const tipoDebitore int = 0
const tipoCreditore int = 1

var tipoVisitatore = [2]string{"Debitore", "Creditore"}

var done = make(chan bool)
var terminaSala = make(chan bool)
var terminaSportelloDiCassa = make(chan bool)

var entrataDestraSala = make(chan int, 100)
var entrataSinistraSala = make(chan int, 100)
var entrataCassaDebitore = make(chan int, 100)
var entrataCassaCreditore = make(chan int, 100)

var uscitaSalaDebitore = make(chan int, 100)
var uscitaSalaCreditore = make(chan int, 100)
var uscitaCassaDebitore = make(chan int, 100)
var uscitaCassaCreditore = make(chan int, 100)

var ack_visitatore_dx = make(chan int)
var ack_visitatore_sx = make(chan int)
var ack_debitore = make(chan int)  
var ack_creditore = make(chan int)  

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Visitatore(tipo int, dir int) {
	var tt int
	fmt.Printf("[Visitatore %s]: partenza! \n", tipoVisitatore[tipo])
	var ris int

	if (dir == 0) {
		entrataDestraSala <- 1
		ris = <-ack_visitatore_dx
		if ris == -1 {
			fmt.Printf("[Visitatore]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Visitatore]: entrato nella sala da destra! \n")
	} else {
		entrataSinistraSala <- 1
		ris = <-ack_visitatore_sx
		if ris == -1 {
			fmt.Printf("[Visitatore]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Visitatore]: entrato nella sala da sinistra! \n")
	}

	tt = (rand.Intn(10) + 1)
	time.Sleep(time.Duration(tt) * time.Second) //tempo dentro alla sala
	tipo = (rand.Intn(2)) //setto se debitore o creditore

	if (tipo == 0) {
		uscitaSalaDebitore <- 1
		ris = <-ack_debitore
		if ris == -1 {
			fmt.Printf("[Visitatore %s]: termino! \n", tipoVisitatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Visitatore %s]: uscito dalla sala! \n", tipoVisitatore[tipo])

		entrataCassaDebitore <- 1
		ris = <-ack_debitore
		if ris == -1 {
			fmt.Printf("[Visitatore %s]: termino! \n", tipoVisitatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Visitatore %s]: entrata cassa! \n", tipoVisitatore[tipo])
		tt = (rand.Intn(10) + 1)
		time.Sleep(time.Duration(tt) * time.Second) //tempo nella cassa

		uscitaCassaDebitore <- 1
		ris = <-ack_debitore
		if ris == -1 {
			fmt.Printf("[Visitatore %s]: termino! \n", tipoVisitatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Visitatore %s]: uscita cassa! \n", tipoVisitatore[tipo])
	} else {
		uscitaSalaCreditore <- 1
		ris = <-ack_creditore
		if ris == -1 {
			fmt.Printf("[Visitatore %s]: termino! \n", tipoVisitatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Visitatore %s]: uscito dalla sala! \n", tipoVisitatore[tipo])

		entrataCassaCreditore <- 1
		ris = <-ack_creditore
		if ris == -1 {
			fmt.Printf("[Visitatore %s]: termino! \n", tipoVisitatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Visitatore %s]: entrata cassa! \n", tipoVisitatore[tipo])
		tt = (rand.Intn(10) + 1)
		time.Sleep(time.Duration(tt) * time.Second) //tempo nella cassa

		uscitaCassaCreditore <- 1
		ris = <-ack_creditore
		if ris == -1 {
			fmt.Printf("[Visitatore %s]: termino! \n", tipoVisitatore[tipo])
			done <- true
			return
		}
		fmt.Printf("[Visitatore %s]: uscita cassa! \n", tipoVisitatore[tipo])
	}

	done <- true
}

func sala() {
	var controllatiTutti = false
	var fine bool = false
	var visitatoriDentro int = 0
	var visitatoriEntratiDx int = 0
	var visitatoriEntratiSx int = 0

	for {
		select {
			case <-when(fine == false && visitatoriDentro < maxPosti && 
				visitatoriEntratiDx <= visitatoriEntratiSx ||
				(visitatoriEntratiDx > visitatoriEntratiSx && len(entrataSinistraSala) == 0), entrataDestraSala):
				visitatoriDentro++
				visitatoriEntratiDx++
				ack_visitatore_dx <- 1
				fmt.Printf("[sala]: visitatore entrato in sala da dx, ci sono %d visitatori entrati\n", visitatoriDentro)
			case <-when(fine == false && visitatoriDentro < maxPosti && 
				visitatoriEntratiDx > visitatoriEntratiSx ||
				(visitatoriEntratiDx <= visitatoriEntratiSx && len(entrataDestraSala) == 0), entrataSinistraSala):				visitatoriDentro++
				visitatoriEntratiSx++
				ack_visitatore_sx <- 1
				fmt.Printf("[sala]: visitatore entrato in sala da sx, ci sono %d visitatori entrati\n", visitatoriDentro)

			case <-when(fine == false && visitatoriDentro > 0, uscitaSalaDebitore):
				visitatoriDentro--
				ack_debitore <- 1
				fmt.Printf("[sala]: debitore uscito da sala, ci sono %d visitatori entrati\n", visitatoriDentro)
			case <-when(fine == false && visitatoriDentro > 0, uscitaSalaCreditore):
				visitatoriDentro--
				ack_creditore <- 1
				fmt.Printf("[sala]: creditore uscito da sala, ci sono %d visitatori entrati\n", visitatoriDentro)
			
			//terminazione
			case <-when(fine == true, entrataDestraSala):
				ack_visitatore_dx <- -1
			case <-when(fine == true, entrataSinistraSala):
				ack_visitatore_sx <- -1
			case <-when(fine == true, uscitaSalaDebitore):
				ack_debitore <- -1
			case <-when(fine == true, uscitaSalaCreditore):
				ack_creditore <- -1
			
			case <-terminaSala:
				fmt.Printf("[sala]: termino\n")
				done <- true
				return
		}

		if visitatoriEntratiDx + visitatoriEntratiSx == maxVisitatori {
			controllatiTutti = true
		}

		if visitatoriDentro == 0 && controllatiTutti == true {
			fine = true
		}
	}
}

func cassa() {
	var cassaInUso = false;
	var fine bool = false; // diventa true quando sono stati completati i controlli
	var creditoriControllati int = 0;
	var debitoriControllati int = 0;
	var visitatoriControllati int = 0;

	for {
		select {
			case <-when(fine == false && cassaInUso == false, entrataCassaDebitore):
				cassaInUso = true
				ack_debitore <- 1
				fmt.Printf("[cassa]: entrato in cassa debitore, ci sono %d debitori controllati\n", debitoriControllati)
			case <-when(fine == false && cassaInUso == false &&
				(len(entrataCassaDebitore) == 0), entrataCassaCreditore):
				cassaInUso = true
				ack_creditore <- 1
				fmt.Printf("[cassa]: entrato in cassa creditore, ci sono %d creditori entrati\n", creditoriControllati)

			case <-when(fine == false, uscitaCassaDebitore):
				cassaInUso = false
				debitoriControllati++
				ack_debitore <- 1
				fmt.Printf("[cassa]: uscito da cassa debitore, ci sono %d debitori controllati\n", debitoriControllati)
			case <-when(fine == false, uscitaCassaCreditore):
				cassaInUso = false
				creditoriControllati++
				ack_creditore <- 1
				fmt.Printf("[cassa]: uscito da cassa creditore, ci sono %d creditori controllati\n", creditoriControllati)

			//terminazione
			case <-when(fine == true, entrataCassaDebitore):
				ack_debitore <- -1
			case <-when(fine == true, entrataCassaCreditore):
				ack_creditore <- -1
			case <-when(fine == true, uscitaCassaDebitore):
				ack_debitore <- -1
			case <-when(fine == true, uscitaCassaCreditore):
				ack_creditore <- -1
			
			case <-terminaSportelloDiCassa:
				fmt.Printf("[cassa]: termino\n")
				done <- true
				return
		}

		visitatoriControllati = debitoriControllati + creditoriControllati;
		if (visitatoriControllati == maxVisitatori) {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	maxVisitatoriDestri = rand.Intn(maxVisitatori) + 1
	maxVisitatoriSinistri = maxVisitatori - maxVisitatoriDestri
	fmt.Printf("[main] attivo programma\n")

	go sala()
	go cassa()

	for i := 0; i < maxVisitatoriDestri; i++ {
		go Visitatore(0, 0)
	}

	for i := 0; i < maxVisitatoriSinistri; i++ {
		go Visitatore(0, 1)
	}

	for i := 0; i < maxVisitatori; i++ { //terminazione studenti
		<-done
	}

	terminaSala <- true
	<-done
	terminaSportelloDiCassa <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
