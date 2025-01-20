class Section1 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                {   
                    Array.from({length: 5}, (_, i) => i).map((i) =>
                        <p>
                        <input 
                            type="number" 
                            placeholder={"Numero " + (i+1) }
                            min="1"
                            max="10"
                            id={"campo" + i}
                            style={{width:"100px"}}
                        />
                        <br></br>
                        </p> 
                    )
                }
                <br></br>
                <button onClick={this.props.compila}>Conferma</button>
                </fieldset>
            </div>
        );
    }
}
