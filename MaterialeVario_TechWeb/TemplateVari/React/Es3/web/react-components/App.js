'use strict';

class App extends React.Component {
	constructor() {
        super();
        this.state = {
	        lunghezza: 0,
	        larghezza: 0,
	        isFinished: true,
	        griglia: null,
	        visible: null,
	        punteggiMedi: [],
	        punteggi: [],
	        nTiri: 0,
	        tiriEff: 0
        }
        this.handleClick = this.handleClick.bind(this);
        this.init = this.init.bind(this);
        this.resetGame = this.resetGame.bind(this);
        this.handleChange = this.handleChange.bind(this);
  	}
  	
  	handleChange(event) {
	    const name = event.target.name;
	    const value = event.target.value;
	    console.log('onChange:' + name +  'di ' + event.target.value);
	    this.setState({ [name]: value }, () => { this.init(); }); // call init after state has been updated
	}
  	
  	init() {
		const { lunghezza, larghezza } = this.state;
		
		if (!lunghezza || !larghezza || lunghezza <= 0 || larghezza <= 0 || this.state.nTiri == 0) {
        	//console.error("Invalid grid dimensions: lunghezza and larghezza must be greater than 0");
        	return;
    	}
		
	    let griglia = [];
	    let visible = [];
	
	    // Initialize visible
	    for (let i = 0; i < lunghezza; i++) {
	    	const row = [];
	      	for (let j = 0; j < larghezza; j++) {
	        	row.push(false);
	      	}
	      	visible.push(row);
	    }
	
	    // Initialize griglia
	    let nPesci = Math.floor(Math.random() * 5);
	
	    for (let i = 0; i < lunghezza; i++) {
	    	const row = [];
	      	for (let j = 0; j < larghezza; j++) {
	        	row.push(nPesci);
	        	nPesci = Math.floor(Math.random() * 5);
	    	}
	    	griglia.push(row);
	    }
	
	    // Set the state after initialization
	    this.setState({
      		visible: visible,
		    griglia: griglia,
		    isFinished: false
	    });
  	}
  	
  	isOk(x, y) {
	    // Ensure indices are within bounds before accessing the array
	    if (x < 0 || x >= this.state.lunghezza || y < 0 || y >= this.state.larghezza) {
	        return false; // Out of bounds
	    }
	
	    // Check if the current cell and its neighbors meet the conditions
	    return (!this.state.visible[x][y] &&
            (y == this.state.larghezza - 1 || !this.state.visible[x][y + 1]) &&
            (y == 0 || !this.state.visible[x][y - 1]) &&
            (x == 0 || y == 0 || !this.state.visible[x - 1][y - 1]) &&
            (x == 0 || !this.state.visible[x - 1][y]) &&
            (x == 0 || y == this.state.larghezza - 1 || !this.state.visible[x - 1][y + 1]) &&
            (x == this.state.lunghezza - 1 || y == 0 || !this.state.visible[x + 1][y - 1]) &&
            (x == this.state.lunghezza - 1 || !this.state.visible[x + 1][y]) &&
            (x == this.state.lunghezza - 1 || y == this.state.larghezza - 1 || !this.state.visible[x + 1][y + 1]));
	}
	
	updateCell(x, y)
	{
		let score = 0.0;
		let visible = this.state.visible;
		let griglia = this.state.griglia;
		let nCelle = 0.0;
		visible [x][y] = true;
		score = score + griglia[x][ y]; nCelle++;
		if (x != 0)
		{
			visible[x - 1][y] = true;
			score = score + griglia[x - 1][ y]; nCelle++;
			if (y != 0)
			{
				visible[x - 1][y - 1] = true;
				score = score + griglia[x -  1][ y - 1]; nCelle++;
			}
			if (y != this.state.larghezza - 1)
			{
				visible[x - 1][y + 1] = true;
				score = score + griglia[x - 1][y + 1]; nCelle++;
			}
		}
		if (x != this.state.larghezza - 1)
		{
			visible[x + 1][y] = true;
			score = score + griglia[x + 1][ y]; nCelle++;
			if (y != 0)
			{
				visible[x + 1][y - 1] = true;
				score = score + griglia[x + 1][ y - 1]; nCelle++;
			}
			if (y != this.state.larghezza - 1)
			{
				visible[x + 1][y + 1] = true;
				score = score + griglia[x + 1][ y + 1]; nCelle++;
			}
		}
		if (y != 0)
		{
			visible[x][y - 1] = true;
			score = score + griglia[x][ y - 1]; nCelle++;
		}
		if (y != this.state.larghezza - 1)
		{
			visible[x][y + 1] = true;
			score = score + griglia[x][ y + 1]; nCelle++;
		}
		
		let punteggi = this.state.punteggi;
		let punteggiMedi = this.state.punteggiMedi;
		punteggi.push(score);
		punteggiMedi.push(score / nCelle);
		this.setState({ visible: visible, punteggi: punteggi, punteggiMedi: punteggiMedi, tiriEff: this.state.tiriEff + 1 });
	}
	
	handleClick(event) {
		//alert('E\' stata cliccata una cella');
		if (this.state.isFinished === false)
		{
			const targetPosition = parseInt(event.target.name, 10);
			let currentPosition = 0;
			
			for (var i = 0; i < this.state.lunghezza; i++) {
		        for (var j = 0; j < this.state.larghezza; j++) {
					currentPosition = i * this.state.larghezza + j;
					if (targetPosition === currentPosition && this.isOk(i, j)) {
						this.updateCell(i, j);
					}
		        }
			}
		}
		
		let visible = this.state.visible;
		let nVisibili = 0;
		
		for (var i = 0; i < this.state.lunghezza; i++) {
	        for (var j = 0; j < this.state.larghezza; j++) {
				if (visible[i][j]) {
					nVisibili++;
				}
	        }
		}
		
		if (nVisibili == this.state.lunghezza * this.state.larghezza || this.state.tiriEff == this.state.nTiri - 1)
		{
			this.setState({ isFinished: true });
			console.log('onClick: Partita finita!');
		}
	}
	
	// Reset the game state to the initial state
    resetGame() {
        this.setState({
            lunghezza: 0,
	        larghezza: 0,
	        isFinished: true,
	        griglia: null,
	        visible: null,
	        punteggiMedi: [],
	        punteggi: [],
	        nTiri: 0,
	        tiriEff: 0
        }, () => {
            this.init(); // Reinitialize the game
        });
    }
	
	render() {
    	return (
	        <div className="TrovaLeCoppie">
	            <h1>Trova le coppie</h1>
	            <div>Partita in corso: {this.state.isFinished ? "no" : "sì"}</div>
	            <ConfigSection lunghezza={this.state.lunghezza} larghezza={this.state.larghezza} handleChange={this.handleChange}/>
	            {this.state.visible && this.state.griglia ? (
	                <Grid 
	                    key={this.state.griglia ? this.state.griglia.join() : ''}
	                    visible={this.state.visible} 
	                    griglia={this.state.griglia} 
	                    lunghezza={this.state.lunghezza} 
	                    larghezza={this.state.larghezza} 
	                    handleClick={this.handleClick} 
	                />
	            ) : (
	                <div>Loading...</div> // Render a fallback until visible is ready
	            )}
	            <Score isFinished={this.state.isFinished} punteggiMedi={this.state.punteggiMedi} punteggi={this.state.punteggi} />
	            <button onClick={this.resetGame}>Reset Game</button>
	        </div>
    	);
  	}
}
