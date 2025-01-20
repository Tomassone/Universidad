class Section1 extends React.Component {
    // Configurazione 
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                {this.props.premi.map(
                    (premio, idx) => (
                        <div>
                        <text>{premio.nome}</text>
                        <br>
                        </br>
                        <input 
                                type="number" 
                                min="0" 
                                max="40" 
                                value={premio.premio} 
                                onChange={(event) => this.props.updateStatus(event, idx)}
                                readOnly={this.props.isPlaying}/>  
                        <br>
                        </br>
                        </div>
                    )
                )}
                

                </fieldset>
            </div>
        );
    }
}
