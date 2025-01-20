"use strict";

class App extends React.Component {
/*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Credito", "Slot machine", "Risultati"];
        this.vowels = ["a", "e", "i", "o", "u"];
        // Inizializzazione dello state
        this.state = {
            credito: 0,
            canPlay: false,
            risultati: [],
            display: ["-", "-", "-"],   
        };

        // Binding delle funzioni
        this.aggiornaCredito = this.aggiornaCredito.bind(this);
        this.estrazione = this.estrazione.bind(this);
        this.controlloVincita = this.controlloVincita.bind(this);
        this.updateCanPlay = this.updateCanPlay.bind(this);
    }

/*** Funzioni ***********************************************************************/
    // Aggiorna il credito
    aggiornaCredito() {
        let Tcredito = parseInt(document.getElementById("TCredito").value);
        this.setState({credito: this.state.credito + Tcredito}, () => this.updateCanPlay());
        document.getElementById("TCredito").value = 0;
    }

    // Estrae 3 letter e le dispone
    estrazione() {
        setTimeout(() => {
            var disp = Array.from({length: 3}, () => {return this.vowels[Math.floor(Math.random()*5)]});
            this.setState({
                credito: this.state.credito -5,
                display: disp}, () => this.controlloVincita());}, 
        2000)
    }

    // Controllo vincita
    controlloVincita(){
        let disp = this.state.display;
        let result = disp.filter((value, index) => disp.indexOf(value) != index).length + 1;
        let incremento = 0;
        if (result == 2){
            incremento = 15;
        }
        else if (result == 3){
            incremento = 50;
        }
        this.setState({
            credito: this.state.credito + incremento,
            risultati: [...this.state.risultati, [disp, incremento]]
        }, () => this.updateCanPlay())
        this.updateCanPlay();
    }

    // Controlla che l'utente abbia almeno 5 monete
    updateCanPlay(){
        let atLeast5 = (this.state.credito >= 5);
        this.setState({canPlay: atLeast5});
    }

/*** Render *************************************************************************/
    render() {
        return (
            <div>
                <Section1
                    sectionName = {this.sectionNames[0]}
                    aggiornaCredito = {this.aggiornaCredito}
                />
                <Section2
                    sectionName = {this.sectionNames[1]}
                    credito = {this.state.credito}
                    estrazione = {this.estrazione}
                    canPlay = {this.state.canPlay}
                    display = {this.state.display}
                />
                <Section3
                    sectionName = {this.sectionNames[2]}
                    risultati = {this.state.risultati}
                />
            </div>
        );
    }
}
