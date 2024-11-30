
/**
 * ServerImpl.java
 * 		Implementazione del server
 * */

import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements RemOp {

	// Costruttore
	public ServerImpl() throws RemoteException {
		super();
	}

	public int conta_righe(String remotePath, int min_words) throws RemoteException
	{
		System.out.println("Server RMI: richiesta conteggio parole sul file remoto");
		System.out.println("remotePath = " + remotePath);
		System.out.println("min_words = " + min_words);
		File textFile = new File(remotePath);
		int result = 0; 
		if (!(textFile.isFile()))
		{
			System.out.println("Il file indicato non esiste!");
			throw new RemoteException();
		}
		else
			System.out.println("Il file indicato esiste");

		if (!(textFile.getName().endsWith(".txt")))
		{
			System.out.println("Il file indicato non è di testo!");
			throw new RemoteException();
		}
		else
			System.out.println("Il file indicato è di testo");

		try {
			BufferedReader bf = new BufferedReader(new FileReader(textFile));
			String line = null; 
			while ((line = bf.readLine()) != null)
				if (line.split(" ").length > min_words)
					result = result + 1;
		} catch  (IOException e) {
			// TODO: handle exception
		}
		
		return result;
	}

	synchronized public Esito elimina_riga(String remotePath, int line_number) throws RemoteException
	{
		System.out.println("Server RMI: richiesta eliminazione riga sul file remoto");
		System.out.println("remotePath = " + remotePath);
		System.out.println("line_number = " + line_number);
		File textFile = new File(remotePath);
		if (!(textFile.isFile()))
		{
			System.out.println("Il file indicato non esiste!");
			throw new RemoteException();
		}
		else
			System.out.println("Il file indicato esiste");

		if (!(textFile.getName().endsWith(".txt")))
		{
			System.out.println("Il file indicato non è di testo!");
			throw new RemoteException();
		}
		else
			System.out.println("Il file indicato è di testo");
		if (line_number <= 0)
		{
			System.out.println("Il numero di righe indicato è nullo o negativo!");
			throw new RemoteException();
		}

		int currentLine = 1;
		try {
			File tempFile = new File("temp.txt");
			BufferedReader bf = new BufferedReader(new FileReader(textFile));
			BufferedWriter tempBf = new BufferedWriter(new FileWriter(tempFile));
			String line = null; 
			while ((line = bf.readLine()) != null)
			{
				if (currentLine != line_number)
					tempBf.write(line + "\n");
				currentLine++;
			}
			if (currentLine < line_number)
			{
				System.out.println("Il file indicato non ha abbastanza righe!");
				throw new RemoteException();
			}
			bf.close();
			tempBf.close();
			textFile.delete();
			tempFile.renameTo(textFile);
		//tempFile.renameTo(textFile);
		} catch  (IOException e) {
			// TODO: handle exception
		}

		return new Esito(remotePath, currentLine - 2);
	}

	// Avvio del Server RMI
	public static void main(String[] args) {

		final int REGISTRYPORT = 1099;
		String registryHost = "localhost";
		String serviceName = "RemOp"; // lookup name...

		// Registrazione del servizio RMI
		String completeName = "//" + registryHost + ":" + REGISTRYPORT + "/" + serviceName;
		try {
			ServerImpl serverRMI = new ServerImpl();
			Naming.rebind(completeName, serverRMI);
			System.out.println("Server RMI: Servizio \"" + serviceName + "\" registrato");
		} catch (Exception e) {
			System.err.println("Server RMI \"" + serviceName + "\": " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}