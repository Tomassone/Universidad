class Section1 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <input type="number" min="2" max="4" id="nCars" placeholder="numero di auto" style={{width: "150px"}}></input>
                <br></br>
                <button onClick={this.props.setN}>Conferma!</button>
                </fieldset>
            </div>
        );
    }
}
