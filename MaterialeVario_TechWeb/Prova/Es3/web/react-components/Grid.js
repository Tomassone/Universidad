'use strict';

function Grid(props) {
    let griglia = [];
    let colors = props.colors;
    let lunghezza = props.lunghezza;
    let larghezza = props.larghezza;
    let pos = "";
    
    for (var i = 0; i < lunghezza; i++) {
        const row = [];
        for (var j = 0; j < larghezza; j++) {
            pos = i * props.larghezza + j;
            row.push(<input type="text" name={pos} key={pos} style={{backgroundColor: colors[i][j]}} onClick={props.handleClick} />);
        }
        griglia.push(<div key={i}>{row}</div>);
    }
    griglia.push(<button onClick={props.resetGame}>Reset Game</button>);
    return <div>{griglia}</div>;

}