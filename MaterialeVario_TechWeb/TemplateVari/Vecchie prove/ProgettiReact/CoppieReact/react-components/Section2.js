class Section2 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <b>Errori: </b> {this.props.errori}
                <br></br>
                <b>Coppie trovate: </b> {this.props.coppie}
                <br></br>
                {this.props.coppie === 8? "Gioco terminato" : ""}
                <br></br>
                <button onClick={this.props.init}>Ricomincia!</button>
                </fieldset>: 
            </div>
        );
    }
}
