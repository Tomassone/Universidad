'use strict';

class App extends React.Component {
	constructor() {
        super();
        this.state = {
	        lunghezza: 3,
	        larghezza: 3,
	        isFinished: true,
	        griglia: null,
	        colors: null,
	        prevNumber: 0,
	        errori: -1,
	        sequenzaErroriCorr: 0,
	        sequenzaErrori: 0
        }
        this.handleClick = this.handleClick.bind(this);
        this.init = this.init.bind(this);
        this.resetGame = this.resetGame.bind(this);
  	}
  	
  	// Initialize colors and griglia in componentDidMount
	componentDidMount() {
		this.init();
	}
  	
  	init() {
		const { lunghezza, larghezza } = this.state;
	
	    let griglia = [];
	    let colors = [];
	
	    // Initialize colors
	    for (let i = 0; i < lunghezza; i++) {
	    	const row = [];
	      	for (let j = 0; j < larghezza; j++) {
	        	row.push("grey");
	      	}
	      	colors.push(row);
	    }
	
	    // Initialize griglia
	    let letters = "123456789";
	    let alreadyUsed = Array(9).fill(false);
	    let chosen_number = letters[Math.floor(Math.random() * 9)];
	
	    for (let i = 0; i < lunghezza; i++) {
	    	const row = [];
	      	for (let j = 0; j < larghezza; j++) {  
	        	while (alreadyUsed[parseInt(chosen_number, 10)]) {
				    chosen_number = letters[Math.floor(Math.random() * 9)];
				}
	    		row.push(chosen_number);
	    		alreadyUsed[parseInt(chosen_number, 10)] = true; 
	    		chosen_number = letters[Math.floor(Math.random() * 9)];
	    	}
	    	griglia.push(row);
	    }
	
	    // Set the state after initialization
	    this.setState({
      		colors: colors,
		    griglia: griglia,
		    errori: 0,
		    successi: 0,
		    isFinished: false
	    });
  	}
	
	handleClick(event) {
		//alert('E\' stata cliccata una cella');
		
		if (event.target.style.backgroundColor !== "green" && this.state.isFinished === false)
		{
			const targetPosition = parseInt(event.target.name, 10);
			let currentPosition = 0;
			
			for (var i = 0; i < this.state.lunghezza; i++) {
		        for (var j = 0; j < this.state.larghezza; j++) {
					currentPosition = i * this.state.larghezza + j;
					if (targetPosition === currentPosition) {
						if (parseInt(this.state.griglia[i][j], 10) == (this.state.prevNumber + 1)) {
							event.target.value = this.state.griglia[i][j];
							let colors = this.state.colors;
							colors[i][j] = "green";
							let sequenzaErrori = 0;
							if (this.state.errori < this.state.sequenzaErroriCorr)
								sequenzaErrori = this.state.sequenzaErroriCorr
							else
								sequenzaErrori = this.state.errori;
							
							this.setState({ colors: colors, prevNumber: this.state.prevNumber + 1, sequenzaErroriCorr: 0, sequenzaErrori: sequenzaErrori });
						}
						else {
							alert("Questa è la casella: " + parseInt(this.state.griglia[i][j], 10) + ", riprova!");
							this.setState({ sequenzaErroriCorr: this.state.sequenzaErroriCorr + 1, errori: this.state.errori + 1, });
						}
					}
		        }
			}
		}
		
		let colors = this.state.colors;
		let nVerdi = 0;
		
		for (var i = 0; i < this.state.lunghezza; i++) {
	        for (var j = 0; j < this.state.larghezza; j++) {
				if (colors[i][j] === "green") {
					nVerdi++;
				}
	        }
		}
		
		if (nVerdi == 9)
		{
			this.setState({ isFinished: true });
			console.log('onClick: Partita finita!');
		}
	}
	
	// Reset the game state to the initial state
    resetGame() {
        this.setState({
            lunghezza: 3,
	        larghezza: 3,
	        isFinished: true,
	        griglia: null,
	        colors: null,
	        prevNumber: 0,
	        errori: -1,
	       	sequenzaErroriCorr: 0,
	        sequenzaErrori: 0
        }, () => {
            this.init(); // Reinitialize the game
        });
    }
	
	render() {
    	return (
	        <div className="Sequenza">
	            <h1>Sequenza</h1>
	            <div>Partita in corso: {this.state.isFinished ? "no" : "sì"}</div>
	            {this.state.colors && this.state.griglia ? (
	                <Grid 
	                    key={this.state.griglia ? this.state.griglia.join() : ''}
	                    colors={this.state.colors} 
	                    lunghezza={this.state.lunghezza} 
	                    larghezza={this.state.larghezza} 
	                    handleClick={this.handleClick}
	                    resetGame={this.resetGame} 
	                />
	            ) : (
	                <div>Loading...</div> // Render a fallback until colors is ready
	            )}
	            <Score nErrori={this.state.errori} sequenzaErrori={this.state.sequenzaErrori} />
	        </div>
    	);
  	}
}
