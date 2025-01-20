class Section1 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <input type="number" id="larghezza" min="5" placeholder="larghezza" style={{width: "80px"}}/>
                <br></br>
                <input type="number" id="lunghezza" min="5" placeholder="lunghezza" style={{width: "80px"}}/>
                <br></br>
                <br></br>
                <button onClick={this.props.init}>Conferma!</button>
                </fieldset>
            </div>
        );
    }
}
