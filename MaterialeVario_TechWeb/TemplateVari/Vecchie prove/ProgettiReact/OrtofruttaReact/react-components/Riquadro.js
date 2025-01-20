class Riquadro extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                <div style={{display:"flex"}}>
                    <Settore 
                        sectionName="Frutta e verdura"
                        color={this.props.colors[0]}
                        items={this.props.items.filter((item) => item.type === "fv")}
                        switch={this.props.switch}
                    />
                    <Settore 
                        sectionName="Carne e pesce"
                        color={this.props.colors[1]}
                        items={this.props.items.filter((item) => item.type === "cp")}
                        switch={this.props.switch}
                    />
                    <Settore 
                        sectionName="Lunga conservazione"
                        color={this.props.colors[2]}
                        items={this.props.items.filter((item) => item.type === "lc")}
                        switch={this.props.switch}
                    />
                </div>
                </fieldset>
            </div>
        );
    }
}
