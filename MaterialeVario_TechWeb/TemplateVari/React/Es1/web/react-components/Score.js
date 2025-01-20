'use strict';

class Score extends React.Component {
    render() {
        let nErrori = this.props.nErrori;
        let isFinished = this.props.isFinished;
        let score = 0;
        
        if (nErrori < 10) {
            score = 5;
        } else {
            score = 2;
        }

        return (
            <div>
                {(nErrori !== -1) && <div> Numero errori: {nErrori}</div>}
                {isFinished && (nErrori !== -1) && <div>Punteggio: {score}</div>}
            </div>
        );
    }
}