'use strict';

class App extends React.Component {
	constructor() {
        super();
        this.state = {
			incremento: 0,
	        credito: 0,
	        display1: "x",
	        display2: "x",
	        display3: "x",
	        storico: [],
	        win: false,
	        endGame: false,
	        colors: ["white", "white", "white"]
        }
        this.handleClick = this.handleClick.bind(this);
        this.resetGame = this.resetGame.bind(this);
        this.handleChange = this.handleChange.bind(this);
  	}
  	
  	handleChange(event) {
	    const name = event.target.name;
	    const value = event.target.value;
	    const parsedValue = parseInt(value, 10);
	    console.log('onChange: ' + name +  ' di ' + event.target.value);
	    if (this.isNumber(parsedValue) && parsedValue >= 1 && parsedValue <= 10)
	    	this.setState({ [name]: parsedValue, win: false, endGame: false });
	    else
        	console.log("Invalid input value: " + value);
	}
	
	isNumber(value) {
    	return !isNaN(value) && !isNaN(parseFloat(value));
	}
	
	handleClick(event) {
	    if (event.target.value === "Inserisci") {
			this.setState({ credito: this.state.credito + this.state.incremento });	
		}
		else if (event.target.value === "Gioca" && this.state.credito > 0) {
			let vowels = "aeiou";
			let value1 = vowels[Math.floor(Math.random() * 5)];
			let value2 = vowels[Math.floor(Math.random() * 5)];
			let value3 = vowels[Math.floor(Math.random() * 5)];
			let colors = ["blue", "blue", "blue"];
			let guadagno = 0;
			
			if (value1 == "a" || value1 == "e")
				colors[0] = "green";
			if (value2 == "a" || value2 == "e")
				colors[1] = "green";
			if (value3 == "a" || value3 == "e")
				colors[2] = "green";
				
			if (value1 == value2 || value1 == value3 || value2 == value3) {
				guadagno = 15;
				this.setState({ win: true });
			}
			if (value1 == value2 && value2 == value3) {
				guadagno = 50;
				this.setState({ win: true });
			}
			
			let storico = this.state.storico;
			storico.push(guadagno);
			
			this.setState({ colors: colors, 
				display1: value1, 
				display2: value2, 
				display3: value3,
				storico: storico,
				endGame: true,
				credito: this.state.credito + guadagno - 5 });	
		}
	}
	
	// Reset the game state to the initial state
    resetGame() {
	    this.setState({
	        incremento: 0,
	        credito: 0,
	        display1: "x",
	        display2: "x",
	        display3: "x",
	        storico: [],
	        win: false,
	        endGame: false,
	        colors: ["white", "white", "white"]
	    });
	}
	
	render() {
    	return (
	        <div className="SlotMachine">
	            <h1>SlotMachine</h1>
	            <Credito
	            	credito={this.state.credito}
	            	handleChange={this.handleChange}
	            	handleClick={this.handleClick} />
                <Slot 
                	colors={this.state.colors}
                	display1={this.state.display1}
                    display2={this.state.display2}
                    display3={this.state.display3}
                   	handleClick={this.handleClick}
                />
	            <Risultati 
                    endGame={this.state.endGame} 
                    storico={this.state.storico} 
                    display1={this.state.display1}
                    display2={this.state.display2}
                    display3={this.state.display3}
                />
	            <button onClick={this.resetGame}>Reset Game</button>
	        </div>
    	);
  	}
}
