class Section3 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                {
                    this.props.lanci.map((lancio, idx) => 
                        <div>
                            Punti fatti al lancio <i>{idx+1}</i>: <b>{lancio}</b>
                            <br></br> 
                        </div>
                    )
                }
                <br></br>
                <b><i>Totale: {this.props.lanci.reduce((partialSum, a) => partialSum + a, 0)}</i></b>
                <br></br>
                <i>Punti Medi: {(this.props.lanci.reduce((partialSum, a) => partialSum + a, 0)/(this.props.lanci.length||1)).toFixed(2)}</i>
                </fieldset>
            </div>
        );
    }
}
