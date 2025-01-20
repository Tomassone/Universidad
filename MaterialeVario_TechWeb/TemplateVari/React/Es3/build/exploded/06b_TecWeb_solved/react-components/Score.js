'use strict';

class Score extends React.Component {
    render() {
        let punteggiMedi = this.props.punteggiMedi;
        let punteggi = this.props.punteggi;
        let isFinished = this.props.isFinished;
        
        let resultPunteggiLancio = [];
        let resultPunteggiMediLancio = [];
        let punteggioComplessivo = 0;
        
        for (var i = 0; i < punteggi.length; i++) {
	        resultPunteggiLancio.push(<p> Punteggio lancio {i + 1}: {punteggi[i]} </p>);
	    }
	    
	    for (var i = 0; i < punteggiMedi.length; i++) {
	        resultPunteggiMediLancio.push(<p> Punteggio medio lancio {i + 1}: {punteggiMedi[i]} </p>);
	    }
	    
	    for (var i = 0; i < punteggi.length; i++) {
	       punteggioComplessivo = punteggioComplessivo + punteggi[i];
	    }

        return (
            <div>
                {(isFinished) && <div> Punteggio complessivo: {punteggioComplessivo}</div>}
                {(isFinished) && <div> {resultPunteggiMediLancio}</div>}
                {(!isFinished) && <div> {resultPunteggiLancio}</div>}
            </div>
        );
    }
}