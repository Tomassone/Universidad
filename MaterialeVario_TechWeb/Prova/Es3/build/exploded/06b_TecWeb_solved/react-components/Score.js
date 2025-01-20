'use strict';

class Score extends React.Component {
    render() {
        let nErrori = this.props.nErrori;
        let sequenzaErrori = this.props.sequenzaErrori;

        return (
            <div>
                {(nErrori !== -1) && <div> Numero errori: {nErrori}</div>}
                {(nErrori !== -1) && <div> Sequenza errori più lunga: {sequenzaErrori}</div>}
            </div>
        );
    }
}