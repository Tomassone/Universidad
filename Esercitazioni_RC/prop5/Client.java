import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class Client {

	public static void main(String[] args) {

		final int REGISTRYPORT = 1099;
		String registryHost = null; // host remoto con registry
		String serviceName = "RemOp";
		int port = -1; // porta da usare se viene inserito un valore come argomento
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		// Controllo dei parametri della riga di comando
		if (args.length == 2) {
			try {
				port = Integer.parseInt(args[1]);
			}
			catch (NumberFormatException e) {
				System.out.println("Errore: registryPort deve essere una porta valida");
				System.exit(1);
			}
			if (port < 1024 || port < 65535) {
				System.out.println("Errore: registryPort deve essere una porta valida");
				System.exit(1);
			}
		}
		else if (args.length != 1) { // se non è 2 deve essere per forza 1
			System.out.println("Sintassi: RMI_Registry_IP ServiceName");
			System.exit(1);
		}
		registryHost = args[0];

		System.out.println("Invio richieste a " + registryHost + " " + (port > 0 ? port : REGISTRYPORT));

		// Connessione al servizio RMI remoto
		try {
			String completeName = "//" + registryHost + ":" + (port > 0 ? port : REGISTRYPORT) + "/" + serviceName;
			RemOp serverRMI = (RemOp) Naming.lookup(completeName);
			System.out.println("ClientRMI: Servizio \"" + serviceName + "\" connesso");

			System.out.println("\nRichieste di servizio fino a fine file");

			String service;
			System.out.print("Servizio (C = Conta Righe, E = Elimina Riga): ");

			/* ciclo accettazione richieste utente */
			while ((service = stdIn.readLine()) != null) {

				if (service.equals("C")) {
					System.out.print("Nome del file remoto? ");
					String nome_file = stdIn.readLine();

					System.out.print("Soglia per cui contare quante righe con almeno Soglia parole? ");
					int soglia = -1;
					boolean ok = false;
					while (ok != true) {
						try {
							soglia = Integer.parseInt(stdIn.readLine());
							if (soglia >= 0) ok = true;	
						}
						catch (Exception e) {}
					}

					int res = serverRMI.conta_righe(nome_file, soglia);
					System.out.println("Il file remoto " + nome_file + " contiene " + res + " righe con più di " + soglia + " parole");
				} // C = Conta Righe

				else if (service.equals("E")) {
					System.out.print("Nome del file remoto? ");
					String nome_file = stdIn.readLine();

					System.out.print("Numero riga da eliminare nel file remoto? ");
					int riga = -1;
					boolean ok = false;
					while (ok != true) {
						try {
							riga = Integer.parseInt(stdIn.readLine());
							if (riga > 0) ok = true;
						}
						catch (Exception e) {}
					}

					Esito esito = serverRMI.elimina_riga(nome_file, riga);
					System.out.println(esito.toString());
				} // E = Elimina Riga

				else
					System.out.println("Servizio non disponibile");

				System.out.print("Servizio (C = Conta Righe, E = Elimina Riga): ");
			} // while (!EOF), fine richieste utente

		} catch (NotBoundException nbe) {
			System.err.println("ClientRMI: il nome fornito non risulta registrato; " + nbe.getMessage());
			nbe.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.err.println("ClientRMI: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

	}
}