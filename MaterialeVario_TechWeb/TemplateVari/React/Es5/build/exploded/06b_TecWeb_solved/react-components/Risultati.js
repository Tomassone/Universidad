'use strict';

function Risultati(props) {
    let storico = props.storico;
    let endGame = props.endGame;
    let display1 = props.display1;
    let display2 = props.display2;
    let display3 = props.display3;
    let addedLine = display1 + " - " + display2 + " - " + display3 + "; punteggio: " + storico[storico.length - 1] + ".";

    let result = [];
    
    if (endGame)
    	result.push(<p> Giocata corrente: {addedLine} </p>);
    result.push(<p> Storico: </p>);
    for (var i = 0; i < storico.length; i++) {
        result.push(<p name={i} key={i}> {storico[i]} </p>);
    }
    return <div>{result}</div>;
}