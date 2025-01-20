"use strict";

class App extends React.Component {
    /*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Configurazione", "Lago", "Punteggio"];

        // Inizializzazione dello state
        this.state = {
            x_len: 8,
            y_len: 8,
            n_lanci: 5,
            init: true,
            lake: [],
            lanci: []
        };

        // Binding delle funzioni
        this.init = this.init.bind(this);
        this.buildLake = this.buildLake.bind(this);
        this.getPoints = this.getPoints.bind(this);
    }

    /*** Funzioni ***********************************************************************/
    // Modifica le dimensioni del lago
    init() {
        let x = parseInt(document.getElementById("xLen").value)
        let y = parseInt(document.getElementById("yLen").value)
        let n_lanci = parseInt(document.getElementById("nLanci").value)
        this.setState({ x_len: x, y_len: y, n_lanci:n_lanci, lanci: []}, () => this.buildLake());
    }

    // Genera il lago
    buildLake() {
        let lake = [];
        let row;
        for (let i = 0; i < this.state.y_len; i++) {
            row = [];
            for (let j = 0; j < this.state.x_len; j++) {
                row.push({
                    value: Math.floor(Math.random() * 6),
                    discover: false,
                    coords: {
                        x: j,
                        y: i
                    }
                })
            }
            lake.push(row);
        }
        this.setState({lake: lake});
    }

    // Assegna il punteggio corrispondente nel lago
    getPoints(e) {
        let coords = JSON.parse(e.target.id);
        let newLake = this.state.lake;
        let lancio = 0;
        if (!newLake[coords.y][coords.x].discover && this.state.n_lanci > 0) {
            for (let x = Math.max(0, coords.x - 1); x <= Math.min(this.state.x_len, coords.x + 1); x++) {
                for (let y = Math.max(0, coords.y - 1); y <= Math.min(this.state.y_len, coords.y + 1); y++) {
                    if (!newLake[y][x].discover) {
                        newLake[y][x].discover = true;
                        lancio += newLake[y][x].value;
                    }
                }
            }
            this.setState({
                lake: newLake,
                lanci: [...this.state.lanci, lancio],
                n_lanci: this.state.n_lanci -1
            })
        }
    }

    /*** Render *************************************************************************/
    render() {
        if (this.state.init){
            this.setState({init: false}, this.buildLake());
        }
        return (
            <div>
                <Section1
                    sectionName={this.sectionNames[0]}
                    init={this.init}
                />
                <Section2
                    sectionName={this.sectionNames[1]}
                    lake={this.state.lake}
                    n_lanci={this.state.n_lanci}
                    getPoints={this.getPoints}
                />
                <Section3
                    sectionName={this.sectionNames[2]}
                    lanci={this.state.lanci}
                />
            </div>
        );
    }
}
