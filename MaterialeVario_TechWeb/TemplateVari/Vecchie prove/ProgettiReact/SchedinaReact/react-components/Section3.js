class Section3 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <table hidden={this.props.estratti.length == 0}>
                    <tr>
                        {
                            this.props.estratti.map((item) => 
                                <td style={{border: "1px solid red"}}>
                                    {item}
                                </td>
                            )
                        }
                    </tr>
                </table>
                <br></br>
                <button onClick={this.props.estrai}>Estrai!</button>
                </fieldset>
            </div>
        );
    }
}
