class Section3 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <table>
                    <tr>
                        <td>
                            Premio
                        </td>
                        <td>
                            Valore
                        </td>
                        <td>
                            Vincitore
                        </td>
                    </tr>
                    {
                        this.props.premi.map(
                            (premio) => (
                                <tr>
                                    <td>
                                        {premio.nome}
                                    </td>
                                    <td>
                                        {premio.premio}
                                    </td>
                                    <td>
                                        {premio.vincitore? premio.vincitore: "-"}
                                    </td>
                                </tr>
                            )
                        )
                    }
                </table>
                <br>
                </br>
                Totale giocatore 1: {
                            this.props.premi
                                .filter((premio) => premio.vincitore === 1)
                                .reduce((sum, premio) => sum += premio.premio, 0)
                            }
                <br>
                </br>
                Totale giocatore 2: {
                            this.props.premi
                                .filter((premio) => premio.vincitore === 2)
                                .reduce((sum, premio) => sum += premio.premio, 0)
                            }
                <br></br>
                <button onClick={this.props.reset} disabled={this.props.isPlaying}> Ricomincia!</button>
                </fieldset>
            </div>
        );
    }
}
