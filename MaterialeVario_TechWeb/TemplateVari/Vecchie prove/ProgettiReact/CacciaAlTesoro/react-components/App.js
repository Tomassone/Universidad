"use strict";

class App extends React.Component {
    /*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Configurazione", "Mappa del tesoro", "Punteggio"];

        // Inizializzazione dello state
        this.state = {
            dim: {
                x: 5,
                y: 5,
            },
            tesoro: {
                x: 0,
                y: 0
            },
            trovato: false,
            griglia: [],
            tentativi: 0,
        };

        // Binding delle funzioni
        this.init = this.init.bind(this);
        this.creaGriglia = this.creaGriglia.bind(this);
        this.clicca = this.clicca.bind(this);
    }

    /*** Funzioni ***********************************************************************/
    // Modifica la sezione corrente
    init() {
        let x = parseInt(document.getElementById("larghezza").value)
        let y = parseInt(document.getElementById("lunghezza").value)
        let dims = {
            x: x,
            y: y
        }
        let tesoro = {
            x: Math.floor(Math.random()*x),
            y: Math.floor(Math.random()*y),
        }
        this.setState({
            dim: dims,
            tesoro: tesoro,
            griglia: [],
            tentativi: 0,
            trovato: false
        }, () => this.creaGriglia())
    }

    // Crea la griglia
    creaGriglia(){
        let griglia = []
        let row;
        for(let i = 0; i < this.state.dim.y; i++){
            row = [];
            for(let j = 0; j < this.state.dim.x; j++){
                row.push({
                    pos: {
                        x: j,
                        y: i
                    },
                    cliccato: false,
                    isTesoro: (i === this.state.tesoro.y && j === this.state.tesoro.x)
                });
            }
            griglia.push(row);
        }
        this.setState({griglia: griglia, tentativi: 0});
    }

    // Scopri cella
    clicca(e){
        let coord = JSON.parse(e.target.id);
        let newGriglia = this.state.griglia;
        if (!newGriglia[coord.y][coord.x].cliccato){
        newGriglia[coord.y][coord.x].cliccato = true;
            this.setState({
                griglia: newGriglia,
                tentativi: this.state.tentativi + 1,
                trovato: this.state.trovato || newGriglia[coord.y][coord.x].isTesoro
            })
        }
    }

    /*** Render *************************************************************************/
    render() {
        return (
            <div>
                <Section1
                    sectionName={this.sectionNames[0]}
                    init={this.init}
                />
                <Section2
                    sectionName={this.sectionNames[1]}
                    griglia={this.state.griglia}
                    clicca={this.clicca}
                    trovato={this.state.trovato}
                />
                <Section3
                    sectionName={this.sectionNames[2]}
                    tentativi={this.state.tentativi}
                    trovato={this.state.trovato}
                />
            </div>
        );
    }
}
