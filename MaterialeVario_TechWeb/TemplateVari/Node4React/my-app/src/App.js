import React, { Component} from 'react';
import './App.css';
import SezioneCredito from './SezioneCredito';
import SezioneSlot from './SezioneSlot';
import SezioneRisultati from './SezioneRisultati';


class App extends React.Component {

  constructor() {
    super();
    this.state = {  
      mappa: null,
      punti: [],
      lista : [],
      finalizza: false,
      valore :0,
      fatto:false,
    };
    this.initializeConfig=this.initializeConfig.bind(this);
    this.buildGriglia=this.buildGriglia.bind(this);
    this.aggiornaGriglia=this.aggiornaGriglia.bind(this);
    this.resetta = this.resetta.bind(this);
    this.aggiorna= this.aggiorna.bind(this);
  }

  initializeConfig() {
      this.setState( this.buildGriglia());
  }

  resetta() {
    this.setState({
      mappa: null,
      punti: [],
      lista : [],
      finalizza: false,
      valore :0,
      fatto:0,
      }, this.aggiorna);
   }
  
   aggiorna(){
    const val = parseInt(document.getElementById("incremento").value);

    if(isNaN(val)){
        alert("Devi inserire un numero naturale");
        this.resetta();
        return;
      }

    this.state.valore = this.state.valore + val;
    this.setState({ valore : this.state.valore});
  
  };

  //creo 3 caselle
  buildGriglia() {
    let mappa = [];

    //FACCIO 3 CASELLE DI COLORE VARIABILE
    if(this.state.valore>5){
    this.state.valore = this.state.valore - 5;
    }else{
      alert("Devi avere almeno 1 moneta");
      this.resetta();
      return;
    }
     
        for (let j = 0; j < 3; j++) {
            mappa.push( { value: "", //valore casuale T o niente
                         coords: {  //coordinate
                         x: j  } })
        }

    this.state.finalizza=true;
    //RIEMPIO LE CASELLE DI UNA VOCALE DOPO

    this.setState({ valore : this.state.valore , mappa: mappa , finalizza : this.state.finalizza});
    
    setTimeout(() => {
      this.aggiornaGriglia();
    }, 2000);
    

  }

   //creo la mappa e la aggiorno
   aggiornaGriglia() {
    //creo copia della mappa
    let newmappa=[];
    const vocali = ['a','e','i','o','u']; 
    let index;
    let newLista = JSON.parse(JSON.stringify(this.state.lista));
    let newPunti = JSON.parse(JSON.stringify(this.state.punti));
    let valori; 


    for(let i = 0; i < 3; i++){
      index = Math.floor(Math.random() * 4);
      if(vocali[index]=='a' || vocali[index]=='e'){
       newmappa.push({
        value: vocali[index],
        coords: {x : i},
        color: "green",
      });
     }else{
      newmappa.push({
        value: vocali[index],
        coords: {x : i},
        color: "blue",
      });
     }
    }

    valori = newmappa[0].value + newmappa[1].value + newmappa[2].value;

    console.log(valori);

    
    
    console.log("valori " + newmappa[0].value + newmappa[1].value + newmappa[2].value);


    let trovato = 0;
    //SE SONO UGUALI LE PRIME DUE
    if(newmappa[0].value === newmappa[1].value) 
      trovato++;
    //SE SONO UGUALI LE ULTIME DUE
     if(newmappa[1].value === newmappa[2].value)
      trovato++;
    //SE SONO UGUALI LA PRIMA E L'ULTIMA
    if(newmappa[0].value === newmappa[2].value)
      trovato++;

    console.log(trovato);

    if(trovato === 1){
      this.state.valore = this.state.valore + 15;
      newLista.push({
        value: valori + " +15",
      });
    }else if(trovato === 3){
      this.state.valore = this.state.valore + 50;
      newLista.push({
        value: valori + " +50",
      });
    }else{
      newLista.push({
        value: valori + " +0",
      });

    }

    this.state.fatto=true;
   
    //risetto il lago
    this.setState({ mappa: newmappa, valore: this.state.valore , lista : newLista, punti: newPunti, fatto: this.state.fatto});

  }


  
// {this.state.mappa && <SezioneRisultati estratta={this.state.estratto} vinto={this.state.vinto} estrai={this.estrai} inizializza={this.resetta} fatto={this.state.fatto}/>}

  render() {
    return (
        <div className="App">
        <SezioneCredito aggiorna={this.aggiorna} valore={this.state.valore} />
        {<SezioneSlot  avvia={this.initializeConfig} mappa={this.state.mappa} finalizza={this.state.finalizza} valore={this.state.valore}/>}
        { <SezioneRisultati punteggio={this.state.punti} lista={this.state.lista} fatto={this.state.fatto} />}
      </div>
    )
  }
}


export default App;
