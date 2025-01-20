class Section2 extends React.Component {
    constructor() {
        super();
    }

    render() {
        var ae = ["a", "e"]
        var iou = ["i", "o", "u"]
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <span>Credito attuale: {this.props.credito}</span>
                <br></br>
                <br></br>
                <button 
                    onClick={this.props.estrazione}
                    disabled={!this.props.canPlay}
                >Estrai!</button>
                <br></br>
                <br></br>
                <table>
                <tr >
                {
                    this.props.display.map((value) =>  
                        <td style={{
                                border: "3px solid black",
                                width: "50px",
                                backgroundColor: ae.includes(value)? 
                                                "green" : 
                                                (iou.includes(value)? 
                                                        "blue" : 
                                                        "none"), 
                                textAlign: "center" }}>
                            {value}
                        </td>)
                }
                </tr>
                </table>
                    
                </fieldset>
            </div>
        );
    }
}
