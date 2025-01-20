'use strict';

class App extends React.Component {
	constructor() {
        super();
        this.state = {
	        lunghezza: "",
	        larghezza: "",
	        isFinished: true,
	        griglia: null,
	        errori: -1
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleClick = this.handleClick.bind(this);
  	}
  	
  	initGrid()
  	{
		let griglia = [];
		let win_x = Math.floor(Math.random() * this.state.lunghezza);
		let win_y = Math.floor(Math.random() * this.state.larghezza);
		console.log("X vincente: " + win_x + "; y vincente: " + win_y);
	    
	    for (var i = 0; i < this.state.lunghezza; i++) {
	        const row = [];
	        for (var j = 0; j < this.state.larghezza; j++) {
				if (i === win_x && j === win_y)
	            	row.push("win");
	            else
	            	row.push("lose");
	        }
	    	griglia.push(row);
		}
		this.setState({ griglia: griglia, errori: 0, isFinished: false });
	}
	
	handleChange(event) {
	    const name = event.target.name;
	    const value = event.target.value;
	    console.log('onChange:' + name +  'di ' + event.target.value);
	    this.setState({ [name]: value }, () => { this.initGrid(); }); // call initGrid after state has been updated
	}
	
	handleClick(event) {
		//alert('E\' stata cliccata una cella');
		let hasWinned = false;
		
		if (this.state.isFinished === false)
		{
			const targetPosition = parseInt(event.target.name, 10);
			let currentPosition = 0;
			
			for (var i = 0; i < this.state.lunghezza && hasWinned === false; i++) {
		        for (var j = 0; j < this.state.larghezza && hasWinned === false; j++) {
					currentPosition = i * this.state.larghezza + j;
					if (targetPosition === currentPosition && this.state.griglia[i][j] === "win")
						hasWinned = true;
		        }
			}
		}
		
		if (hasWinned && this.state.isFinished === false) 
		{
			this.setState({isFinished: true});
			event.target.style.backgroundColor = "blue";
			event.target.innerText = "T";
			console.log('onClick: Partita finita!');
		}
		else if (!hasWinned && this.state.isFinished === false)
		{
			this.setState({errori: this.state.errori + 1});
			event.target.style.backgroundColor = "yellow";
			console.log('onClick: + 1 errore');
		}
	}
	
	render() {
    	return (
	        <div className="caccia-al-tesoro">
	            <h1>Caccia al tesoro</h1>
	            <div>Parita in corso: {this.state.isFinished ? "no" : "sì"}</div>
	            <ConfigSection lunghezza={this.state.lunghezza} larghezza={this.state.larghezza} handleChange={this.handleChange} handleSubmit={this.handleSubmit}/>
	            <Grid key={this.state.griglia ? this.state.griglia.join() : ''} lunghezza={this.state.lunghezza} larghezza={this.state.larghezza} handleClick={this.handleClick}/>
	            <Score nErrori={this.state.errori} isFinished={this.state.isFinished}/>
	        </div>
    	);
  	}
}
