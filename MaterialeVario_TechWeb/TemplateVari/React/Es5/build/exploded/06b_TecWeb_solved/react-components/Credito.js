'use strict';

class Credito extends React.Component {
    render() {
		let credito = this.props.credito;
        return (
            <div className="credito">
                <form onSubmit={this.props.handleSubmit}>
                    <p>Incremento: <input type="text" name="incremento" onChange={this.props.handleChange} /></p>
                    <p>Credito: <input type="text" name="credito" value={credito} readonly/></p>
                    <p><input type ="button" value="Inserisci" onClick={this.props.handleClick} /></p>
                    </form>
            </div>
        );
    }
}
