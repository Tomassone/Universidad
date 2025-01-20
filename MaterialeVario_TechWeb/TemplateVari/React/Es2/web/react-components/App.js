'use strict';

class App extends React.Component {
	constructor() {
        super();
        this.state = {
	        lunghezza: 4,
	        larghezza: 4,
	        isFinished: true,
	        griglia: null,
	        colors: null,
	        prevPosition: -1,
	       	prevElement: null,
	        errori: -1,
	        successi: 0
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
	    let letters = "ABCDEFGH";
	    let chosen_letter = letters[Math.floor(Math.random() * lunghezza)];
	
	    for (let i = 0; i < lunghezza; i++) {
	    	const row = [];
	      	for (let j = 0; j < larghezza; j++) {
	        	row.push(chosen_letter);
	        	chosen_letter = letters[Math.floor(Math.random() * lunghezza)];
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
	
	resetCell(targetPosition, element)
	{
		let currentPosition = 0;
		let griglia = this.state.griglia;
		
		for (var i = 0; i < this.state.lunghezza; i++) {
	        for (var j = 0; j < this.state.larghezza; j++) {
				currentPosition = i * this.state.larghezza + j;
				if (targetPosition === currentPosition) {
					element.innerText = "";
				}
	        }
		}
		this.setState({ griglia: griglia, prevElement: null, prevPosition: -1 });
	}
	
	updateCell(targetPosition)
	{
		let currentPosition = 0;
		let colors = this.state.colors;
		let griglia = this.state.griglia;
		
		for (var i = 0; i < this.state.lunghezza; i++) {
	        for (var j = 0; j < this.state.larghezza; j++) {
				currentPosition = i * this.state.larghezza + j;
				if (targetPosition === currentPosition) {
					colors[i][j] = "green";
				}
	        }
		}
		this.setState({ griglia: griglia, colors: colors, prevElement: null, prevPosition: -1 });
	}
	
	handleClick(event) {
		//alert('E\' stata cliccata una cella');
		
		if (event.target.style.backgroundColor !== "green" && this.state.isFinished === false && this.state.prevElement === null)
		{
			const targetPosition = parseInt(event.target.name, 10);
			let currentPosition = 0;
			
			for (var i = 0; i < this.state.lunghezza; i++) {
		        for (var j = 0; j < this.state.larghezza; j++) {
					currentPosition = i * this.state.larghezza + j;
					if (targetPosition === currentPosition) {
						event.target.innerText = this.state.griglia[i][j];
						event.target.active = "false";
						this.setState({ prevElement: event.target, prevPosition: currentPosition });
					}
		        }
			}
		}
		else if (event.target.style.backgroundColor !== "green"  && this.state.isFinished === false && this.state.prevElement !== null)
		{
			const targetPosition = parseInt(event.target.name, 10);
			let currentPosition = 0;
			
			for (var i = 0; i < this.state.lunghezza; i++) {
		        for (var j = 0; j < this.state.larghezza; j++) {
					currentPosition = i * this.state.larghezza + j;
					if (targetPosition !== this.state.prevPosition && targetPosition === currentPosition && this.state.griglia[i][j] === this.state.prevElement.innerText) {
						event.target.innerText = this.state.griglia[i][j];
	                    // Use setState callback to ensure DOM is updated before alert
	                    this.setState({ successi: this.state.successi + 1 }, () => {
	                        alert("Coppia individuata!");
	                    });				
						this.updateCell(currentPosition);
						this.updateCell(this.state.prevPosition);
					}
					else if (targetPosition !== this.state.prevPosition && targetPosition === currentPosition && this.state.griglia[i][j] !== this.state.prevElement.innerText) {
						event.target.innerText = this.state.griglia[i][j];
						// Use setState callback to ensure DOM is updated before alert
	                    this.setState({ errori: this.state.errori + 1 }, () => {
	                        alert("Coppia non individuata!");
	                    });
	                  	this.resetCell(currentPosition, event.target);
	                    this.resetCell(this.state.prevPosition, this.state.prevElement);
					}
		        }
			}
			this.setState({ prevElement: null, prevPosition: -1 });
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
		
		if (nVerdi == 16)
		{
			this.setState({ isFinished: true });
			console.log('onClick: Partita finita!');
		}
	}
	
	// Reset the game state to the initial state
    resetGame() {
        this.setState({
            lunghezza: 4,
            larghezza: 4,
            isFinished: false,
            griglia: null,
            colors: null,
            prevPosition: -1,
            prevElement: null,
            errori: 0,
            successi: 0
        }, () => {
            this.init(); // Reinitialize the game
        });
    }
	
	render() {
    	return (
	        <div className="TrovaLeCoppie">
	            <h1>Trova le coppie</h1>
	            <div>Parita in corso: {this.state.isFinished ? "no" : "sì"}</div>
	            {this.state.colors && this.state.griglia ? (
	                <Grid 
	                    key={this.state.griglia ? this.state.griglia.join() : ''}
	                    colors={this.state.colors} 
	                    lunghezza={this.state.lunghezza} 
	                    larghezza={this.state.larghezza} 
	                    handleClick={this.handleClick} 
	                />
	            ) : (
	                <div>Loading...</div> // Render a fallback until colors is ready
	            )}
	            <Score nErrori={this.state.errori} nSuccessi={this.state.successi} />
	            <button onClick={this.resetGame}>Reset Game</button>
	        </div>
    	);
  	}
}
