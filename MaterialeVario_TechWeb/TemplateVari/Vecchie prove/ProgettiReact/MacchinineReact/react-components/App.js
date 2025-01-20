"use strict";

class App extends React.Component {
    /*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Configurazione", "Pista", "Statistiche"];

        // Colori
        this.colors = ["blue", "red", "green", "yellow"];

        this.interval = null;

        // Inizializzazione dello state
        this.state = {
            ended: false,
            n_calls: 0,
            n: 2,
            cars: [],
            pista: []
        };

        // Binding delle funzioni
        this.setN = this.setN.bind(this);
        this.creaPista = this.creaPista.bind(this);
        this.start = this.start.bind(this);
        this.avanza = this.avanza.bind(this);
        this.checkWinner = this.checkWinner.bind(this);
    }

    /*** Funzioni ***********************************************************************/

    // Setta il numero di auto
    setN(){
        let n = parseInt(document.getElementById("nCars").value) || 2;
        this.setState({n: n}, () => this.creaPista());
    }

    // Crea pista
    creaPista() {
        let pista = [];
        let r;
        this.setState({
            cars: Array.from({ length: this.state.n }, (_, idx ) => {
                return {pos: 0, col: this.colors[idx]}
            }) 
        }, () => {
            for (let row = 0; row < this.state.n; row++) {
                r = [];
                for (let cell = 0; cell < 11; cell++) {
                    r.push({
                        isCar: false,
                        n: cell
                    });
                }
                r[this.state.cars[row].pos].isCar = true;
                pista.push(r);
            }
            this.setState({ pista: pista });
        })
    }

    // Inizia la gara
    start(){
        this.setState({
            ended: false,
            n_calls: 0
        })
        this.interval = setInterval(this.avanza, 4000);
    }

    // Avanza macchinine
    avanza() {
        let step;
        let cars = this.state.cars;
        for(let i = 0; i < this.state.n; i++){
            step = Math.floor(Math.random()*4);
            cars[i].pos += step;
        }
        this.setState({
            cars: cars,
            n_calls: this.state.n_calls + 1
        }, () => {this.checkWinner()});
    }

    // Controllo se c'è un vincitore
    checkWinner(){
        let cars = this.state.cars;
        let winners = cars.filter((car) => car.pos>= 10);
        if (winners.length > 0){
            this.setState({ended:true});
            if (winners.length == 1){
                alert("Ha vinto la macchinina: " + winners[0].col);
            }
            else {
                let colList = "";
                for (let car in winners){
                    colList += car.col + " "
                }
                alert("Hanno vinto le macchinine " + colList);
            }
            clearInterval(this.interval);
        }
    }

    /*** Render *************************************************************************/
    render() {
        if (this.state.init){
            this.creaPista();
            setState({init: false});
        }

        return (
            <div>
                <Section1
                    sectionName={this.sectionNames[0]}
                    setN={this.setN}
                />
                <Section2
                    sectionName={this.sectionNames[1]}
                    start={this.start}
                    cars={this.state.cars}
                    pista={this.state.pista}
                />
                <Section3
                    sectionName={this.sectionNames[2]}
                    cars={this.state.cars}
                    calls={this.state.n_calls}
                    ended={this.state.ended}
                />
            </div>
        );
    }
}
