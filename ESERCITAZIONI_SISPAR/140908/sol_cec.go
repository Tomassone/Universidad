package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxUtenti = 15 
const maxOperatori = 10
const maxIterazioni = 15

var done = make(chan bool)
var terminaUrp = make(chan bool)

var entrataTasse = make(chan int, 100)
var entrataScuola = make(chan int, 100)
var entrataEdilizia = make(chan int, 100)

var uscitaTasse = make(chan int, 100)
var uscitaScuola = make(chan int, 100)
var uscitaEdilizia = make(chan int, 100)

var ack_utenti[maxUtenti] chan int  

var temi = [3]string{"tasse", "scuola", "edilizia"}

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Utente(id int) {
	var tt int
	fmt.Printf("[Utente %d]: partenza! \n", id)
	var ris int

	for {
		choice := rand.Intn(3)

		if choice == 0 { //sclego tasse
			entrataTasse <- id
			ris = <- ack_utenti[id] 
			if ris == -1 {
				fmt.Printf("[Utente %d]: termino! \n", id)
				done <- true
				return
			}
			fmt.Printf("[Utente %d]: parla con l'operatore di tasse! \n", id)

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo con l'operatore

			uscitaTasse <- id
			ris = <- ack_utenti[id] 
			if ris == -1 {
				fmt.Printf("[Utente %d]: termino! \n", id)
				done <- true
				return
			}
			fmt.Printf("[Utente %d]: finito con l'operatore! \n", id)
		} else if choice == 1 {
			entrataScuola <- id
			ris = <- ack_utenti[id] 
			if ris == -1 {
				fmt.Printf("[Utente %d]: termino! \n", id)
				done <- true
				return
			}
			fmt.Printf("[Utente %d]: parla con l'operatore di scuola! \n", id)

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo con l'operatore

			uscitaScuola <- id
			ris = <- ack_utenti[id] 
			if ris == -1 {
				fmt.Printf("[Utente %d]: termino! \n", id)
				done <- true
				return
			}
			fmt.Printf("[Utente %d]: finito con l'operatore! \n", id)
		} else {
			entrataEdilizia <- id
			ris = <- ack_utenti[id] 
			if ris == -1 {
				fmt.Printf("[Utente %d]: termino! \n", id)
				done <- true
				return
			}
			fmt.Printf("[Utente %d]: parla con l'operatore di edilizia! \n", id)

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo con l'operatore

			uscitaEdilizia <- id
			ris = <- ack_utenti[id] 
			if ris == -1 {
				fmt.Printf("[Utente %d]: termino! \n", id)
				done <- true
				return
			}
			fmt.Printf("[Utente %d]: finito con l'operatore! \n", id)
		}
    }
}

func gestioneRichieste() {
	var fine bool = false
	var chiusura bool = false
	var utentiDentroTasse int = 0
	var utentiEntratiTasse int = 0
	var utentiDentroScuola int = 0
	var utentiEntratiScuola int = 0
	var utentiDentroEdilizia int = 0
	var utentiEntratiEdilizia int = 0
	var operatoriLiberi int = maxOperatori
	var id int = 0

	for {
		select {
			case <-terminaUrp:
				fmt.Printf("[gestioneRichieste]: termino\n")
				done <- true
				return
			case id = <-when(chiusura == false && fine == false && operatoriLiberi > 0, entrataTasse):
				utentiDentroTasse++
				utentiEntratiTasse++
				ack_utenti[id] <- 1
				operatoriLiberi--
				fmt.Printf("[gestioneRichieste]: utente entrato in sezione tasse, ci sono %d utenti dentro alla tasse\n", utentiDentroTasse)
			case id = <-when(chiusura == false && fine == false && operatoriLiberi > 0 && 
				len(entrataTasse) == 0 && (len(entrataScuola) >= len(entrataEdilizia)), entrataScuola):
				utentiDentroScuola++
				utentiEntratiScuola++
				operatoriLiberi--
				ack_utenti[id] <- 1
				fmt.Printf("[gestioneRichieste]: utente entrato in sezione scuola, ci sono %d utenti dentro alla scuola\n", utentiDentroScuola)
			case id = <-when(chiusura == false && fine == false && operatoriLiberi > 0 && 
				len(entrataTasse) == 0 && (len(entrataEdilizia) > len(entrataScuola)), entrataEdilizia):
				utentiDentroEdilizia++
				utentiEntratiEdilizia++
				operatoriLiberi--
				ack_utenti[id] <- 1
				fmt.Printf("[gestioneRichieste]: utente entrato in sezione edilizia, ci sono %d utenti dentro alla edilizia\n", utentiDentroEdilizia)
				
			case id = <-when(fine == false && utentiDentroTasse > 0, uscitaTasse):
				utentiDentroTasse--
				operatoriLiberi++
				ack_utenti[id] <- 1
				fmt.Printf("[gestioneRichieste]: utente uscito da tasse, ci sono %d utenti dentro\n", utentiDentroTasse)
			case id = <-when(fine == false && utentiDentroScuola > 0, uscitaScuola):
				utentiDentroScuola--
				operatoriLiberi++
				ack_utenti[id] <- 1
				fmt.Printf("[gestioneRichieste]: utente uscito da scuola, ci sono %d utenti dentro\n", utentiDentroScuola)
			case id = <-when(fine == false && utentiDentroEdilizia > 0, uscitaEdilizia):
				utentiDentroEdilizia--
				operatoriLiberi++
				ack_utenti[id] <- 1
				fmt.Printf("[gestioneRichieste]: utente uscito da edilizia, ci sono %d utenti dentro\n", utentiDentroEdilizia)

			//terminazione
			case id = <-when(fine == true, entrataTasse):
				ack_utenti[id] <- -1
			case id = <-when(fine == true, entrataScuola):
				ack_utenti[id] <- -1
			case id = <-when(fine == true, uscitaTasse):
				ack_utenti[id] <- -1
			case id = <-when(fine == true, uscitaScuola):
				ack_utenti[id] <- -1
			case id = <-when(fine == true, entrataEdilizia):
				ack_utenti[id] <- -1
			case id = <-when(fine == true, uscitaEdilizia):
				ack_utenti[id] <- -1
		}

		if utentiEntratiTasse + utentiEntratiScuola + utentiEntratiEdilizia >= 2 * maxIterazioni { //condizione abritraria di terminazione
			chiusura = true
		}

		if (chiusura == true && utentiDentroScuola + utentiDentroTasse + utentiDentroEdilizia == 0) {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	fmt.Printf("[main] attivo programma\n")

	//inizializzazione canali di ack
	for i := 0; i < maxUtenti; i++ {
		ack_utenti[i] = make(chan int)
	}

	go gestioneRichieste()

	for i := 0; i < maxUtenti; i++ {
		go Utente(i)
	}

	for i := 0; i < maxUtenti; i++ { 
		<- done
	}

	terminaUrp <- true
	<- done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
