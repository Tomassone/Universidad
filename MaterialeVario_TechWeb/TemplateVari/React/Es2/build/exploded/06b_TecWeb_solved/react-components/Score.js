'use strict';

class Score extends React.Component {
    render() {
        let nErrori = this.props.nErrori;
        let nSuccessi = this.props.nSuccessi;

        return (
            <div>
                {(nErrori !== -1) && <div> Numero errori: {nErrori}</div>}
                {(nErrori !== -1) && <div> Numero successi: {nSuccessi}</div>}
            </div>
        );
    }
}