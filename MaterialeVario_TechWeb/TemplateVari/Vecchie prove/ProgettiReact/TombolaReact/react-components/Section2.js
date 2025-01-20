class Section2 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <button onClick={this.props.startGame} disabled={this.props.isPlaying}> Inizia!</button>
                <div hidden = {!this.props.isPlaying}>
                    GIOCATORE 1:
                    <div style={{display:"flex"}}>
                        <Schedina
                            extracted={this.props.extracted}
                            schedina={this.props.giocatore1[0]}
                            borderColor='green'
                        />
                        <Schedina
                            extracted={this.props.extracted}
                            schedina={this.props.giocatore1[1]}
                            borderColor='red'
                            
                        />
                    </div>
                    <br ></br>
                    GIOCATORE 2:
                    <div style={{display:"flex"}}>
                        <Schedina
                            extracted={this.props.extracted}
                            schedina={this.props.giocatore2[0]}
                            borderColor='green'
                        />
                        <Schedina
                            extracted={this.props.extracted}
                            schedina={this.props.giocatore2[1]}
                            borderColor='red'
                        />
                    </div>
                    <br ></br>
                    NUMERI ESTRATTI:
                    <br></br>
                    {this.props.extracted.map((value) =>
                        (
                            <span>{value+ " "}</span>
                        )
                    )}
                </div>
                </fieldset>
            </div>
        );
    }
}
