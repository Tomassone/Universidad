class Section1 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <label for="TCredito">T Credito: </label>
                <input type="number" min="0" id="TCredito"></input>
                <br></br>
                <br></br>
                <button onClick={this.props.aggiornaCredito}>Aggiungi Credito!</button>
                </fieldset>
            </div>
        );
    }
}
