package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxUtenti = 15
const maxScambi = 10

var done = make(chan bool)
var terminaScambi = make(chan bool)

var richiestaScambioNuovo = make(chan int, 100)
var richiestaScambioEsperto = make(chan int, 100)
var accettazioneScambioNuovoUomoDonna = make(chan int, 100)
var accettazioneScambioNuovoUomoBambino = make(chan int, 100)
var accettazioneScambioNuovoDonnaUomo = make(chan int, 100)
var accettazioneScambioNuovoDonnaBambino = make(chan int, 100)
var accettazioneScambioNuovoBambinoUomo = make(chan int, 100)
var accettazioneScambioNuovoBambinoDonna = make(chan int, 100)
var accettazioneScambioEspertoUomoDonna = make(chan int, 100)
var accettazioneScambioEspertoUomoBambino = make(chan int, 100)
var accettazioneScambioEspertoDonnaUomo = make(chan int, 100)
var accettazioneScambioEspertoDonnaBambino = make(chan int, 100)
var accettazioneScambioEspertoBambinoUomo = make(chan int, 100)
var accettazioneScambioEspertoBambinoDonna = make(chan int, 100)
var maxUtentiNuovi = 0
var maxUtentiEsperti = 0 

//canali di ack
var ack_utentiNuovi = make(chan int)
var ack_utentiEsperti = make(chan int)
var ack_utenti[maxUtenti] chan int

var tipoRichiesta = [6]string{"UomoDonna", "UomoBambino", "DonnaUomo", "DonnaBambino", "BambinoUomo", "BambinoDonna"}

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Utente(tipo int, id int) {
	fmt.Printf("[Utente]: partenza! \n")
	var ris int

	for {
		if tipo == 0 { //utente nuovo
			richiestaTipo := rand.Intn(6)
			richiestaScambioNuovo <- richiestaTipo
			ris = <-ack_utentiNuovi
			if ris == -1 {
				fmt.Printf("[Utente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Utente]: effettuata richiesta! \n")

			switch richiestaTipo {
			case 0:
				accettazioneScambioNuovoUomoDonna <- id
			case 1:
				accettazioneScambioNuovoUomoBambino <- id
			case 2:
				accettazioneScambioNuovoDonnaUomo <- id
			case 3:
				accettazioneScambioNuovoDonnaBambino <- id
			case 4:
				accettazioneScambioNuovoBambinoUomo <- id	
			case 5:
				accettazioneScambioNuovoBambinoDonna <- id
			}
			ris = <-ack_utenti[id]
			if ris == -1 {
				fmt.Printf("[Utente]: termino! \n")
				done <- true
				return
			}

			fmt.Printf("[Utente]: scambiata bici! \n")
			tipo = 1 //l'utente diventa esperto
		} else { //utente esperto
			richiestaTipo := rand.Intn(6)
			richiestaScambioEsperto <- richiestaTipo
			ris = <-ack_utentiEsperti
			if ris == -1 {
				fmt.Printf("[Utente]: termino! \n")
				done <- true
				return
			}
			fmt.Printf("[Utente]: effettuata richiesta! \n")

			switch richiestaTipo {
			case 0:
				accettazioneScambioEspertoUomoDonna <- id
			case 1:
				accettazioneScambioEspertoUomoBambino <- id	
			case 2:
				accettazioneScambioEspertoDonnaUomo <- id				
			case 3:
				accettazioneScambioEspertoDonnaBambino <- id				
			case 4:
				accettazioneScambioEspertoBambinoUomo <- id				
			case 5:
				accettazioneScambioEspertoBambinoDonna <- id				
			}
			ris = <-ack_utenti[id]
			if ris == -1 {
				fmt.Printf("[Utente]: termino! \n")
				done <- true
				return
			}

			fmt.Printf("[Utente]: scambiata bici! \n")
		}
	}
}

func gestioneScambi() {
	var scambiEffettuati int = 0;
	var fine bool = false;
	var idCorrente int = 0;
	var tipoCorrente int = 0;
	var richiesteUomoDonna int = 0;
	var richiesteUomoBambino int = 0;
	var richiesteDonnaUomo int = 0;
	var richiesteDonnaBambino int = 0;
	var richiesteBambinoUomo int = 0;
	var richiesteBambinoDonna int = 0;

	for {
		select {
			case tipoCorrente = <-when(fine == false && scambiEffettuati < maxScambi &&
				len(richiestaScambioEsperto) == 0, richiestaScambioNuovo):
				ack_utentiNuovi <- 1	
				switch tipoCorrente {
				case 0:
					richiesteUomoDonna++					
				case 1:
					richiesteUomoBambino++					
				case 2:
					richiesteDonnaUomo++					
				case 3:
					richiesteDonnaBambino++					
				case 4:
					richiesteBambinoUomo++					
				case 5:
					richiesteBambinoDonna++					
				}
				fmt.Printf("[Gestione scambi]: registrato scambio da nuovo, ci sono %d scambi effettuati\n", scambiEffettuati)
			case tipoCorrente = <-when(fine == false && scambiEffettuati < maxScambi, richiestaScambioEsperto):
				ack_utentiEsperti <- 1
				switch tipoCorrente {
				case 0:
					richiesteUomoDonna++					
				case 1:
					richiesteUomoBambino++					
				case 2:
					richiesteDonnaUomo++					
				case 3:
					richiesteDonnaBambino++					
				case 4:
					richiesteBambinoUomo++					
				case 5:
					richiesteBambinoDonna++					
				}
				fmt.Printf("[Gestione scambi]: registrato scambio da esperto, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteDonnaUomo > 0 && len(accettazioneScambioEspertoUomoDonna) == 0, accettazioneScambioNuovoUomoDonna):
				scambiEffettuati++
				richiesteDonnaUomo--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteDonnaUomo > 0, accettazioneScambioEspertoUomoDonna):
				scambiEffettuati++
				richiesteDonnaUomo--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteBambinoUomo > 0 && len(accettazioneScambioEspertoUomoBambino) == 0, accettazioneScambioNuovoUomoBambino):
				scambiEffettuati++
				richiesteBambinoUomo--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteBambinoUomo > 0, accettazioneScambioEspertoUomoBambino):
				scambiEffettuati++
				richiesteBambinoUomo--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteUomoDonna > 0 && len(accettazioneScambioEspertoDonnaUomo) == 0, accettazioneScambioNuovoDonnaUomo):
				scambiEffettuati++
				richiesteUomoDonna--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteUomoDonna > 0, accettazioneScambioEspertoDonnaUomo):
				scambiEffettuati++
				richiesteUomoDonna--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteBambinoDonna > 0 && len(accettazioneScambioEspertoDonnaBambino) == 0, accettazioneScambioNuovoDonnaBambino):
				scambiEffettuati++
				richiesteBambinoDonna--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteBambinoDonna > 0, accettazioneScambioEspertoDonnaBambino):
				scambiEffettuati++
				richiesteBambinoDonna--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteUomoBambino > 0 && len(accettazioneScambioEspertoBambinoUomo) == 0, accettazioneScambioNuovoBambinoUomo):
				scambiEffettuati++
				richiesteUomoBambino--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteUomoBambino > 0, accettazioneScambioEspertoBambinoUomo):
				scambiEffettuati++
				richiesteUomoBambino--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteDonnaBambino > 0 && len(accettazioneScambioEspertoBambinoDonna) == 0, accettazioneScambioNuovoBambinoDonna):
				scambiEffettuati++
				richiesteDonnaBambino--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)
			case idCorrente = <-when(fine == false && richiesteDonnaBambino > 0, accettazioneScambioEspertoBambinoDonna):
				scambiEffettuati++
				richiesteDonnaBambino--
				ack_utenti[idCorrente] <- 1
				fmt.Printf("[Gestione scambi]: effettuato scambio, ci sono %d scambi effettuati\n", scambiEffettuati)

			//terminazione
			case <-when(fine == true, richiestaScambioNuovo):
				ack_utentiNuovi <- -1
			case <-when(fine == true, richiestaScambioEsperto):
				ack_utentiEsperti <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioNuovoUomoDonna):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioNuovoUomoBambino):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioNuovoDonnaUomo):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioNuovoDonnaBambino):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioNuovoBambinoUomo):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioNuovoBambinoDonna):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioEspertoUomoDonna):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioEspertoUomoBambino):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioEspertoDonnaUomo):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioEspertoDonnaBambino):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioEspertoBambinoUomo):
				ack_utenti[idCorrente] <- -1
			case idCorrente = <-when(fine == true, accettazioneScambioEspertoBambinoDonna):
				ack_utenti[idCorrente] <- -1
						
			case <-terminaScambi:
				fmt.Printf("[Gestione scambi]: termino\n")
				done <- true
				return
		}

		if scambiEffettuati == maxScambi {
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

	go gestioneScambi()

	for i := 0; i < maxUtenti; i++ {
		go Utente(0, i)
	}

	for i := 0; i < maxUtenti; i++ { 
		<-done
	}

	terminaScambi <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
