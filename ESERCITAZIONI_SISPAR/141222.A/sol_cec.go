package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxClienti = 15 
const maxFrigorifero = 5
const maxTortellini = 10
const maxSfogline = 20

var done = make(chan bool)
var terminaLaboratorio = make(chan bool)

var richiestePrenotazioni = make(chan int, 100)
var richiesteRitiro = make(chan int, 100)
var richiesteDeposito = make(chan int, 100)

//canali di ack
var ack_clienti = make(chan int) 
var ack_sfogline = make(chan int)

var idCounter int = 0

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Cliente() {
	var id int
	fmt.Printf("[Cliente]: partenza! \n")
	var ris int

	richiestePrenotazioni <- 1
	ris = <-ack_clienti
	id = ris //ottengo in risposta l'id della richiesta effettuata
	if ris == -1 {
		fmt.Printf("[Cliente]: termino! \n")
		done <- true
		return
	}
	fmt.Printf("[Cliente %d]: effettuata prenotazione! \n", id)

	richiesteRitiro <- id
	ris = <-ack_clienti
	if ris == -1 {
		fmt.Printf("[Cliente]: termino! \n")
		done <- true
		return
	}
	fmt.Printf("[Cliente %d]: ritirata prenotazione! \n", id)

	done <- true
}

func Sfoglina() {
	fmt.Printf("[Sfoglina]: partenza! \n")
	var ris int

	for {
		richiesteDeposito <- 1
		ris = <-ack_sfogline
		if ris == -1 {
			fmt.Printf("[Sfoglina]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Sfoglina]: effettuato deposito! \n")
	}

	done <- true
}

func gestioneLaboratorio() {
	var prenotazioniEffettuate int = 0;
	var ritiriEffettuati int = 0;
	var depositiEffettuati int = 0;
	var tortelliniDisponibili int = 0;
	var isIdUsed [maxClienti]bool;
	var fine bool = false;
	var idIndex int = 0;
	var tt int = 0;

	for {
		select {
			case <-when(fine == false && ritiriEffettuati < maxTortellini, richiestePrenotazioni):
				if (prenotazioniEffettuate >= maxTortellini) {
					ack_clienti <- -1
					fmt.Printf("[Gestione laboratorio]: non ammessa prenotazione tortellino, ci sono %d tortellini disponibili e %d ritiri effettuati; tot depositi effettuati = %d\n", tortelliniDisponibili, ritiriEffettuati, depositiEffettuati)
				} else {
					prenotazioniEffettuate++
					tt = (rand.Intn(2) + 1)
					time.Sleep(time.Duration(tt) * time.Second)
					ack_clienti <- idCounter
					idCounter++
					fmt.Printf("[Gestione laboratorio]: prenotato tortellino, ci sono %d tortellini disponibili e %d ritiri effettuati; tot depositi effettuati = %d\n", tortelliniDisponibili, ritiriEffettuati, depositiEffettuati)
				}
			case idIndex = <-when(fine == false && ritiriEffettuati < maxTortellini && tortelliniDisponibili > 0 && len(richiestePrenotazioni) == 0, richiesteRitiro):
				tortelliniDisponibili--
				ritiriEffettuati++
				tt = (rand.Intn(2) + 1)
				time.Sleep(time.Duration(tt) * time.Second)
				if isIdUsed[idIndex] == false {
					ack_clienti <- 1
					isIdUsed[idIndex] = true
					fmt.Printf("[Gestione laboratorio]: ritirato tortellino, ci sono %d tortellini disponibili e %d ritiri effettuati; tot depositi effettuati = %d\n", tortelliniDisponibili, ritiriEffettuati, depositiEffettuati)	
				} else {
					ack_clienti <- -1
					fmt.Printf("[Gestione laboratorio]: non ammesso ritiro tortellino, ci sono %d tortellini disponibili e %d ritiri effettuati; tot depositi effettuati = %d\n", tortelliniDisponibili, ritiriEffettuati, depositiEffettuati)
				}
			case <-when(fine == false && tortelliniDisponibili < maxFrigorifero && 
				(len(richiestePrenotazioni) == 0 && (len(richiesteRitiro) == 0 || tortelliniDisponibili == 0)), richiesteDeposito):
				depositiEffettuati++
				tortelliniDisponibili++
				tt = (rand.Intn(2) + 1)
				time.Sleep(time.Duration(tt) * time.Second)
				ack_sfogline <- 1
				fmt.Printf("[Gestione laboratorio]: aggiunto tortellino, ci sono %d tortellini disponibili e %d ritiri effettuati; tot depositi effettuati = %d\n", tortelliniDisponibili, ritiriEffettuati, depositiEffettuati)

			//terminazione
			case <-when(fine == true, richiesteDeposito):
				ack_sfogline <- -1
			case <-when(fine == true, richiestePrenotazioni):
				ack_clienti <- -1
			case <-when(fine == true, richiesteRitiro):
				ack_clienti <- -1
			
			case <-terminaLaboratorio:
				fmt.Printf("[Gestione Laboratorio]: termino\n")
				done <- true
				return
		}

		if ritiriEffettuati == maxTortellini {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	fmt.Printf("[main] attivo programma\n")

	go gestioneLaboratorio()

	for i := 0; i < maxSfogline; i++ {
		go Sfoglina()
	}

	for i := 0; i < maxClienti; i++ {
		go Cliente()
	}

	for i := 0; i < maxSfogline; i++ { 
		<-done
	}

	for i := 0; i < maxClienti; i++ { 
		<-done
	}

	terminaLaboratorio <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
