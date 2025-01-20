class TopBar extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div class="top-bar">
                <button onClick={this.props.switchTable} name="tab1" >{this.props.tableNames[0]}</button>
                <button onClick={this.props.switchTable} name="tab2" >{this.props.tableNames[1]}</button>
                <button onClick={this.props.switchTable} name="tab3" >{this.props.tableNames[2]}</button>
            </div>
        );
    }
}
