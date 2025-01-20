import './App.css';

function SezioneSlot({ valore,avvia, mappa, finalizza}) {
  return (
    <div className="Sezione2">
      <div>SEZIONE 2</div>
      
      <button  onClick={()=>{
                            if(valore>0){  //se onclick nel blocco ho ancora dei lanci allora lancio il lago
                              avvia()
                            }
                          }}>Avvia</button>
      
      {    finalizza && (
      <table>
          <tbody><tr>
         {
           mappa.map ((cell)=> 
            <td  id={JSON.stringify(cell.coords.x)}>
                          <input type="text" id={"campo"+cell.coords.x} 
                          style={ {border:"1px solid black",height: "40px", width: "40px",  backgroundColor: cell.color}} 
                          readOnly 
                          value={cell.value}
                          ></input>
                     </td>
          )
        }
        </tr>
             </tbody>
         </table>
            )}
            
    </div>
  );
}

export default SezioneSlot;
