class Section2 extends React.Component {
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
                        <tr>
                            {
                                row.map((cell) =>
                                <td
                                    id={JSON.stringify(cell.pos)}
                                    style={{
                                        width: "50px",
                                        height: "50px",
                                        border: "1px solid black",
                                        backgroundColor: cell.cliccato?
                                                (cell.isTesoro? "yellow": "blue") : "gray"
                                    }}
                                    onClick={
                                        this.props.trovato? null : this.props.clicca
                                    }
                                    >
                                        {cell.isTesoro && cell.cliccato? "T": ""}
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
