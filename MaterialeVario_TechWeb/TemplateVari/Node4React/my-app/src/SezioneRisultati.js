import './App.css';

function SezioneRisultati({ lista, fatto}) {
  return (
    <div className="Sezione3">
      <div>SEZIONE 3</div>
      
      {    fatto && (
      <table>
          <tbody>
         {
           lista.map ((cell)=> 
              <tr  style={{ border:"1px solid black", height: "40px", width: "80px"}} >
               {cell.value}</tr>
           )}
        
             </tbody>
         </table>
         
            )}

    </div>
  );
}

export default SezioneRisultati;
