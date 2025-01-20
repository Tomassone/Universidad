'use strict';

class Slot extends React.Component {
    render() {
		let colors = this.props.colors;
		let display1 = this.props.display1;
		let display2 = this.props.display2;
		let display3 = this.props.display3;
        return (
            <div className="slot">
                <form onSubmit={this.props.handleSubmit}>
                	<p><input type="text" name="display1" value={ display1 } style={{backgroundColor: colors[0]}} readonly/>
                    <input type="text" name="display2" value={ display2 } style={{backgroundColor: colors[1]}} readonly/>
                   	<input type="text" name="display3" value={ display3 } style={{backgroundColor: colors[2]}} readonly/></p>
                    <p><input type ="button" value="Gioca" onClick={this.props.handleClick} /></p>
                    </form>
            </div>
        );
    }
}