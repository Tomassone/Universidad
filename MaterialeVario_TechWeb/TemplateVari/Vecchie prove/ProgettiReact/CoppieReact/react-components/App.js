"use strict";

class App extends React.Component {
    /*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Griglia", "Risultati"];

        // Mantengo la coppia corrente
        this.currentCouple = [];

        // Inizializzazione dello state
        this.state = {
            griglia: [],
            errori: 0,
            coppie: 0
        };

        // Binding delle funzioni
        this.init = this.init.bind(this);
        this.scopri = this.scopri.bind(this);
    }

    /*** Funzioni ***********************************************************************/
    // Inizializzazione griglia
    init() {
        this.currentCouple = [];
        let griglia = [];
        let lettere = ["a", "b", "c", "d", "e", "f", "g", "h", "a", "b", "c", "d", "e", "f", "g", "h"];
        lettere = lettere.map(value => ({ value, sort: Math.random() }))
            .sort((a, b) => a.sort - b.sort)
            .map(({ value }) => value)
        let r;
        for (let row = 0; row < 4; row++) {
            r = []
            for (let cell = 0; cell < 4; cell++) {
                r.push({
                    discoverd: false,
                    letter: lettere[4 * row + cell],
                    accoppiato: false,
                    row: row,
                    col: cell
                })
            }
            griglia.push(r);
        }
        this.setState({
            errori: 0,
            coppie: 0,
            griglia: griglia
        });
    }

    // Funzione per scopire le carte
    scopri(e) {
        let coords = JSON.parse(e.target.id);
        let newGriglia = this.state.griglia;
        if (this.currentCouple.length > 0) {
            let newValue = this.state.griglia[coords.row][coords.col];
            let oldValue = this.currentCouple[0];
            if (oldValue.col !== newValue.col && oldValue.row !== newValue.row) {
                if (oldValue.letter === newValue.letter) {
                    newGriglia[oldValue.row][oldValue.col].accoppiato = true;
                    newGriglia[newValue.row][newValue.col].accoppiato = true;
                    this.setState({
                        griglia: newGriglia,
                        coppie: this.state.coppie + 1
                    });
                }
                else {
                    newGriglia[newValue.row][newValue.col].discovered = true;

                    this.setState({
                        griglia: newGriglia,
                        errori: this.state.errori + 1
                    }, () => {
                        newGriglia[newValue.row][newValue.col].discoverd = false;
                        newGriglia[oldValue.row][oldValue.col].discoverd = false;
                        this.setState({
                            griglia: newGriglia
                        })
                    });
                }
                this.currentCouple = [];
            }
        }
        else {
            newGriglia[coords.row][coords.col].discoverd = true;
            this.currentCouple.push(newGriglia[coords.row][coords.col]);
            this.setState({ griglia: newGriglia });
        }
    }

    /*** Render *************************************************************************/
    render() {
        return (
            <div>
                <Section1
                    sectionName={this.sectionNames[0]}
                    griglia={this.state.griglia}
                    scopri={this.scopri}
                />
                <Section2
                    sectionName={this.sectionNames[1]}
                    errori={this.state.errori}
                    coppie={this.state.coppie}
                    init={this.init}
                />
            </div>
        );
    }
}
