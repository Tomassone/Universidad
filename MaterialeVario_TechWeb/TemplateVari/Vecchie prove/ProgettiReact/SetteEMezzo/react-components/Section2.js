class Section2 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <label>Puntata: </label>
                <input type="number" max={this.props.credito} min="0" id="puntata" readOnly = {this.props.isPlaying}/>
                <br></br>
                <br></br>
                <button onClick={this.props.iniziaGioco} 
                        disabled = {this.props.isPlaying}>Inizia!</button>

                
                <div hidden = {!this.props.isPlaying}>
                    <br></br>
                    <button onClick={this.props.pescaGiocatore} disabled = {!this.props.playerTurn}>Chiama</button>
                    <button onClick={this.props.stop} disabled = {!this.props.playerTurn}>Stop</button>
                    <br></br>
                    <br></br>
                    {this.props.giocatore.reduce((acc, value) => (acc += value), 0 ) > 7.5? "Hai perso" :
                         "Punteggio giocatore: " + this.props.giocatore.reduce((acc, value) => (acc += value), 0 )
                        }
                    <br></br>
                    <table >
                        <tr>
                            {
                                this.props.giocatore.map((carta) => (
                                    
                                        <td style={{
                                                border: "3px solid red",
                                                width: "40px",
                                                backgroundColor: carta === 0.5? "yellow" : "none"
                                            }}
                                        >
                                            {carta === 0.5? "figura" : carta}
                                        </td>
                                    
                                ))
                            }
                        </tr>
                    </table>
                    <br></br>
                    <br></br>
                    {
                        this.props.banco.reduce((acc, value) => (acc += value), 0 ) > 7.5? "Hai perso" :
                         "Punteggio banco: " + this.props.banco.reduce((acc, value) => (acc += value), 0 )
                    }
                    <br></br>
                    <table >
                        <tr>
                            {
                                this.props.banco.map((carta) => (
                                    
                                        <td style={{
                                                border: "3px solid green",
                                                width: "40px",
                                                backgroundColor: carta === 0.5? "yellow" : "none"
                                            }}
                                        >
                                            {carta === 0.5? "figura" : carta}
                                        </td>
                                    
                                ))
                            }
                        </tr>
                    </table>
                    <br></br>
                    <button onClick={this.props.reset} disabled={!this.props.isEnded}>Reset</button>
                </div>
                </fieldset>
            </div>
        );
    }
}
