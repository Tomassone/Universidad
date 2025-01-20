package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import beans.*;
import java.util.*;

@ServerEndpoint("/actions2")
public class ProvaWS_2{
	private Session session;
	private Gson gson = new Gson();
	private TipoMex messaggio;
	private static String storicoCompleto;

	//action="ws://localhost:8080/08_TecWeb/actions"
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.messaggio = new TipoMex();
        this.storicoCompleto = "";
        System.out.println("Connessione aperta al server.");
    }

    @OnMessage
    public void onMessage(String message) {
    	this.messaggio = gson.fromJson(message, TipoMex.class);
		try {
			this.sendMessage(gson.toJson(messaggio));
			System.out.println("Messaggio {" + this.messaggio.getMex() + "} inviato, tipo: " + messaggio.getTipo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @OnClose
    public void onClose(Session session) {
        //System.out.println("Connessione chiusa.");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        //System.err.println("Errore: " + throwable.getMessage());
    }

    public void sendMessage(String message) throws Exception {
    	if (!this.messaggio.getMex().equals("nothing"))
    		this.storicoCompleto = this.messaggio.getMex() + "; " + this.storicoCompleto;
    	System.out.println("Storico completo: " + this.storicoCompleto);
    	if (this.messaggio.getTipo() == 0) {
	    	int nFortunati = (int) (1 * session.getOpenSessions().size());
	    	for (Session s : session.getOpenSessions())
		        if (s != null && s.isOpen() && nFortunati != 0) {
		            if (Math.random() > 0.5) {
		            	nFortunati--;
			            s.getBasicRemote().sendText(message);
		            }
		        }
    	}
    	else {
    		messaggio.setMex(this.storicoCompleto);
    		messaggio.setTipo(1);
    		for (Session s : session.getOpenSessions()) {
		        if (s != null && s.isOpen()) {
			            s.getBasicRemote().sendText(gson.toJson(messaggio));
		            }
		        }
    	}
    }
}