"use strict";

class App extends React.Component {
/*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Configurazione", "Gioco", "Credito"];
        
        // Inizializzazione dello state
        this.state = {
            credito: 0,
            giocatore: [],
            isPlaying: false,
            banco: [],
            puntata: 0,
            playerTurn: true,
            creditoInit: 0,
            isEnded: false
        };

        // Binding delle funzioni
        this.aumentaCredito = this.aumentaCredito.bind(this);
        this.estrai = this.estrai.bind(this);
        this.pescaGiocatore = this.pescaGiocatore.bind(this);
        this.iniziaGioco = this.iniziaGioco.bind(this);
        this.stop = this.stop.bind(this);
        this.reset = this.reset.bind(this);
    }

/*** Funzioni ***********************************************************************/
    // Aumenta il credito
    aumentaCredito(){
        this.setState({
                credito: parseInt(document.getElementById("soldi").value, 10),
                creditoInit: parseInt(document.getElementById("soldi").value, 10)   
            })
    }

    // Estrazione della carta
    estrai(){
        let carte = [1, 2, 3, 4, 5, 6, 7, 0.5, 0.5, 0.5];
        return carte[Math.floor(Math.random()*10)]
    }

    // Funzione di pesca per il giocatore
    pescaGiocatore(){
        let gioc = this.state.giocatore;
        gioc.push(this.estrai());
        this.setState({giocatore: gioc})
        if (this.state.giocatore.reduce((acc, value) => (acc += value), 0) >= 7.5){
            this.stop();
        }
    }

    // Funzione inizio gioco
    iniziaGioco(){
        let puntata = parseInt(document.getElementById("puntata").value);
        if (!(puntata >= 0)){
            alert("Inserire una puntata per giocare!");
        }
        else{
            this.setState({
                    playerTurn: true,
                    isPlaying: true,
                    giocatore: [],
                    banco: [],
                    puntata: puntata
                })
            this.pescaGiocatore();
        }

    }

    // Stop turno giocatore
    stop(){
        this.setState({playerTurn: false})
        var interval = setInterval(() => {
            var banco = this.state.banco;
            banco.push(this.estrai());
            this.setState({banco: banco});
            let puntiBanco = banco.reduce((acc, value) => (acc += value), 0);
            if (puntiBanco >= 6 || this.state.giocatore.reduce((acc, value) => (acc += value), 0) > 7.5){
                clearInterval(interval);
                let puntiGiocatore = this.state.giocatore.reduce((acc, value) => (acc += value), 0);
                if (puntiGiocatore > 7.5 || (puntiGiocatore < puntiBanco && puntiBanco < 7.5)){
                    this.setState({credito: this.state.credito - this.state.puntata});
                }
                else{
                    this.setState({credito: this.state.credito + this.state.puntata});
                }
                this.setState({isEnded:true})
            }
        }, 3000);
    }

    // Reset del gioco, altra mano
    reset () {
        this.setState({
            giocatore: [],
            banco: [],
            puntata: 0,
            playerTurn: true,
            isEnded: false,
            isPlaying: false
        })
    }

/*** Render *************************************************************************/
    render() {
        return (
            <div>
                <Section1
                    sectionName = {this.sectionNames[0]}
                    aumentaCredito = {this.aumentaCredito}
                    credito = {this.state.credito}
                />
                <Section2
                    sectionName = {this.sectionNames[1]}
                    giocatore = {this.state.giocatore}
                    pescaGiocatore = {this.pescaGiocatore}
                    credito = {this.state.credito}
                    isPlaying = {this.state.isPlaying}
                    iniziaGioco = {this.iniziaGioco}
                    playerTurn = {this.state.playerTurn}
                    banco = {this.state.banco}
                    stop = {this.stop}
                    reset = {this.reset}
                    isEnded = {this.state.isEnded}
                />
                <Section3
                    sectionName = {this.sectionNames[2]}
                    credito = {this.state.credito}
                    creditoInit = {this.state.creditoInit}
                />
            </div>
        );
    }
}
