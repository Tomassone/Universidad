class Section1 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <input type="number" min="0" id="soldi"/>
                <br></br>
                <br></br>
                <button onClick={this.props.aumentaCredito}>Inserisci il credito</button>
                </fieldset>
            </div>
        );
    }
}
