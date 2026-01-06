package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxClienti = 15 
const maxOperatori = 5
const maxPostiPiscina = 3
const maxPostiSPA = 3 
var maxPiscina = 0
var maxSpa = 0
var maxPiscinaSpa = 0

var maxOperatoriPiscina = 0
var maxOperatoriSpa = 0

const tipoClientePiscina int = 0
const tipoClienteSpa int  = 1
const tipoClientePiscinaSpa int = 2

var tipoCliente = [3]string{"Piscina", "Spa", "PiscinaSpa"}
var tipoOperatore = [2]string{"Piscina", "Spa"}

var done = make(chan bool)
var terminaControlli = make(chan bool)
var terminaOperatori = make(chan bool)

var entrataPiscinaClientePiscina = make(chan int, 100)
var entrataSpaClienteSpa = make(chan int, 100)
var entrataSpaClientePiscinaSpa = make(chan int, 100)
var entrataPiscinaClientePiscinaSpa = make(chan int, 100)
var entrataPiscinaOperatore = make(chan int, 100)
var entrataSpaOperatore = make(chan int, 100)

var uscitaPiscinaClientePiscina = make(chan int, 100)
var uscitaSpaClienteSpa = make(chan int, 100)
var uscitaSpaClientePiscinaSpa = make(chan int, 100)
var uscitaPiscinaClientePiscinaSpa = make(chan int, 100)
var uscitaPiscinaOperatore = make(chan int, 100)
var uscitaSpaOperatore = make(chan int, 100)

var ack_piscina = make(chan int)  
var ack_spa = make(chan int)  
var ack_piscinaSpa = make(chan int) 
var ack_op_piscina = make(chan int) 
var ack_op_spa = make(chan int) 

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Cliente(tipo int) {
	var tt int
	fmt.Printf("[Cliente %s]: partenza! \n", tipoCliente[tipo])
	var ris int

	switch (tipo) {
		case 0:
			entrataPiscinaClientePiscina <- 1
			ris = <- ack_piscina
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: entrato in piscina! \n", tipoCliente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella piscina

			uscitaPiscinaClientePiscina <- 1
			ris = <- ack_piscina
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: uscito dalla piscina! \n", tipoCliente[tipo])
		case 1:
			entrataSpaClienteSpa <- 1
			ris = <- ack_spa
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: entrato in spa! \n", tipoCliente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella spa

			uscitaSpaClienteSpa <- 1
			ris = <- ack_spa
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: uscito dalla spa! \n", tipoCliente[tipo])
		case 2:
			entrataPiscinaClientePiscinaSpa <- 1
			ris = <- ack_piscinaSpa
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: entrato in piscina! \n", tipoCliente[tipo])

			uscitaPiscinaClientePiscinaSpa <- 1
			ris = <- ack_piscinaSpa
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: uscito dalla piscina!\n", tipoCliente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella piscina

			entrataSpaClientePiscinaSpa <- 1
			ris = <- ack_piscinaSpa
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: entrato in spa! \n", tipoCliente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella spa

			uscitaSpaClientePiscinaSpa <- 1
			ris = <- ack_piscinaSpa
			if ris == -1 {
				fmt.Printf("[Cliente %s]: termino! \n", tipoCliente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Cliente %s]: uscito dalla spa! \n", tipoCliente[tipo])
	}

	done <- true
}

func Operatore(tipo int) {
    fmt.Printf("[Operatore %s]: partenza!\n", tipoOperatore[tipo])
    status := false

    for {
        select {
        case <-terminaOperatori:
            fmt.Printf("[Operatore %s]: termino!\n", tipoOperatore[tipo])
            done <- true
            return
        default:
            choice := rand.Intn(2)

            if tipo == 0 {
                if choice == 0 && !status {
                    uscitaPiscinaOperatore <- 1
                    if <-ack_op_piscina == -1 {
                        done <- true
                        return
                    }
                    status = true
                } else if choice == 0 && status {
                    entrataPiscinaOperatore <- 1
                    if <-ack_op_piscina == -1 {
                        done <- true
                        return
                    }
                    status = false
                }
            } else {
                if choice == 0 && !status {
                    uscitaSpaOperatore <- 1
                    if <-ack_op_spa == -1 {
                        done <- true
                        return
                    }
                    status = true
                } else if choice == 0 && status {
                    entrataSpaOperatore <- 1
                    if <-ack_op_spa == -1 {
                        done <- true
                        return
                    }
                    status = false
                }
            }

            time.Sleep(time.Duration(rand.Intn(5)+1) * time.Second)
        }
    }
}

func gestioneIngressi() {
	var clientiGestiti = false
	var fine bool = false
	var clientiDentroPiscina int = 0
	var clientiEntratiPiscina int = 0
	var clientiDentroSpa int = 0
	var clientiEntratiSpa int = 0
	var operatoriInSpa int = maxOperatoriSpa
	var operatoriInPiscina int = maxOperatoriPiscina

	for {
		select {
			case <-terminaControlli:
				fmt.Printf("[gestioneIngressi]: termino\n")
				done <- true
				return
			case <-when(fine == false && clientiDentroPiscina < maxPostiPiscina && operatoriInPiscina > 0, entrataPiscinaClientePiscinaSpa):
				clientiDentroPiscina++
				clientiEntratiPiscina++
				ack_piscinaSpa <- 1
				fmt.Printf("[gestioneIngressi]: utente spaPiscina entrato in piscina, ci sono %d clienti dentro alla piscina\n", clientiDentroPiscina)
			case <-when(fine == false && clientiDentroPiscina < maxPostiPiscina && len(entrataPiscinaClientePiscinaSpa) == 0 &&
			len(entrataSpaClientePiscinaSpa) == 0 && operatoriInPiscina > 0, entrataPiscinaClientePiscina):
				clientiDentroPiscina++
				clientiEntratiPiscina++
				ack_piscina <- 1
				fmt.Printf("[gestioneIngressi]: utente piscina entrato in piscina, ci sono %d clienti dentro alla piscina\n", clientiDentroPiscina)
			case <-when(fine == false && clientiDentroSpa < maxPostiSPA && operatoriInSpa > 0, entrataSpaClientePiscinaSpa):
				clientiDentroSpa++
				clientiEntratiSpa++
				ack_piscinaSpa <- 1
				fmt.Printf("[gestioneIngressi]: utente spaPiscina entrato in spa, ci sono %d clienti dentro alla spa\n", clientiDentroSpa)
			case <-when(fine == false && clientiDentroSpa < maxPostiSPA && len(entrataSpaClientePiscinaSpa) == 0 &&
			len(entrataPiscinaClientePiscinaSpa) == 0 && operatoriInSpa > 0, entrataSpaClienteSpa):
				clientiDentroSpa++
				clientiEntratiSpa++
				ack_spa <- 1
				fmt.Printf("[gestioneIngressi]: utente spa entrato in spa, ci sono %d clienti dentro alla spa\n", clientiDentroSpa)
		
			case <-when(fine == false, entrataPiscinaOperatore):
				operatoriInPiscina++
				ack_op_piscina <- 1
				fmt.Printf("[gestioneIngressi]: operatore entrato in piscina, ci sono %d op dentro alla piscina\n", operatoriInPiscina)
			case <-when(fine == false, entrataSpaOperatore):
				operatoriInSpa++
				ack_op_spa <- 1
				fmt.Printf("[gestioneIngressi]: operatore entrato in spa, ci sono %d op dentro alla spa\n", operatoriInSpa)
				
			case <-when(fine == false && clientiDentroPiscina > 0, uscitaPiscinaClientePiscina):
				clientiDentroPiscina--
				ack_piscina <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da piscina, ci sono %d clienti dentro\n", clientiDentroPiscina)
			case <-when(fine == false && clientiDentroSpa > 0, uscitaSpaClienteSpa):
				clientiDentroSpa--
				ack_spa <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da spa, ci sono %d clienti dentro\n", clientiDentroSpa)
			case <-when(fine == false && clientiDentroPiscina > 0, uscitaPiscinaClientePiscinaSpa):
				clientiDentroPiscina--
				ack_piscinaSpa <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da piscina, ci sono %d clienti dentro\n", clientiDentroPiscina)
			case <-when(fine == false && clientiDentroSpa > 0, uscitaSpaClientePiscinaSpa):
				clientiDentroSpa--
				ack_piscinaSpa <- 1
				fmt.Printf("[gestioneIngressi]: cliente uscito da spa, ci sono %d clienti dentro\n", clientiDentroSpa)
			
			case <-when(fine == false && (clientiDentroPiscina == 0 || operatoriInPiscina > 1), uscitaPiscinaOperatore):
				operatoriInPiscina--
				ack_op_piscina <- 1
				fmt.Printf("[gestioneIngressi]: operatore uscito da piscina, ci sono %d op dentro alla piscina\n", operatoriInPiscina)
			case <-when(fine == false && (clientiDentroSpa == 0 || operatoriInSpa > 1), uscitaSpaOperatore):
				operatoriInSpa--
				ack_op_spa <- 1
				fmt.Printf("[gestioneIngressi]: operatore uscito da spa, ci sono %d op dentro alla spa\n", operatoriInSpa)

			//terminazione
			case <-when(fine == true, entrataPiscinaClientePiscina):
				ack_piscina <- -1
			case <-when(fine == true, entrataSpaClienteSpa):
				ack_spa <- -1
			case <-when(fine == true, entrataPiscinaClientePiscinaSpa):
				ack_piscinaSpa <- -1
			case <-when(fine == true, entrataSpaClientePiscinaSpa):
				ack_piscinaSpa <- -1
			case <-when(fine == true, uscitaPiscinaClientePiscina):
				ack_piscina <- -1
			case <-when(fine == true, uscitaSpaClienteSpa):
				ack_spa <- -1
			case <-when(fine == true, uscitaPiscinaClientePiscinaSpa):
				ack_piscinaSpa <- -1
			case <-when(fine == true, uscitaSpaClientePiscinaSpa):
				ack_piscinaSpa <- -1
			case <-when(fine == true, entrataPiscinaOperatore):
				ack_op_piscina <- -1
			case <-when(fine == true, entrataSpaOperatore):
				ack_op_spa <- -1
			case <-when(fine == true, uscitaPiscinaOperatore):
				ack_op_piscina <- -1
			case <-when(fine == true, uscitaSpaOperatore):
				ack_op_spa <- -1
		}

		if clientiEntratiPiscina == maxPiscina && clientiEntratiSpa == maxSpa {
			clientiGestiti = true
		}

		if clientiDentroPiscina == 0 && clientiDentroSpa == 0 && clientiGestiti == true {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	maxPiscina = rand.Intn(maxClienti / 2) + 1
	maxPiscinaSpa = maxClienti / 2 - maxPiscina
	maxSpa = maxClienti - maxPiscinaSpa - maxPiscina
	maxOperatoriPiscina = rand.Intn(maxOperatori)
	maxOperatoriSpa = maxOperatori - maxOperatoriPiscina
	fmt.Printf("[main] attivo programma\n")

	go gestioneIngressi()

	for i := 0; i < maxOperatoriPiscina; i++ {
		go Operatore(0)
	}

	for i := 0; i < maxOperatoriSpa; i++ {
		go Operatore(1)
	}

	for i := 0; i < maxPiscina; i++ {
		go Cliente(0)
	}

	for i := 0; i < maxSpa; i++ {
		go Cliente(1)
	}

	for i := 0; i < maxPiscinaSpa; i++ {
		go Cliente(2)
	}

	for i := 0; i < maxClienti; i++ { 
		<-done
	}

	for i := 0; i < maxOperatori; i++ {
		terminaOperatori <- true
	}

	for i := 0; i < maxOperatori; i++ {
		<-done
	}

	terminaControlli<- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
