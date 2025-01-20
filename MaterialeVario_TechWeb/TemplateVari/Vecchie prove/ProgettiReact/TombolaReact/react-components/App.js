"use strict";

class App extends React.Component {
/*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Configurazione", "Gioco", "Premi"];
        this.allNumbers = Array.from({ length: 100 }, (_, indice) => indice + 1);
        this.interval;
        
        // Inizializzazione dello state
        this.state = {
            premi: [{
                        nome:"ambo",
                        premio: 0,
                        vincitore: null
                    },
                    {
                        nome:"terna",
                        premio: 0,
                        vincitore: null
                    },
                    {
                        nome:"quaterna",
                        premio: 0,
                        vincitore: null
                    },
                    {
                        nome:"cinquina",
                        premio: 0,
                        vincitore: null
                    },
                    {
                        nome:"tombola",
                        premio: 0,
                        vincitore: null
                    },
                ],
            isPlaying: false,
            giocatore1: [[], []],
            giocatore2: [[], []],
            extracted: []
        };

        // Binding delle funzioni
        this.updateValues = this.updateValues.bind(this);
        this.startGame = this.startGame.bind(this);
        this.resetGame = this.resetGame.bind(this);
        this.estrazione = this.estrazione.bind(this);
        this.checkWinners = this.checkWinners.bind(this);
    }

/*** Funzioni ***********************************************************************/
    // Aggiorna lo stato
    updateValues(event, idx){
        let oldState = this.state.premi;
        oldState[idx].premio = +event.target.value;
        this.setState({premi: oldState});
    }

    // Reset dello stato
    resetGame(){
        this.setState({premi: this.state.premi.map((premio) => 
                {
                    premio.vincitore = null;
                    return premio;
                }
            )})
        this.setState({
            isPlaying: false,
            giocatore1: [[], []],
            giocatore2: [[], []],
            extracted: []
        })
    }

    // Inizia il gioco
    startGame(){
        this.resetGame();
        this.setState({
                        isPlaying: true,
                        extracted: [],
                    })
        this.setState({giocatore1: [this.generaSchedina(), this.generaSchedina()]})
        this.setState({giocatore2: [this.generaSchedina(), this.generaSchedina()]})
        this.interval = setInterval(this.estrazione, 2000);
    }


    // Genera schedina
    generaSchedina(){
        let numeri = [];
        var result = [];
        for (let i = 0; i < 4; i++){
            let row = []; 
            for (let j = 0; j < 5; j++){
                let filtred_numbers = this.allNumbers.filter((value) => !numeri.includes(value))
                let number = filtred_numbers[Math.floor(Math.random() * filtred_numbers.length)];
                numeri.push(number);
                row.push(number);
            }
            result.push(row)
        }
        return result;
    }

    // Estrazione
    estrazione(){
        var extracted = this.state.extracted;
        let filtred_numbers = this.allNumbers.filter((value) => !(extracted.includes(value)))
        let number = filtred_numbers[Math.floor(Math.random() * filtred_numbers.length)];
        extracted.push(number);
        this.setState({extracted: extracted})
        this.checkWinners();
    }

    // Controlla i vincitori
    checkWinners(){
        let giocatore1 = this.state.giocatore1;
        let premi = this.state.premi;
        let new_row;

        giocatore1.map((schedina) => (            
            schedina.map((row) => {
                new_row = row.filter((number) => this.state.extracted.includes(number))
                if (new_row.length >= 2){
                    let idx = new_row.length -2;
                    premi[idx].vincitore = premi[idx].vincitore? premi[idx].vincitore : 1;
                }
            }
        )))

        let giocatore2 = this.state.giocatore2;
        giocatore2.map((schedina) => (            
            schedina.map((row) => {
                new_row = row.filter((number) => this.state.extracted.includes(number))
                if (new_row.length >= 2){
                    let idx = new_row.length -2;
                    premi[idx].vincitore = premi[idx].vincitore? premi[idx].vincitore : 2;
                }
            }
        )))

        // Controllo tombola
        let schedine1 = giocatore1.map((schedina) => (            
            schedina.reduce((numbers, row) => numbers = [...numbers, ...row], [])
        ))
        let schedine2 = giocatore2.map((schedina) => (            
            schedina.reduce((numbers, row) => numbers = [...numbers, ...row], [])
        ))
        let f_sched1 = schedine1.filter((schedina) => schedina.every((number) => this.state.extracted.includes(number)))
        let f_sched2 = schedine2.filter((schedina) => schedina.every((number) => this.state.extracted.includes(number)))
        if (f_sched1.length > 0){
            premi[4].vincitore = 1;
            clearInterval(this.interval);
            alert("Il giocatore 1 ha fatto tombola!");
            this.setState({extracted: []});
            this.setState({isPlaying: false});
        }
        else if (f_sched2.length > 0){
            premi[4].vincitore = 2;
            clearInterval(this.interval);
            alert("Il giocatore 2 ha fatto tombola!");
            this.setState({extracted: []});
            this.setState({isPlaying: false});
        }
        this.setState({premi:premi})
    }
/*** Render *************************************************************************/
    render() {
        
        return (
            <div>
                <Section1
                    sectionName = {this.sectionNames[0]}
                    premi = {this.state.premi}
                    updateStatus = {this.updateValues}
                    isPlaying = {this.state.isPlaying}
                />
                <Section2
                    sectionName = {this.sectionNames[1]}
                    startGame   = {this.startGame}
                    isPlaying   = {this.state.isPlaying}
                    extracted   = {this.state.extracted}
                    giocatore1  = {this.state.giocatore1}
                    giocatore2  = {this.state.giocatore2}
                />
                <Section3
                    sectionName = {this.sectionNames[2]}
                    premi = {this.state.premi}
                    reset = {this.resetGame}
                    isPlaying   = {this.state.isPlaying}
                />
            </div>
        );
    }
}
