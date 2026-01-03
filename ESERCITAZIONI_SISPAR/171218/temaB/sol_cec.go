package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxUtenti = 15 //max studenti
const maxPosti = 3 //max posti disponibili
var maxOrdinariNonAbbonati = 0
var maxStudentiNonAbbonati = 0
var maxOrdinariAbbonati = 0
var maxStudentiAbbonati = 0

const tipoOrdinarioNonAbbonato int = 0
const tipoStudenteNonAbbonato int = 1
const tipoOrdinarioAbbonato int = 2
const tipoStudenteAbbonato int = 3

var tipoStudente = [4]string{"OrdinarioNonAbbonato", "StudenteNonAbbonato", "OrdinarioAbbonato", "StudenteAbbonato"}

var done = make(chan bool)
var terminaBiglietteria = make(chan bool)
var terminaPiscina = make(chan bool)

var entrataBiglietteriaOrdinarioNonAbbonato = make(chan int, 100)
var entrataPiscinaOrdinarioNonAbbonato = make(chan int, 100)
var entrataBiglietteriaStudenteNonAbbonato = make(chan int, 100)
var entrataPiscinaStudenteNonAbbonato = make(chan int, 100)
var entrataBiglietteriaOrdinarioAbbonato = make(chan int, 100)
var entrataPiscinaOrdinarioAbbonato = make(chan int, 100)
var entrataBiglietteriaStudenteAbbonato = make(chan int, 100)
var entrataPiscinaStudenteAbbonato = make(chan int, 100)

var uscitaBiglietteriaOrdinarioNonAbbonato = make(chan int, 100)
var uscitaPiscinaOrdinarioNonAbbonato = make(chan int, 100)
var uscitaBiglietteriaStudenteNonAbbonato = make(chan int, 100)
var uscitaPiscinaStudenteNonAbbonato = make(chan int, 100)
var uscitaBiglietteriaOrdinarioAbbonato = make(chan int, 100)
var uscitaPiscinaOrdinarioAbbonato = make(chan int, 100)
var uscitaBiglietteriaStudenteAbbonato = make(chan int, 100)
var uscitaPiscinaStudenteAbbonato = make(chan int, 100)

var ack_ordinarioNonAbbonato = make(chan int)  
var ack_utenteNonAbbonato = make(chan int)  
var ack_ordinarioAbbonato = make(chan int)  
var ack_utenteAbbonato = make(chan int)  

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Studente(tipo int) {
	var tt int
	fmt.Printf("[Studente %s]: partenza! \n", tipoStudente[tipo])
	var ris int

	switch (tipo) {
		case 0:
			entrataBiglietteriaOrdinarioNonAbbonato <- 1
			ris = <-ack_ordinarioNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ritirata chiave! \n", tipoStudente[tipo])

			entrataPiscinaOrdinarioNonAbbonato <- 1
			ris = <-ack_ordinarioNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è entrato nella piscina!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaPiscinaOrdinarioNonAbbonato <- 1
			ris = <-ack_ordinarioNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è uscito dalla piscina! \n", tipoStudente[tipo])

			uscitaBiglietteriaOrdinarioNonAbbonato <- 1
			ris = <-ack_ordinarioNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: restituita chiave! \n", tipoStudente[tipo])
		case 1:
			entrataBiglietteriaStudenteNonAbbonato <- 1
			ris = <-ack_utenteNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ritirata chiave! \n", tipoStudente[tipo])

			entrataPiscinaStudenteNonAbbonato <- 1
			ris = <-ack_utenteNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è entrato nella piscina!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaPiscinaStudenteNonAbbonato <- 1
			ris = <-ack_utenteNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è uscito dalla piscina! \n", tipoStudente[tipo])

			uscitaBiglietteriaStudenteNonAbbonato <- 1
			ris = <-ack_utenteNonAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: restituita chiave! \n", tipoStudente[tipo])
		case 2:
			entrataBiglietteriaOrdinarioAbbonato <- 1
			ris = <-ack_ordinarioAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ritirata chiave! \n", tipoStudente[tipo])

			entrataPiscinaOrdinarioAbbonato <- 1
			ris = <-ack_ordinarioAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è entrato nella piscina!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaPiscinaOrdinarioAbbonato <- 1
			ris = <-ack_ordinarioAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è uscito dalla piscina! \n", tipoStudente[tipo])

			uscitaBiglietteriaOrdinarioAbbonato <- 1
			ris = <-ack_ordinarioAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: restituita chiave! \n", tipoStudente[tipo])
		case 3:
			entrataBiglietteriaStudenteAbbonato <- 1
			ris = <-ack_utenteAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ritirata chiave! \n", tipoStudente[tipo])

			entrataPiscinaStudenteAbbonato <- 1
			ris = <-ack_utenteAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è entrato nella piscina!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaPiscinaStudenteAbbonato <- 1
			ris = <-ack_utenteAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: è uscito dalla piscina! \n", tipoStudente[tipo])

			uscitaBiglietteriaStudenteAbbonato <- 1
			ris = <-ack_utenteAbbonato
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: restituita chiave! \n", tipoStudente[tipo])
	}

	done <- true
}

func biglietteria() {
	var datiBiglietti = false
	var fine bool = false
	var utentiDentro int = 0
	var utentiEntrati int = 0

	for {
		select {
			case <-when(fine == false && utentiDentro <= maxUtenti, entrataBiglietteriaOrdinarioNonAbbonato):
				utentiDentro++
				utentiEntrati++
				ack_ordinarioNonAbbonato <- 1
				fmt.Printf("[biglietti]: ordinario senza abbonamento entrato in biglietteria, ci sono %d utenti dentro\n", utentiDentro)
			case <-when(fine == false && utentiDentro <= maxUtenti, entrataBiglietteriaStudenteNonAbbonato):
				utentiDentro++
				utentiEntrati++
				ack_utenteNonAbbonato <- 1
				fmt.Printf("[biglietti]: utente con abbonamento entrato in biglietteria, ci sono %d utenti dentro\n", utentiDentro)
			case <-when(fine == false && utentiDentro <= maxUtenti, entrataBiglietteriaOrdinarioAbbonato):
				utentiDentro++
				utentiEntrati++
				ack_ordinarioAbbonato <- 1
				fmt.Printf("[biglietti]: ordinario con abbonamento entrato in biglietteria, ci sono %d utenti dentro\n", utentiDentro)
			case <-when(fine == false && utentiDentro <= maxUtenti, entrataBiglietteriaStudenteAbbonato):
				utentiDentro++
				utentiEntrati++
				ack_utenteAbbonato <- 1
				fmt.Printf("[biglietti]: utente senza abbonamento entrato in biglietteria, ci sono %d utenti dentro\n", utentiDentro)

			case <-when(fine == false && utentiDentro > 0, uscitaBiglietteriaOrdinarioNonAbbonato):
				utentiDentro--
				ack_ordinarioNonAbbonato <- 1
				fmt.Printf("[biglietti]: ordinario senza abbonamento uscito da biglietteria, ci sono %d utenti dentro\n", utentiDentro)
			case <-when(fine == false && utentiDentro > 0, uscitaBiglietteriaStudenteNonAbbonato):
				utentiDentro--
				ack_utenteNonAbbonato <- 1
				fmt.Printf("[biglietti]: utente senza abbonamento uscito da biglietteria, ci sono %d utenti dentro\n", utentiDentro)
			case <-when(fine == false && utentiDentro > 0, uscitaBiglietteriaOrdinarioAbbonato):
				utentiDentro--
				ack_ordinarioAbbonato <- 1
				fmt.Printf("[biglietti]: ordinario con abbonamento uscito da biglietteria, ci sono %d utenti dentro\n", utentiDentro)
			case <-when(fine == false && utentiDentro > 0, uscitaBiglietteriaStudenteAbbonato):
				utentiDentro--
				ack_utenteAbbonato <- 1
				fmt.Printf("[biglietti]: utente con abbonamento uscito da biglietteria, ci sono %d utenti dentro\n", utentiDentro)
			
			//terminazione
			case <-when(fine == true, entrataBiglietteriaOrdinarioNonAbbonato):
				ack_ordinarioNonAbbonato <- -1
			case <-when(fine == true, entrataBiglietteriaStudenteNonAbbonato):
				ack_utenteNonAbbonato <- -1
			case <-when(fine == true, entrataBiglietteriaOrdinarioAbbonato):
				ack_ordinarioAbbonato <- -1
			case <-when(fine == true, entrataBiglietteriaStudenteAbbonato):
				ack_utenteAbbonato <- -1
			case <-when(fine == true, uscitaBiglietteriaOrdinarioNonAbbonato):
				ack_ordinarioNonAbbonato <- -1
			case <-when(fine == true, uscitaBiglietteriaStudenteNonAbbonato):
				ack_utenteNonAbbonato <- -1
			case <-when(fine == true, uscitaBiglietteriaOrdinarioAbbonato):
				ack_ordinarioAbbonato <- -1
			case <-when(fine == true, uscitaBiglietteriaStudenteAbbonato):
				ack_utenteAbbonato <- -1
			
			case <-terminaBiglietteria:
				fmt.Printf("[biglietteria]: termino\n")
				done <- true
				return
		}

		if utentiEntrati == maxUtenti {
			datiBiglietti = true
		}

		if utentiDentro == 0 && datiBiglietti == true {
			fine = true
		}
	}
}

func piscina() {
	var numStudentiEntrati int = 0;
	var numOrdinariEntrati int = 0;
	var numStudentiDentro int = 0;
	var numOrdinariDentro int = 0;
	var postiLiberi int = maxPosti;
	var effettuatiControlliOrdinario bool = false;
	var effettuatiControlliStudente bool = false;
	var fine bool = false; // diventa true quando sono stati completati i controlli

	for {
		select {
			case <-when(fine == false && (numOrdinariDentro + numStudentiDentro <= maxPosti) && (postiLiberi > 0) &&
					((numStudentiDentro > numOrdinariDentro && len(entrataPiscinaOrdinarioAbbonato) == 0) || (numOrdinariDentro >= numStudentiDentro && (effettuatiControlliStudente == true || 
						(len(entrataPiscinaOrdinarioAbbonato) == 0 && len(entrataPiscinaStudenteNonAbbonato) == 0 && len(entrataPiscinaStudenteAbbonato) == 0)))), entrataPiscinaOrdinarioNonAbbonato):
				postiLiberi--
				numOrdinariDentro++
				numOrdinariEntrati++
				ack_ordinarioNonAbbonato <- 1
				fmt.Printf("[piscina]: controllato utente, ci sono %d studenti e %d ordinari entrati; tot posti liberi = %d\n", numStudentiDentro, numOrdinariDentro, postiLiberi)
			case <-when(fine == false && (numOrdinariDentro + numStudentiDentro <= maxPosti) && (postiLiberi > 0) &&
					((numStudentiDentro > numOrdinariDentro) || (numOrdinariDentro >= numStudentiDentro && (effettuatiControlliStudente == true || 
						(len(entrataPiscinaStudenteNonAbbonato) == 0 && len(entrataPiscinaStudenteAbbonato) == 0)))), entrataPiscinaOrdinarioAbbonato):
				postiLiberi--
				numOrdinariDentro++
				numOrdinariEntrati++
				ack_ordinarioAbbonato <- 1
				fmt.Printf("[piscina]: controllato utente, ci sono %d studenti e %d ordinari entrati; tot posti liberi = %d\n", numStudentiDentro, numOrdinariDentro, postiLiberi)
			case <-when(fine == false && (numOrdinariDentro + numStudentiDentro <= maxPosti) && (postiLiberi > 0) &&
					((numStudentiDentro <= numOrdinariDentro && len(entrataPiscinaStudenteAbbonato) == 0) || (numOrdinariDentro < numStudentiDentro && (effettuatiControlliOrdinario == true || 
						(len(entrataPiscinaOrdinarioAbbonato) == 0 && len(entrataPiscinaStudenteAbbonato) == 0 && len(entrataPiscinaOrdinarioNonAbbonato) == 0)))), entrataPiscinaStudenteNonAbbonato):
				postiLiberi--
				numStudentiDentro++
				numStudentiEntrati++
				ack_utenteNonAbbonato <- 1
				fmt.Printf("[piscina]: controllato utente, ci sono %d studenti e %d ordinari entrati; tot posti liberi = %d\n", numStudentiDentro, numOrdinariDentro, postiLiberi)
			case <-when(fine == false && (numOrdinariDentro + numStudentiDentro <= maxPosti) && (postiLiberi > 0) &&
					((numStudentiDentro <= numOrdinariDentro) || (numOrdinariDentro < numStudentiDentro && (effettuatiControlliOrdinario == true || 
						(len(entrataPiscinaOrdinarioAbbonato) == 0 && len(entrataPiscinaOrdinarioNonAbbonato) == 0)))), entrataPiscinaStudenteAbbonato):
				postiLiberi--
				numStudentiDentro++
				numStudentiEntrati++
				ack_utenteAbbonato <- 1
				fmt.Printf("[piscina]: controllato utente, ci sono %d studenti e %d ordinari entrati; tot posti liberi = %d\n", numStudentiDentro, numOrdinariDentro, postiLiberi)

			case <-when(fine == false && numOrdinariDentro > 0, uscitaPiscinaOrdinarioNonAbbonato):
				numOrdinariDentro--
				postiLiberi++
				ack_ordinarioNonAbbonato <- 1
				fmt.Printf("[piscina]: ordinario senza abbonamento è uscito dalla piscina, ci sono %d utenti rimasti dentro\n", postiLiberi)
			case <-when(fine == false && numStudentiDentro > 0, uscitaPiscinaStudenteNonAbbonato):
				numStudentiDentro--
				postiLiberi++
				ack_utenteNonAbbonato <- 1
				fmt.Printf("[piscina]: utente senza abbonamento è uscito dalla piscina, ci sono %d utenti rimasti dentro\n", postiLiberi)
			case <-when(fine == false && numOrdinariDentro > 0, uscitaPiscinaOrdinarioAbbonato):
				numOrdinariDentro--
				postiLiberi++
				ack_ordinarioAbbonato <- 1
				fmt.Printf("[piscina]: ordinario con abbonamento è uscito dalla piscina, ci sono %d utenti rimasti dentro\n", postiLiberi)
			case <-when(fine == false && numStudentiDentro > 0, uscitaPiscinaStudenteAbbonato):
				numStudentiDentro--
				postiLiberi++
				ack_utenteAbbonato <- 1
				fmt.Printf("[piscina]: utente con abbonamento è uscito dalla piscina, ci sono %d utenti rimasti dentro\n", postiLiberi)

			//terminazione
			case <-when(fine == true, entrataPiscinaOrdinarioNonAbbonato):
				ack_ordinarioNonAbbonato <- -1
			case <-when(fine == true, entrataPiscinaStudenteNonAbbonato):
				ack_utenteNonAbbonato <- -1
			case <-when(fine == true, entrataPiscinaOrdinarioAbbonato):
				ack_ordinarioAbbonato <- -1
			case <-when(fine == true, entrataPiscinaStudenteAbbonato):
				ack_utenteAbbonato <- -1
			case <-when(fine == true, uscitaPiscinaOrdinarioNonAbbonato):
				ack_ordinarioNonAbbonato <- -1
			case <-when(fine == true, uscitaPiscinaStudenteNonAbbonato):
				ack_utenteNonAbbonato <- -1
			case <-when(fine == true, uscitaPiscinaOrdinarioAbbonato):
				ack_ordinarioAbbonato <- -1
			case <-when(fine == true, uscitaPiscinaStudenteAbbonato):
				ack_utenteAbbonato <- -1
			
			case <-terminaPiscina:
				fmt.Printf("[piscina]: termino\n")
				done <- true
				return
		}

		if numOrdinariEntrati == maxOrdinariAbbonati + maxOrdinariNonAbbonati {
			effettuatiControlliOrdinario = true
		}

		if numStudentiEntrati == maxStudentiAbbonati + maxStudentiNonAbbonati {
			effettuatiControlliStudente = true
		}

		if effettuatiControlliOrdinario == true && effettuatiControlliStudente == true && postiLiberi == maxPosti {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	maxOrdinariNonAbbonati = rand.Intn(maxUtenti / 2) + 1
	maxOrdinariAbbonati = maxUtenti / 2 - maxOrdinariNonAbbonati
	maxStudentiNonAbbonati = rand.Intn(maxUtenti / 2) + 1
	maxStudentiAbbonati = maxUtenti - maxStudentiNonAbbonati - maxOrdinariAbbonati - maxOrdinariNonAbbonati
	fmt.Printf("[main] attivo programma\n")

	go biglietteria()
	go piscina()

	for i := 0; i < maxOrdinariNonAbbonati; i++ {
		go Studente(0)
	}

	for i := 0; i < maxStudentiNonAbbonati; i++ {
		go Studente(1)
	}

	for i := 0; i < maxOrdinariAbbonati; i++ {
		go Studente(2)
	}

	for i := 0; i < maxStudentiAbbonati; i++ {
		go Studente(3)
	}

	for i := 0; i < maxUtenti; i++ { //terminazione studenti
		<-done
	}

	terminaBiglietteria <- true
	<-done
	terminaPiscina <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
