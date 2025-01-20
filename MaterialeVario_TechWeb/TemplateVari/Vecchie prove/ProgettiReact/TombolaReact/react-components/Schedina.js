class Schedina extends React.Component {
    constructor(){
        super()
    }

    render(){
        return (
            <table 
                style={{
                    margin: "30px",
                    border: "2px solid black"
                }}
                hidden={this.props.hidden}
            >
                {this.props.schedina.map(
                    (row) => (
                        <tr 
                            style={{
                            border: this.props.borderColor
                        }}>
                            {row.map((cell) => 
                                <td 
                                    style={{
                                        border: "3px solid " + this.props.borderColor,
                                        backgroundColor: this.props.extracted.includes(cell) ? 'lightgrey': 'none',
                                        width: "20px",
                                        height: "20px"
                                    }}
                                >
                                    {this.props.extracted.includes(cell) ? '': cell}
                                </td>
                            )}
                        </tr>
                ))}
            </table>
        )
    }
}