class Section1 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <input type="number" placeholder="Larghezza" min="8" id="xLen"></input>
                <br></br>
                <input type="number" placeholder="Lunghezza" min="8" id="yLen"></input>
                <br></br>
                <input type="number" placeholder="Numero di lanci" min="0" id="nLanci"></input>
                <br></br>
                <button onClick={this.props.init}>Inizia!</button>
                </fieldset>
            </div>
        );
    }
}
