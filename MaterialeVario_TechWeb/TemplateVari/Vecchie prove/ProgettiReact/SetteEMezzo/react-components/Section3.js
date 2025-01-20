class Section3 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                Credito iniziale: {this.props.creditoInit}€
                <br></br>
                Credito attuale: {this.props.credito}€
       

                </fieldset>
            </div>
        );
    }
}
