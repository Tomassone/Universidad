'use strict';

class App extends React.Component {
	constructor() {
        super();
        this.state = {
	        generated: false,
	        val_1: 0,
	        val_2: 0,
	        val_3: 0,
	        val_4: 0,
	        val_5: 0,
	        val_1g: 0,
	        val_2g: 0,
	        val_3g: 0,
	        val_4g: 0,
	        val_5g: 0,
	        finalized: false,
	        extracted: false,
	        isFinished: false,
	        result: ""
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
	    	this.setState({ [name]: parsedValue });
	    else
        	console.log("Invalid input value: " + value);
	}
	
	isNumber(value) {
    	return !isNaN(value) && !isNaN(parseFloat(value));
	}
	
	getScore()
	{
		let score = 0;
		if (this.state.val_1 === this.state.val_1g)
			score++;
		if (this.state.val_2 === this.state.val_2g)
			score++;
		if (this.state.val_3 === this.state.val_3g)
			score++;
		if (this.state.val_4 === this.state.val_4g)
			score++;
		if (this.state.val_5 === this.state.val_5g)
			score++;
		return score;
	}
	
	handleClick(event) {
	    if (!this.state.isFinished) {
	        console.log("Button pressed! " + event.target.value);
	        
	        if (event.target.value === "GeneraScheda") {
	            this.setState({ generated: true });
	        } else if (event.target.value === "FinalizzaScheda") {
	            if (
	                this.state.val_1 !== 0 &&
	                this.state.val_2 !== 0 &&
	                this.state.val_3 !== 0 &&
	                this.state.val_4 !== 0 &&
	                this.state.val_5 !== 0
	            ) {
	                this.setState({ finalized: true });
	            }
	        } else {
	            // Generate random numbers and set isFinished to true
	            this.setState({
	                isFinished: true,
	                extracted: true,
	                val_1g: Math.floor(Math.random() * 10) + 1,
	                val_2g: Math.floor(Math.random() * 10) + 1,
	                val_3g: Math.floor(Math.random() * 10) + 1,
	                val_4g: Math.floor(Math.random() * 10) + 1,
	                val_5g: Math.floor(Math.random() * 10) + 1,
	            }, () => {
	                // Callback after state update
	                const score = this.getScore();
	                console.log('Score calculated: ', score);
	
	                // Determine result based on score
	                let result = "";
	                switch (score) {
	                    case 0:
	                    case 1:
	                        result = "non-vincente";
	                        break;
	                    case 2:
	                        result = "ambo";
	                        break;
	                    case 3:
	                        result = "terno";
	                        break;
	                    case 4:
	                        result = "quaterna";
	                        break;
	                    case 5:
	                        result = "cinquina";
	                        break;
	                    default:
	                        break;
	                }
	
	                // Update the result state
	                this.setState({ result: result });
	            });
	        }
	    } else {
	        console.log("Game finished!");
	    }
	}
	
	// Reset the game state to the initial state
    resetGame() {
	    this.setState({
	        generated: false,
	        val_1: 0,
	        val_2: 0,
	        val_3: 0,
	        val_4: 0,
	        val_5: 0,
	        val_1g: 0,
	        val_2g: 0,
	        val_3g: 0,
	        val_4g: 0,
	        val_5g: 0,
	        finalized: false,
	        extracted: false,
	        isFinished: false,
	        result: ""
	    });
	}
	
	render() {
    	return (
	        <div className="Schedina">
	            <h1>Schedina</h1>
	            <div>Partita in corso: {this.state.isFinished ? "no" : "sì"}</div>
	            <ConfigSection generated={this.state.generated} handleChange={this.handleChange} handleClick={this.handleClick} />
                <View 
                    generated={this.state.generated} 
                    isFinished={this.state.isFinished} 
                    finalized={this.state.finalized} 
                    result={this.state.result}
                    val_1={this.state.val_1}
                    val_2={this.state.val_2}
                    val_3={this.state.val_3}
                    val_4={this.state.val_4}
                    val_5={this.state.val_5} 
                />
	            <Random 
                    extracted={this.state.extracted} 
                    finalized={this.state.finalized} 
                    val_1g={this.state.val_1g}
                    val_2g={this.state.val_2g}
                    val_3g={this.state.val_3g}
                    val_4g={this.state.val_4g}
                    val_5g={this.state.val_5g}
                    handleClick={this.handleClick}
                />
	            <button onClick={this.resetGame}>Reset Game</button>
	        </div>
    	);
  	}
}
