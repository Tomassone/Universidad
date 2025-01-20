'use strict';

class Random extends React.Component {
    render() {
		let extracted = this.props.extracted;
		let finalized = this.props.finalized;
		let val_1g = this.props.val_1g;
		let val_2g = this.props.val_2g;
		let val_3g = this.props.val_3g;
		let val_4g = this.props.val_4g;
		let val_5g = this.props.val_5g;
        return (
            <div className="config">
                <form onSubmit={this.props.handleSubmit}>
                    {(finalized) && <input type ="button" value="Estrai" onClick={this.props.handleClick} />}                    
                    {(extracted && finalized) && <p> Primo valore:
                        <input type="text" name="val_1g" value={val_1g} readonly />
                    </p> }
                    {(extracted && finalized) && <p> Secondo valore:
                        <input type="text" name="val_2g" value={val_2g} readonly />
                    </p> }
                    {(extracted && finalized) && <p> Terzo valore:
                        <input type="text" name="val_3g" value={val_3g} readonly />
                    </p> }
                    {(extracted && finalized) && <p> Quarto valore:
                        <input type="text" name="val_4g" value={val_4g} readonly />
                    </p> }
                    {(extracted && finalized) && <p> Quinto valore:
                        <input type="text" name="val_5g" value={val_5g} readonly />
                    </p>}
                    </form>
            </div>
        );
    }
}