class Section2 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                {
                    this.props.schedina.map((item) =>
                        <p>
                            <input 
                                type="text" 
                                value={""+item.value}
                                style={{backgroundColor: item.color}}
                                readOnly
                            />
                            <br></br>
                        </p>
                    )
                }
                </fieldset>
            </div>
        );
    }
}
