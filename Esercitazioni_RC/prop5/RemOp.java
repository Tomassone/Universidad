
/**
 * Interfaccia remota di servizio
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemOp extends Remote {

	public int conta_righe(String remotePath, int min_words) throws RemoteException;

	public Esito elimina_riga(String remotePath, int line_number) throws RemoteException;

}