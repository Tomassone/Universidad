package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxStudenti = 15 //max studenti
const maxPosti = 3 //max posti disponibili
var maxTriennaliNonLaureandi = 0
var maxMagistraliNonLaureandi = 0
var maxTriennaliLaureandi = 0
var maxMagistraliLaureandi = 0

const tipoTriennaleNonLaureando int = 0
const tipoMagistraleNonLaureando int = 1
const tipoTriennaleLaureando int = 2
const tipoMagistraleLaureando int = 3

var tipoStudente = [4]string{"TriennaleNonLaureando", "MagistraleNonLaureando", "TriennaleLaureando", "MagistraleLaureando"}

var done = make(chan bool)
var terminaPortineria = make(chan bool)
var terminaControlloBadge = make(chan bool)

var entrataPortineriaTriennaleNonLaureando = make(chan int, 100)
var entrataControlloBadgeTriennaleNonLaureando = make(chan int, 100)
var entrataPortineriaMagistraleNonLaureando = make(chan int, 100)
var entrataControlloBadgeMagistraleNonLaureando = make(chan int, 100)
var entrataPortineriaTriennaleLaureando = make(chan int, 100)
var entrataControlloBadgeTriennaleLaureando = make(chan int, 100)
var entrataPortineriaMagistraleLaureando = make(chan int, 100)
var entrataControlloBadgeMagistraleLaureando = make(chan int, 100)

var uscitaPortineriaTriennaleNonLaureando = make(chan int, 100)
var uscitaControlloBadgeTriennaleNonLaureando = make(chan int, 100)
var uscitaPortineriaMagistraleNonLaureando = make(chan int, 100)
var uscitaControlloBadgeMagistraleNonLaureando = make(chan int, 100)
var uscitaPortineriaTriennaleLaureando = make(chan int, 100)
var uscitaControlloBadgeTriennaleLaureando = make(chan int, 100)
var uscitaPortineriaMagistraleLaureando = make(chan int, 100)
var uscitaControlloBadgeMagistraleLaureando = make(chan int, 100)

var ack_triennaleNonLaureando = make(chan int)  
var ack_magistraleNonLaureando = make(chan int)  
var ack_triennaleLaureando = make(chan int)  
var ack_magistraleLaureando = make(chan int)  

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
			entrataPortineriaTriennaleNonLaureando <- 1
			ris = <-ack_triennaleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: mostrato documento! \n", tipoStudente[tipo])

			entrataControlloBadgeTriennaleNonLaureando <- 1
			ris = <-ack_triennaleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: effettuato controllo! \n", tipoStudente[tipo])
			fmt.Printf("[Studente %s]: è entrato!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaControlloBadgeTriennaleNonLaureando <- 1
			ris = <-ack_triennaleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: uscito da biblioteca! \n", tipoStudente[tipo])

			uscitaPortineriaTriennaleNonLaureando <- 1
			ris = <-ack_triennaleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ripreso documento! \n", tipoStudente[tipo])
		case 1:
			entrataPortineriaMagistraleNonLaureando <- 1
			ris = <-ack_magistraleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: mostrato documento! \n", tipoStudente[tipo])

			entrataControlloBadgeMagistraleNonLaureando <- 1
			ris = <-ack_magistraleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: effettuato controllo! \n", tipoStudente[tipo])
			fmt.Printf("[Studente %s]: è entrato!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaControlloBadgeMagistraleNonLaureando <- 1
			ris = <-ack_magistraleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: uscito da biblioteca! \n", tipoStudente[tipo])

			uscitaPortineriaMagistraleNonLaureando <- 1
			ris = <-ack_magistraleNonLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ripreso documento! \n", tipoStudente[tipo])
		case 2:
			entrataPortineriaTriennaleLaureando <- 1
			ris = <-ack_triennaleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: mostrato documento! \n", tipoStudente[tipo])

			entrataControlloBadgeTriennaleLaureando <- 1
			ris = <-ack_triennaleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: effettuato controllo! \n", tipoStudente[tipo])
			fmt.Printf("[Studente %s]: è entrato!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaControlloBadgeTriennaleLaureando <- 1
			ris = <-ack_triennaleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: uscito da biblioteca! \n", tipoStudente[tipo])

			uscitaPortineriaTriennaleLaureando <- 1
			ris = <-ack_triennaleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ripreso documento! \n", tipoStudente[tipo])
		case 3:
			entrataPortineriaMagistraleLaureando <- 1
			ris = <-ack_magistraleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: mostrato documento! \n", tipoStudente[tipo])

			entrataControlloBadgeMagistraleLaureando <- 1
			ris = <-ack_magistraleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: effettuato controllo! \n", tipoStudente[tipo])
			fmt.Printf("[Studente %s]: è entrato!\n", tipoStudente[tipo])

			tt = (rand.Intn(10) + 1)
			time.Sleep(time.Duration(tt) * time.Second) //tempo nella biblioteca

			uscitaControlloBadgeMagistraleLaureando <- 1
			ris = <-ack_magistraleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: uscito da biblioteca! \n", tipoStudente[tipo])

			uscitaPortineriaMagistraleLaureando <- 1
			ris = <-ack_magistraleLaureando
			if ris == -1 {
				fmt.Printf("[Studente %s]: termino! \n", tipoStudente[tipo])
				done <- true
				return
			}
			fmt.Printf("[Studente %s]: ripreso documento! \n", tipoStudente[tipo])
	}

	done <- true
}

func portineria() {
	var datiBiglietti = false
	var fine bool = false
	var documentiDati int = 0

	for {
		select {
			case <-when(fine == false && documentiDati < maxStudenti, entrataPortineriaTriennaleNonLaureando):
				documentiDati++
				ack_triennaleNonLaureando <- 1
				fmt.Printf("[biglietti]: triennale non laureando entrato in portineria, ci sono %d documenti dati\n", documentiDati)
			case <-when(fine == false && documentiDati < maxStudenti, entrataPortineriaMagistraleNonLaureando):
				documentiDati++
				ack_magistraleNonLaureando <- 1
				fmt.Printf("[biglietti]: magistrale laureando entrato in portineria, ci sono %d documenti dati\n", documentiDati)
			case <-when(fine == false && documentiDati < maxStudenti, entrataPortineriaTriennaleLaureando):
				documentiDati++
				ack_triennaleLaureando <- 1
				fmt.Printf("[biglietti]: triennale laureando entrato in portineria, ci sono %d documenti dati\n", documentiDati)
			case <-when(fine == false && documentiDati < maxStudenti, entrataPortineriaMagistraleLaureando):
				documentiDati++
				ack_magistraleLaureando <- 1
				fmt.Printf("[biglietti]: magistrale non laureando entrato in portineria, ci sono %d documenti dati\n", documentiDati)

			case <-when(fine == false && documentiDati > 0, uscitaPortineriaTriennaleNonLaureando):
				documentiDati--
				ack_triennaleNonLaureando <- 1
				fmt.Printf("[biglietti]: triennale non laureando uscito da portineria, ci sono %d documenti dati\n", documentiDati)
			case <-when(fine == false && documentiDati > 0, uscitaPortineriaMagistraleNonLaureando):
				documentiDati--
				ack_magistraleNonLaureando <- 1
				fmt.Printf("[biglietti]: magistrale non laureando uscito da portineria, ci sono %d documenti dati\n", documentiDati)
			case <-when(fine == false && documentiDati > 0, uscitaPortineriaTriennaleLaureando):
				documentiDati--
				ack_triennaleLaureando <- 1
				fmt.Printf("[biglietti]: triennale laureando uscito da portineria, ci sono %d documenti dati\n", documentiDati)
			case <-when(fine == false && documentiDati > 0, uscitaPortineriaMagistraleLaureando):
				documentiDati--
				ack_magistraleLaureando <- 1
				fmt.Printf("[biglietti]: magistrale laureando uscito da portineria, ci sono %d documenti dati\n", documentiDati)
			
			//terminazione
			case <-when(fine == true, entrataPortineriaTriennaleNonLaureando):
				ack_triennaleNonLaureando <- -1
			case <-when(fine == true, entrataPortineriaMagistraleNonLaureando):
				ack_magistraleNonLaureando <- -1
			case <-when(fine == true, entrataPortineriaTriennaleLaureando):
				ack_triennaleLaureando <- -1
			case <-when(fine == true, entrataPortineriaMagistraleLaureando):
				ack_magistraleLaureando <- -1
			case <-when(fine == true, uscitaPortineriaTriennaleNonLaureando):
				ack_triennaleNonLaureando <- -1
			case <-when(fine == true, uscitaPortineriaMagistraleNonLaureando):
				ack_magistraleNonLaureando <- -1
			case <-when(fine == true, uscitaPortineriaTriennaleLaureando):
				ack_triennaleLaureando <- -1
			case <-when(fine == true, uscitaPortineriaMagistraleLaureando):
				ack_magistraleLaureando <- -1
			
			case <-terminaPortineria:
				fmt.Printf("[biglietteria]: termino\n")
				done <- true
				return
		}

		if documentiDati == maxStudenti {
			datiBiglietti = true
		}

		if documentiDati == 0 && datiBiglietti == true {
			fine = true
		}
	}
}

func controlloBadge() {
	var numMagistraliEntrati int = 0;
	var numTriennaliEntrati int = 0;
	var numMagistraliDentro int = 0;
	var numTriennaliDentro int = 0;
	var postiLiberi int = maxPosti;
	var effettuatiControlliTriennale bool = false;
	var effettuatiControlliMagistrale bool = false;
	var fine bool = false; // diventa true quando sono stati completati i controlli

	for {
		select {
			case <-when(fine == false && (numTriennaliDentro + numMagistraliDentro < maxStudenti) && (postiLiberi > 0) &&
					((numMagistraliDentro > numTriennaliDentro && len(entrataControlloBadgeTriennaleLaureando) == 0) || (numTriennaliDentro >= numMagistraliDentro && (effettuatiControlliMagistrale == true || 
						(len(entrataControlloBadgeTriennaleLaureando) == 0 && len(entrataControlloBadgeMagistraleNonLaureando) == 0 && len(entrataControlloBadgeMagistraleLaureando) == 0)))), entrataControlloBadgeTriennaleNonLaureando):
				postiLiberi--
				numTriennaliDentro++
				numTriennaliEntrati++
				ack_triennaleNonLaureando <- 1
				fmt.Printf("[controllo badge]: controllato studente, ci sono %d magistrali e %d triennali entrati; tot posti liberi = %d\n", numMagistraliDentro, numTriennaliDentro, postiLiberi)
			case <-when(fine == false && (numTriennaliDentro + numMagistraliDentro < maxStudenti) && (postiLiberi > 0) &&
					((numMagistraliDentro > numTriennaliDentro) || (numTriennaliDentro >= numMagistraliDentro && (effettuatiControlliMagistrale == true || 
						(len(entrataControlloBadgeMagistraleNonLaureando) == 0 && len(entrataControlloBadgeMagistraleLaureando) == 0)))), entrataControlloBadgeTriennaleLaureando):
				postiLiberi--
				numTriennaliDentro++
				numTriennaliEntrati++
				ack_triennaleLaureando <- 1
				fmt.Printf("[controllo badge]: controllato studente, ci sono %d magistrali e %d triennali entrati; tot posti liberi = %d\n", numMagistraliDentro, numTriennaliDentro, postiLiberi)
			case <-when(fine == false && (numTriennaliDentro + numMagistraliDentro < maxStudenti) && (postiLiberi > 0) &&
					((numMagistraliDentro <= numTriennaliDentro && len(entrataControlloBadgeMagistraleLaureando) == 0) || (numTriennaliDentro < numMagistraliDentro && (effettuatiControlliTriennale == true || 
						(len(entrataControlloBadgeTriennaleLaureando) == 0 && len(entrataControlloBadgeMagistraleLaureando) == 0 && len(entrataControlloBadgeTriennaleNonLaureando) == 0)))), entrataControlloBadgeMagistraleNonLaureando):
				postiLiberi--
				numMagistraliDentro++
				numMagistraliEntrati++
				ack_magistraleNonLaureando <- 1
				fmt.Printf("[controllo badge]: controllato studente, ci sono %d magistrali e %d triennali entrati; tot posti liberi = %d\n", numMagistraliDentro, numTriennaliDentro, postiLiberi)
			case <-when(fine == false && (numTriennaliDentro + numMagistraliDentro < maxStudenti) && (postiLiberi > 0) &&
					((numMagistraliDentro <= numTriennaliDentro) || (numTriennaliDentro < numMagistraliDentro && (effettuatiControlliTriennale == true || 
						(len(entrataControlloBadgeTriennaleLaureando) == 0 && len(entrataControlloBadgeTriennaleNonLaureando) == 0)))), entrataControlloBadgeMagistraleLaureando):
				postiLiberi--
				numMagistraliDentro++
				numMagistraliEntrati++
				ack_magistraleLaureando <- 1
				fmt.Printf("[controllo badge]: controllato studente, ci sono %d magistrali e %d triennali entrati; tot posti liberi = %d\n", numMagistraliDentro, numTriennaliDentro, postiLiberi)

			case <-when(fine == false && numTriennaliDentro > 0, uscitaControlloBadgeTriennaleNonLaureando):
				numTriennaliDentro--
				postiLiberi++
				ack_triennaleNonLaureando <- 1
				fmt.Printf("[controllo badge]: triennale non laureando uscito da biblioteca, ci sono %d studenti rimasti dentro\n", postiLiberi)
			case <-when(fine == false && numMagistraliDentro > 0, uscitaControlloBadgeMagistraleNonLaureando):
				numMagistraliDentro--
				postiLiberi++
				ack_magistraleNonLaureando <- 1
				fmt.Printf("[controllo badge]: magistrale non laureando uscito da biblioteca, ci sono %d studenti rimasti dentro\n", postiLiberi)
			case <-when(fine == false && numTriennaliDentro > 0, uscitaControlloBadgeTriennaleLaureando):
				numTriennaliDentro--
				postiLiberi++
				ack_triennaleLaureando <- 1
				fmt.Printf("[controllo badge]: triennale laureando uscito da biblioteca, ci sono %d studenti rimasti dentro\n", postiLiberi)
			case <-when(fine == false && numMagistraliDentro > 0, uscitaControlloBadgeMagistraleLaureando):
				numMagistraliDentro--
				postiLiberi++
				ack_magistraleLaureando <- 1
				fmt.Printf("[controllo badge]: magistrale laureando uscito da biblioteca, ci sono %d studenti rimasti dentro\n", postiLiberi)

			//terminazione
			case <-when(fine == true, entrataControlloBadgeTriennaleNonLaureando):
				ack_triennaleNonLaureando <- -1
			case <-when(fine == true, entrataControlloBadgeMagistraleNonLaureando):
				ack_magistraleNonLaureando <- -1
			case <-when(fine == true, entrataControlloBadgeTriennaleLaureando):
				ack_triennaleLaureando <- -1
			case <-when(fine == true, entrataControlloBadgeMagistraleLaureando):
				ack_magistraleLaureando <- -1
			case <-when(fine == true, uscitaControlloBadgeTriennaleNonLaureando):
				ack_triennaleNonLaureando <- -1
			case <-when(fine == true, uscitaControlloBadgeMagistraleNonLaureando):
				ack_magistraleNonLaureando <- -1
			case <-when(fine == true, uscitaControlloBadgeTriennaleLaureando):
				ack_triennaleLaureando <- -1
			case <-when(fine == true, uscitaControlloBadgeMagistraleLaureando):
				ack_magistraleLaureando <- -1
			
			case <-terminaControlloBadge:
				fmt.Printf("[controllo badge]: termino\n")
				done <- true
				return
		}

		if numTriennaliEntrati == maxTriennaliLaureandi + maxTriennaliNonLaureandi {
			effettuatiControlliTriennale = true
		}

		if numMagistraliEntrati == maxMagistraliLaureandi + maxMagistraliNonLaureandi {
			effettuatiControlliMagistrale = true
		}

		if effettuatiControlliTriennale == true && effettuatiControlliMagistrale == true && postiLiberi == maxPosti {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	maxTriennaliNonLaureandi = rand.Intn(maxStudenti / 2) + 1
	maxTriennaliLaureandi = maxStudenti / 2 - maxTriennaliNonLaureandi
	maxMagistraliNonLaureandi = rand.Intn(maxStudenti / 2) + 1
	maxMagistraliLaureandi = maxStudenti - maxMagistraliNonLaureandi - maxTriennaliLaureandi - maxTriennaliNonLaureandi
	fmt.Printf("[main] attivo programma\n")

	go portineria()
	go controlloBadge()

	for i := 0; i < maxTriennaliNonLaureandi; i++ {
		go Studente(0)
	}

	for i := 0; i < maxMagistraliNonLaureandi; i++ {
		go Studente(1)
	}

	for i := 0; i < maxTriennaliLaureandi; i++ {
		go Studente(2)
	}

	for i := 0; i < maxMagistraliLaureandi; i++ {
		go Studente(3)
	}

	for i := 0; i < maxStudenti; i++ { //terminazione studenti
		<-done
	}

	terminaPortineria <- true
	<-done
	terminaControlloBadge <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
