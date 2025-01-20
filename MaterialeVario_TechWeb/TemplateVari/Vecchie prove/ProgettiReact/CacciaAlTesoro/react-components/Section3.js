class Section3 extends React.Component {
    constructor() {
        super();
    }

    render() {
        let punti;
        let tentativi = this.props.tentativi
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <b>Tentativi: </b> {this.props.tentativi || 0}
                <br></br>
                <div hidden={!this.props.trovato}>
                <i>Punti: </i> {this.props.tentativi > 10? "2" : "5"}
                </div>
                </fieldset>
            </div>
        );
    }
}
