package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxClienti = 15 //max studenti
const maxSportelli = 3 //max posti disponibili
var maxClientiOver70 = 0
var maxClientiUnder70 = 0

const tipoClientiOver70 int = 0
const tipoClientiUnder70 int = 1

var tipoCliente = [2]string{"Over 70", "Under 70"}

var done = make(chan bool)
var terminaSportelli = make(chan bool)
var terminaCaveau = make(chan bool)

var entrataSportelliClientiOver70 = make(chan int, 100)
var entrataSportelliClientiUnder70 = make(chan int, 100)
var entrataCaveauClientiOver70 = make(chan int, 100)
var entrataCaveauClientiUnder70 = make(chan int, 100)

var uscitaSportelliClientiOver70 = make(chan int, 100)
var uscitaSportelliClientiUnder70 = make(chan int, 100)
var uscitaCaveauClientiOver70 = make(chan int, 100)
var uscitaCaveauClientiUnder70 = make(chan int, 100)

var ack_clientiOver70[maxClienti] chan int 
var ack_clientiUnder70[maxClienti] chan int  

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Cliente(tipo int, id int) {
	var tt int
	fmt.Printf("[Cliente %s]: partenza! \n", tipoCliente[tipo])
	var ris int

	if tipo == 0 {
		entrataSportelliClientiOver70 <- id
		ris = <-ack_clientiOver70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: accesso allo sportello! \n", tipoCliente[tipo])

		entrataCaveauClientiOver70 <- id
		ris = <-ack_clientiOver70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: accesso al caveau! \n", tipoCliente[tipo])

		tt = (rand.Intn(10) + 1)
		time.Sleep(time.Duration(tt) * time.Second) //tempo nel caveau

		uscitaCaveauClientiOver70 <- id
		ris = <-ack_clientiOver70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: uscito dal caveau! \n", tipoCliente[tipo])

		uscitaSportelliClientiOver70 <- id
		ris = <-ack_clientiOver70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: uscito dallo sportello! \n", tipoCliente[tipo])
	} else {
		entrataSportelliClientiUnder70 <- id
		ris = <-ack_clientiUnder70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: accesso allo sportello! \n", tipoCliente[tipo])

		entrataCaveauClientiUnder70 <- id
		ris = <-ack_clientiUnder70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: accesso al caveau! \n", tipoCliente[tipo])

		tt = (rand.Intn(10) + 1)
		time.Sleep(time.Duration(tt) * time.Second) //tempo nel caveau

		uscitaCaveauClientiUnder70 <- id
		ris = <-ack_clientiUnder70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: uscito dal caveau! \n", tipoCliente[tipo])

		uscitaSportelliClientiUnder70 <- id
		ris = <-ack_clientiUnder70[id]
		if ris == -1 {
			fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
			done <- true
			return
		}
		fmt.Printf("[Cliente %s]: uscito dallo sportello! \n", tipoCliente[tipo])
	}

	done <- true
}

func sportelli() {
	var fine bool = false
	var sportelliLiberi int = maxSportelli
	var clientiUsciti = 0;
	var id int = 0;

	for {
		select {
			case id = <-when(fine == false && sportelliLiberi > 0, entrataSportelliClientiOver70):
				sportelliLiberi--
				ack_clientiOver70[id] <- 1
				fmt.Printf("[sportelli]: cliente over 70 entrato in sportelli, ci sono %d sportelli liberi\n", sportelliLiberi)
			case id = <-when(fine == false && sportelliLiberi > 0 && len(entrataSportelliClientiOver70) == 0, entrataSportelliClientiUnder70):
				sportelliLiberi--
				ack_clientiUnder70[id] <- 1
				fmt.Printf("[sportelli]: cliente under 70 entrato in sportelli, ci sono %d sportelli liberi\n", sportelliLiberi)

			case id = <-when(fine == false, uscitaSportelliClientiOver70):
				sportelliLiberi++
				clientiUsciti++
				ack_clientiOver70[id] <- 1
				fmt.Printf("[sportelli]: cliente over 70 uscito da sportelli, ci sono %d sportelli liberi\n", sportelliLiberi)
			case id = <-when(fine == false, uscitaSportelliClientiUnder70):
				sportelliLiberi++
				clientiUsciti++
				ack_clientiUnder70[id] <- 1
				fmt.Printf("[sportelli]: cliente under 70 uscito da sportelli, ci sono %d sportelli liberi\n", sportelliLiberi)
			
			//terminazione
			case id = <-when(fine == true, entrataSportelliClientiOver70):
				ack_clientiOver70[id] <- -1
			case id = <-when(fine == true, entrataSportelliClientiUnder70):
				ack_clientiUnder70[id] <- -1
			case id = <-when(fine == true, uscitaSportelliClientiOver70):
				ack_clientiOver70[id] <- -1
			case id = <-when(fine == true, uscitaSportelliClientiUnder70):
				ack_clientiUnder70[id] <- -1
			
			case <-terminaSportelli:
				fmt.Printf("[sportelli]: termino\n")
				done <- true
				return
		}

		if clientiUsciti == maxClienti {
			fine = true
		}
	}
}

func caveau() {
	var numClientiOver70Entrati int = 0;
	var numClientiUnder70Entrati int = 0;
	var numClientiOver70Usciti int = 0;
	var numClientiUnder70Usciti int = 0;
	var libero bool = true;
	var fine bool = false; // diventa true quando sono stati completati id controlli
	var id int = 0;

	for {
		select {
			case id = <-when(fine == false && (libero == true) &&
			((numClientiOver70Entrati > numClientiUnder70Entrati) ||
			(numClientiOver70Entrati <= numClientiUnder70Entrati && len(entrataCaveauClientiOver70) == 0)), entrataCaveauClientiUnder70):
				libero = false
				numClientiUnder70Entrati++
				ack_clientiUnder70[id] <- 1
				fmt.Printf("[caveau]: entrato cliente under 70, sono entrati %d over 70 e %d under 70\n", numClientiOver70Entrati, numClientiUnder70Entrati)
			case id = <-when(fine == false && (libero == true) &&
			((numClientiOver70Entrati <= numClientiUnder70Entrati) ||
			(numClientiOver70Entrati > numClientiUnder70Entrati && len(entrataCaveauClientiUnder70) == 0)), entrataCaveauClientiOver70):
				libero = false
				numClientiOver70Entrati++
				ack_clientiOver70[id] <- 1
				fmt.Printf("[caveau]: entrato cliente over 70, sono entrati %d over 70 e %d under 70\n", numClientiOver70Entrati, numClientiUnder70Entrati)
			case id = <-when(fine == false && (libero == false), uscitaCaveauClientiUnder70):
				libero = true
				numClientiUnder70Usciti++
				ack_clientiUnder70[id] <- 1
				fmt.Printf("[caveau]: uscito cliente under 70, sono entrati %d over 70 e %d under 70\n", numClientiOver70Entrati, numClientiUnder70Entrati)
			case id = <-when(fine == false && (libero == false), uscitaCaveauClientiOver70):
				libero = true
				numClientiOver70Usciti++
				ack_clientiOver70[id] <- 1
				fmt.Printf("[caveau]: uscito cliente over 70, sono entrati %d over 70 e %d under 70\n", numClientiOver70Entrati, numClientiUnder70Entrati)

			//terminazione
			case id = <-when(fine == true, entrataCaveauClientiOver70):
				ack_clientiOver70[id] <- -1
			case id = <-when(fine == true, entrataCaveauClientiUnder70):
				ack_clientiUnder70[id] <- -1
			case id = <-when(fine == true, uscitaCaveauClientiOver70):
				ack_clientiOver70[id] <- -1
			case id = <-when(fine == true, uscitaCaveauClientiUnder70):
				ack_clientiUnder70[id] <- -1
			
			case <-terminaCaveau:
				fmt.Printf("[caveau]: termino\n")
				done <- true
				return
		}

		if numClientiUnder70Usciti == maxClientiUnder70 && numClientiOver70Usciti == maxClientiOver70 {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	maxClientiOver70 = rand.Intn(maxClienti) + 1
	maxClientiUnder70 = maxClienti - maxClientiOver70
	fmt.Printf("[main] attivo programma con %d clienti Over70 e %d clienti Under70\n", maxClientiOver70, maxClientiUnder70)
	
	//inizializzazione canali di ack
	for i := 0; i < maxClienti; i++ {
		ack_clientiOver70[i] = make(chan int)
		ack_clientiUnder70[i] = make(chan int)
	}

	go sportelli()
	go caveau()

	for i := 0; i < maxClientiOver70; i++ {
		go Cliente(0, i)
	}

	for i := 0; i < maxClientiUnder70; i++ {
		go Cliente(1, i)
	}

	for i := 0; i < maxClienti; i++ { //terminazione studenti
		<-done
	}

	terminaSportelli <- true
	<-done
	terminaCaveau <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
