"use strict";

class App extends React.Component {
    /*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Compilazione", "Visualizzazione", "Estrazione"];
        this.colors = ["white", "white", "yellow", "green", "blue", "red"];

        // Inizializzazione dello state
        this.state = {
            schedina: [],
            estratti: []
        };

        // Binding delle funzioni
        this.compila = this.compila.bind(this);
        this.estrazione = this.estrazione.bind(this);
        this.colora = this.colora.bind(this);
    }

    /*** Funzioni ***********************************************************************/
    // Compila la schedina
    compila() {
        let newSchedina = [];
        for(let i = 0; i < 5; i++){
            newSchedina.push({
                value: parseInt(document.getElementById("campo"+ i).value),
                color: "white"
            });
        }
        if (newSchedina.some((item, index) => 
                newSchedina.map((element) => element.value)
                .indexOf(item.value) !== index)){
            alert("I numeri devono essere tutti diversi!");
        } else {
            this.setState({schedina: newSchedina});
        }
    }

    // Estrazione di 5 numeri
    estrazione(){
        let numbers = Array.from({length: 10}, (_, idx) => idx + 1);
        let estratti = [];
        let idx;
        for (let i = 0; i < 5; i++){
            idx = Math.floor(Math.random()*numbers.length);
            estratti.push(numbers[idx]);
            numbers.splice(idx, 1);
        }
        this.setState({estratti: estratti}, () => this.colora())
    }

    // Colora le caselle estratte
    colora(){
        let estratti = this.state.estratti;
        let schedina = this.state.schedina;
        estratti = estratti.filter((numero, idx) => schedina
                            .map((element) => element.value)
                            .includes(numero)
                )
        estratti = estratti.map((value) => this.state.schedina
                        .map((element) => element.value)
                        .indexOf(value));
        for (let i = 0; i < schedina.length; i++){
            if (estratti.includes(i)){
                schedina[i].color = this.colors[estratti.length];
            } else {
                schedina[i].color = "white";
            }
        }
        this.setState({schedina: schedina});
    }


    /*** Render *************************************************************************/
    render() {
        return (
            <div>
                <Section1
                    sectionName={this.sectionNames[0]}
                    compila={this.compila}
                />
                <Section2
                    sectionName={this.sectionNames[1]}
                    schedina={this.state.schedina}
                />
                <Section3
                    sectionName={this.sectionNames[2]}
                    estrai={this.estrazione}
                    estratti={this.state.estratti}
                />
            </div>
        );
    }
}
