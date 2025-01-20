class Settore extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
                <fieldset style={{border: "3px solid " + this.props.color, margin:"5px", width: "33%", height:"100%"}}>
                <legend>{this.props.sectionName}</legend>
                {
                    this.props.items.map((item) => 
                        <button onClick={this.props.switch} name={JSON.stringify(item)}>{item.name}; {item.qt}</button>
                )
                } 
            </fieldset>
        );
    }
}
