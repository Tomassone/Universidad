'use strict';

class ConfigSection extends React.Component {
    render() {
        return (
            <div className="config">
                <form onSubmit={this.props.handleSubmit}>
                    <label> Larghezza:
                        <input type="text" name="larghezza" onChange={this.props.handleChange} />
                    </label>
                    <label> Lunghezza:
                        <input type="text" name="lunghezza" onChange={this.props.handleChange} />
                    </label>
                    <label> Numero tiri:
                        <input type="text" name="nTiri" onChange={this.props.handleChange} />
                    </label>
                    </form>
            </div>
        );
    }
}
