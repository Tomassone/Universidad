class Section2 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <div>
                {this.props.n_lanci} rimanenti!
                <br></br>
                <br></br>
                <table>
                {
                    this.props.lake.map((row) => 
                    <tr>
                        {
                            row.map ((cell)=> 
                                <td onClick={this.props.getPoints } style={{
                                        border:"1px solid black",
                                        backgroundColor: cell.discover? "white":"gray",
                                        height: "40px",
                                        width: "40px"}}
                                    id={JSON.stringify(cell.coords)}>
                                    {cell.discover? cell.value : ""}
                                </td>
                            )
                        }
                    </tr>
                    )
                }
                </table>
                </div>
                </fieldset>
            </div>
        );
    }
}
