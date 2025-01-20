'use strict';

class View extends React.Component {
    render() {
		let generated = this.props.generated;
		let isFinished = this.props.isFinished;
		let finalized = this.props.finalized;
		let result = this.props.result;
		let val_1 = this.props.val_1;
		let val_2 = this.props.val_2;
		let val_3 = this.props.val_3;
		let val_4 = this.props.val_4;
		let val_5 = this.props.val_5;
        return (
            <div className="config">
                <form onSubmit={this.props.handleSubmit}>
                	{(generated && finalized && isFinished) && <p> Risultato: {result}
                	</p> }
                    {(generated && finalized) && <p> Primo valore:
                        <input type="text" name="val_1" value={val_1} readonly />
                    </p> }
                    {(generated && finalized) && <p> Secondo valore:
                        <input type="text" name="val_2" value={val_2} readonly />
                    </p> }
                    {(generated && finalized) && <p> Terzo valore:
                        <input type="text" name="val_3" value={val_3} readonly />
                    </p> }
                    {(generated && finalized) && <p> Quarto valore:
                        <input type="text" name="val_4" value={val_4} readonly />
                    </p> }
                    {(generated && finalized) && <p> Quinto valore:
                        <input type="text" name="val_5" value={val_5} readonly />
                    </p>}
                    </form>
            </div>
        );
    }
}