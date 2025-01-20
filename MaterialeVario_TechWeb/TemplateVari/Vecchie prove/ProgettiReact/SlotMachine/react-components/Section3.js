class Section3 extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <fieldset>
                <legend>{this.props.sectionName}</legend>
                {
                    this.props.risultati.map((item, idx) => 
                    <div>
                        {idx+1 })  {item[0]} - {item[1] > 0? item[1]: ""}
                    </div>

                )}
                </fieldset>
            </div>
        );
    }
}
