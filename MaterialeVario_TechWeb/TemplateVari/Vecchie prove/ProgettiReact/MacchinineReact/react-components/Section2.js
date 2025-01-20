class Section2 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <button onClick={this.props.start}>Inizia!</button>
                <br></br>
                <br></br>
                <table>
                    {
                        this.props.pista.map((row, y) =>
                        <tr>
                            {
                                row.map((cell, x) => 
                                <td style={{
                                    border: "5px solid " + (this.props.cars[y].pos === x?
                                    this.props.cars[y].col : 
                                        (this.props.cars[y].pos >= 10 && x === 10?
                                            this.props.cars[y].col : 
                                            "black"))
                                }}>
                                    {x === 0? "partenza" : 
                                        x === 10? "traguardo":
                                            x
                                    }
                                </td>
                                )
                            }
                        </tr>
                    )}
                </table>
                </fieldset>
            </div>
        );
    }
}
