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
                    this.props.cars.map((car) =>
                    <div>
                        <i>{car.col}</i> - {car.pos === 0? "partenza" : 
                                        car.pos >= 10? "traguardo":
                                        car.pos
                                    } {this.props.ended? " -   velocità:  " + (car.pos/(this.props.calls)).toFixed(3) + " cas/s": ""}
                        <br></br>
                    </div>
                )
                }
                </fieldset>
            </div>
        );
    }
}
