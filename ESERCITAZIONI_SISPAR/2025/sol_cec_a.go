package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxClienti = 15 
const maxBagniniFun = 5
const maxFisioterapisti = 5
const maxIterazioni = 20

const maxPosti = 10

var done = make(chan bool)
var terminaSmistamento = make(chan bool)

var entrataFunCliente = make(chan int, 100)
var entrataFisioCliente = make(chan int, 100)
var entrataFunBagnino = make(chan int, 100)

var uscitaFunCliente = make(chan int, 100)
var uscitaFisioCliente = make(chan int, 100)
var uscitaFunBagnino = make(chan int, 100)

var ack_clienti[maxClienti] chan int  
var ack_bagnino[maxBagniniFun] chan int 

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Cliente(id int) {
	var tt int
	fmt.Printf("[Cliente]: partenza! \n")
	var ris int

	for {
		choice := rand.Intn(2)

		if choice == 0 { //entro in area fun
			entrataFunCliente <- id
			ris = <- ack_clienti[id] 
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: entrato in fun! \n")

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella fun

			uscitaFunCliente <- id
			ris = <- ack_clienti[id] 
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: uscito dalla fun! \n")
		} else {
			entrataFisioCliente <- id
			ris = <- ack_clienti[id] 
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: entrato in fisio! \n")

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella fisio

			uscitaFisioCliente <- id
			ris = <- ack_clienti[id] 
			if ris == -1 {
				fmt.Printf("[Cliente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Cliente]: uscito dalla fisio! \n")
		}
    }
}

func Bagnino(id int) {
    fmt.Printf("[Bagnino]: partenza!\n")
    status := false

    for {
		if !status {
			uscitaFunBagnino <- id
			if <- ack_bagnino[id] == -1 {
				fmt.Printf("[Bagnino]: termino! \n")
				done <- true
				return
			}
			status = true
		} else {
			entrataFunBagnino <- id
			if <- ack_bagnino[id] == -1 {
				fmt.Printf("[Bagnino]: termino! \n")
				done <- true
				return
			}
			status = false
		}

		time.Sleep(time.Duration(rand.Intn(5)+1) * time.Second)  
    }
}

func gestioneIngressi() {
	var fine bool = false
	var chiusura bool = false
	var clientiDentroFun int = 0
	var clientiEntratiFun int = 0
	var clientiDentroFisio int = 0
	var clientiEntratiFisio int = 0
	var operatoriLiberiInFisio int = maxFisioterapisti
	var bagniniInFun int = maxBagniniFun
	var id int = 0

	for {
		select {
			case <-terminaSmistamento:
				fmt.Printf("[gestioneIngressi]: termino\n")
				done <- true
				return
			case id = <-when(chiusura == false && fine == false && clientiDentroFun + clientiDentroFisio < maxPosti && bagniniInFun > 0 &&
				len(entrataFunBagnino) == 0, entrataFunCliente):
				clientiDentroFun++
				clientiEntratiFun++
				ack_clienti[id] <- 1
				fmt.Printf("[gestioneIngressi]: cliente entrato in fun, ci sono %d clienti dentro alla fun\n", clientiDentroFun)
			case id = <-when(chiusura == false && fine == false && clientiDentroFisio + clientiDentroFun < maxPosti && len(entrataFunCliente) == 0 &&
			operatoriLiberiInFisio > 0, entrataFisioCliente):
				clientiDentroFisio++
				clientiEntratiFisio++
				operatoriLiberiInFisio--
				ack_clienti[id] <- 1
				fmt.Printf("[gestioneIngressi]: cliente entrato in fisio, ci sono %d clienti dentro alla fisio\n", clientiDentroFisio)
			case id = <-when(chiusura == false && fine == false, entrataFunBagnino):
				bagniniInFun++
				ack_bagnino[id] <- 1
				fmt.Printf("[gestioneIngressi]: bagnino entrato in fun, ci sono %d bagnini dentro alla fun\n", bagniniInFun)
				
			case id = <-when(fine == false && clientiDentroFun > 0, uscitaFunCliente):
				clientiDentroFun--
				ack_clienti[id] <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da fun, ci sono %d clienti dentro\n", clientiDentroFun)
			case id = <-when(fine == false && clientiDentroFisio > 0, uscitaFisioCliente):
				clientiDentroFisio--
				operatoriLiberiInFisio++
				ack_clienti[id] <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da fisio, ci sono %d clienti dentro\n", clientiDentroFisio)
			case id = <-when(fine == false && (bagniniInFun > 1 || clientiDentroFun == 0), uscitaFunBagnino):
				bagniniInFun--
				ack_bagnino[id] <- 1
				fmt.Printf("[gestioneIngressi]: bagnino da fun, ci sono %d bagnini dentro alla fun\n", bagniniInFun)

			//terminazione
			case id = <-when(fine == true, entrataFunCliente):
				ack_clienti[id] <- -1
			case id = <-when(fine == true, entrataFisioCliente):
				ack_clienti[id] <- -1
			case id = <-when(fine == true, uscitaFunCliente):
				ack_clienti[id] <- -1
			case id = <-when(fine == true, uscitaFisioCliente):
				ack_clienti[id] <- -1
			case id = <-when(fine == true, entrataFunBagnino):
				ack_bagnino[id] <- -1
			case id = <-when(fine == true, uscitaFunBagnino):
				ack_bagnino[id] <- -1
		}

		if clientiEntratiFun + clientiEntratiFisio >= 2 * maxIterazioni { //condizione abritraria di terminazione
			chiusura = true
		}

		if (chiusura == true && clientiDentroFisio + clientiDentroFun == 0) {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	fmt.Printf("[main] attivo programma\n")

	//inizializzazione canali di ack
	for i := 0; i < maxClienti; i++ {
		ack_clienti[i] = make(chan int)
	}
	for i := 0; i < maxBagniniFun; i++ {
		ack_bagnino[i] = make(chan int)
	}

	go gestioneIngressi()

	for i := 0; i < maxBagniniFun; i++ {
		go Bagnino(i)
	}

	for i := 0; i < maxClienti; i++ {
		go Cliente(i)
	}

	for i := 0; i < maxClienti; i++ { 
		<- done
	}

	for i := 0; i < maxBagniniFun; i++ {
		<- done
	}

	terminaSmistamento <- true
	<- done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
