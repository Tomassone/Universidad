import './App.css';

function SezioneCredito({aggiorna, valore}) {
  return (
    <div className="Sezione1">
      <div>SEZIONE 1</div>
      <label>T-incremento </label>
        <input type="text"   style={ {height: "20px", width: "60px" }} id="incremento"></input><br></br>
        <label>T-credito</label>
        <input type="text" style={ {height: "20px", width: "60px" }} readOnly id="credito" value={valore}></input><br></br>
        <button onClick={aggiorna}>Invia</button>
    </div>
  );
}

export default SezioneCredito;
