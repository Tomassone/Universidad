package main

import (
	"fmt"
	"math/rand"
	"time"
)

const maxSpazzaneve = 15 
const maxCamion = 10
const maxSpargisale = 5
const capacitàMax = 10
const maxSilos = 5
const moltiplicatoreIterazioni = 1

var done = make(chan bool)
var terminaGestione = make(chan bool)

var entrataSpazzaneve = make(chan int, 100)
var entrataSpargisale = make(chan int, 100)
var entrataCamion = make(chan int, 100)

var uscitaSpazzaneve = make(chan int, 100)
var uscitaSpargisale = make(chan int, 100)
var uscitaCamion = make(chan int, 100)

//canali di ack
var ack_spazzaneve[maxSpazzaneve] chan int
var ack_spargisale[maxSpargisale] chan int
var ack_camion[maxCamion] chan int

func when(b bool, c chan int) chan int {
	if !b {
		return nil
	}
	return c
}

func Spazzaneve(id int) {
	fmt.Printf("[Spazzaneve]: partenza! \n")
	var ris int
	var tt int

	for i := 0; i < moltiplicatoreIterazioni; i++ {
		entrataSpazzaneve <- id
		ris = <- ack_spazzaneve[id]
		if ris == -1 {
			fmt.Printf("[Spazzaneve]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Spazzaneve %d]: entrato nell'area! \n", id)

		tt = (rand.Intn(10) + 1)
		time.Sleep(time.Duration(tt) * time.Second)

		uscitaSpazzaneve <- id
		ris = <- ack_spazzaneve[id]
		if ris == -1 {
			fmt.Printf("[Spazzaneve]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Spazzaneve %d]: uscito dall'area! \n", id)
	}

	done <- true
}

func Spargisale(id int) {
	fmt.Printf("[Spargisale]: partenza! \n")
	var ris int
	var tt int

	for i := 0; i < moltiplicatoreIterazioni; i++ {
		entrataSpargisale <- id
		ris = <- ack_spargisale[id]
		if ris == -1 {
			fmt.Printf("[Spargisale]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Spargisale %d]: entrato nell'area! \n", id)

		tt = (rand.Intn(10) + 1)
		time.Sleep(time.Duration(tt) * time.Second)

		uscitaSpargisale <- id
		ris = <- ack_spargisale[id]
		if ris == -1 {
			fmt.Printf("[Spargisale]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Spargisale %d]: uscito dall'area! \n", id)
	}

	done <- true
}

func Camion(id int) {
	fmt.Printf("[Camion]: partenza! \n")
	var ris int
	var tt int

	for i := 0; i < moltiplicatoreIterazioni; i++ { 
		entrataCamion <- id
		ris = <- ack_camion[id]
		if ris == -1 {
			fmt.Printf("[Camion]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Camion %d]: entrato nell'area! \n", id)

		tt = (rand.Intn(10) + 1)
		time.Sleep(time.Duration(tt) * time.Second)

		uscitaCamion <- id
		ris = <- ack_camion[id]
		if ris == -1 {
			fmt.Printf("[Camion]: termino! \n")
			done <- true
			return
		}
		fmt.Printf("[Camion %d]: uscito dall'area! \n", id)
	}

	done <- true
}

func gestioneArea() {
	var fine bool = false;
	var id int = 0;
	var spazzaneveDentro int = 0;
	var spargisaleDentro int = 0;
	var camionDentro int = 0;
	var spazzaneveEntrati int = 0;
	var spargisaleEntrati int = 0;
	var camionEntrati int = 0;
	var saleDisponibile int = maxSilos;
	var stopIngressi bool = false;

	for {
		select {
			case id = <-when(stopIngressi == false && fine == false && spazzaneveDentro + spargisaleDentro + camionDentro < capacitàMax, entrataSpazzaneve):
				spazzaneveDentro++
				spazzaneveEntrati++
				fmt.Printf("[Gestione Area]: spazzaneve entrato nell'area\n")
				ack_spazzaneve[id] <- 1
			case id = <-when(stopIngressi == false && fine == false && spazzaneveDentro + spargisaleDentro + camionDentro < capacitàMax &&
				len(entrataSpazzaneve) == 0, entrataCamion):
				camionDentro++
				camionEntrati++
				fmt.Printf("[Gestione Area]: camion entrato nell'area\n")
				ack_camion[id] <- 1
			case id = <-when(stopIngressi == false && fine == false && spazzaneveDentro + spargisaleDentro + camionDentro < capacitàMax &&
				len(entrataSpazzaneve) == 0 && len(entrataCamion) == 0 && saleDisponibile > 0, entrataSpargisale):
				spargisaleDentro++
				spargisaleEntrati++
				saleDisponibile--
				fmt.Printf("[Gestione Area]: spargisale entrato nell'area\n")
				ack_spargisale[id] <- 1

			case id = <-when(fine == false, uscitaSpazzaneve):
				spazzaneveDentro--
				fmt.Printf("[Gestione Area]: spazzaneve uscito dall'area\n")
				ack_spazzaneve[id] <- 1		
			case id = <-when(fine == false, uscitaCamion):
				camionDentro--
				saleDisponibile = maxSilos
				fmt.Printf("[Gestione Area]: camion uscito dall'area\n")
				ack_camion[id] <- 1
			case id = <-when(fine == false, uscitaSpargisale):
				spargisaleDentro--
				fmt.Printf("[Gestione Area]: spargisale uscito dall'area\n")
				ack_spargisale[id] <- 1					
			
			//terminazione
			case id = <-when(fine == true, entrataCamion):
				ack_camion[id] <- -1
			case id = <-when(fine == true, entrataSpazzaneve):
				ack_spazzaneve[id] <- -1
			case id = <-when(fine == true, entrataSpargisale):
				ack_spargisale[id] <- -1
			case id = <-when(fine == true, uscitaCamion):
				ack_camion[id] <- -1
			case id = <-when(fine == true, uscitaSpazzaneve):
				ack_spazzaneve[id] <- -1
			case id = <-when(fine == true, uscitaSpargisale):
				ack_spargisale[id] <- -1
			
			case <-terminaGestione:
				fmt.Printf("[Gestione Area]: termino\n")
				done <- true
				return
		}

		if spargisaleEntrati >= moltiplicatoreIterazioni * maxSpargisale && camionEntrati >= moltiplicatoreIterazioni * maxCamion && spazzaneveEntrati >= moltiplicatoreIterazioni * maxSpazzaneve {
			stopIngressi = true
		}

		if stopIngressi == true && spargisaleDentro == 0 && camionDentro == 0 && spazzaneveDentro == 0 {
			fine = true
		}
	}
}

func main() {
	rand.Seed(time.Now().Unix())
	fmt.Printf("[main] attivo programma\n")

	//inizializzazione canali di ack
	for i := 0; i < maxSpazzaneve; i++ {
		ack_spazzaneve[i] = make(chan int)
	}
	
	for i := 0; i < maxSpargisale; i++ {
		ack_spargisale[i] = make(chan int)
	}

	for i := 0; i < maxCamion; i++ {
		ack_camion[i] = make(chan int)
	}

	go gestioneArea()

	for i := 0; i < maxSpargisale; i++ {
		go Spargisale(i)
	}

	for i := 0; i < maxSpazzaneve; i++ {
		go Spazzaneve(i)
	}

	for i := 0; i < maxCamion; i++ {
		go Camion(i)
	}

	for i := 0; i < maxSpargisale; i++ { 
		<-done
	}

	for i := 0; i < maxSpazzaneve; i++ { 
		<-done
	}

	for i := 0; i < maxCamion; i++ { 
		<-done
	}

	terminaGestione <- true
	<-done
	fmt.Printf("[main] APPLICAZIONE TERMINATA \n")
}
