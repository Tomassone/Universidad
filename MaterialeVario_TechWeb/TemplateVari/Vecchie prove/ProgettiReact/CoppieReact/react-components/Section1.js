class Section1 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <table>
                {
                    this.props.griglia.map((row) =>
                        <tr >
                            {row.map((cell) =>
                                <td id={JSON.stringify({
                                    row: cell.row,
                                    col: cell.col
                                    
                                })}
                                style={{
                                    width: "50px",
                                    height: "50px",
                                    border: "2px solid black",
                                    backgroundColor: cell.accoppiato? "green" : 
                                        cell.discovered? "yellow": "gray"
                                }}
                                onClick={this.props.scopri}
                                >
                                    {cell.discovered || cell.accoppiato? cell.letter : ""}
                                </td>
                            )}
                        </tr>
                    )
                }
                </table>
                </fieldset>
            </div>
        );
    }
}
