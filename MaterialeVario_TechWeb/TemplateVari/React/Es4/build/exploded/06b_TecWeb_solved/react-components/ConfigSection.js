'use strict';

class ConfigSection extends React.Component {
    render() {
		let generated = this.props.generated;
        return (
            <div className="config">
                <form onSubmit={this.props.handleSubmit}>
                	<input type ="button" value="GeneraScheda" onClick={this.props.handleClick} />
                    {(generated) && <p> Primo valore:
                        <input type="text" name="val_1" onChange={this.props.handleChange} />
                    </p> }
                    {(generated) && <p> Secondo valore:
                        <input type="text" name="val_2" onChange={this.props.handleChange} />
                    </p> }
                    {(generated) && <p> Terzo valore:
                        <input type="text" name="val_3" onChange={this.props.handleChange} />
                    </p> }
                    {(generated) && <p> Quarto valore:
                        <input type="text" name="val_4" onChange={this.props.handleChange} />
                    </p> }
                    {(generated) && <p> Quinto valore:
                        <input type="text" name="val_5" onChange={this.props.handleChange} />
                    </p>}
                    <input type ="button" value="FinalizzaScheda" onClick={this.props.handleClick} />
                    </form>
            </div>
        );
    }
}
