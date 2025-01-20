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
	private UpdateOperator richiesta;
	private OperationResp risposta;
	private UpdateReq daInoltrare;
	private Gson gson = new Gson();
	
	private void compute()
	{
		switch (risposta.getTipoOp())
		{
			case "+":
				risposta.setRisultato(risposta.getOp1() + risposta.getOp2());
				risposta.setSuccess(true);
				break;
			case "-":
				risposta.setRisultato(risposta.getOp1() - risposta.getOp2());
				risposta.setSuccess(true);
				break;
			case "*":
				risposta.setRisultato(risposta.getOp1() * risposta.getOp2());
				risposta.setSuccess(true);
				break;
			case "/":
				risposta.setRisultato(risposta.getOp1() / risposta.getOp2());
				risposta.setSuccess(true);
				break;
		}
	}

	//action="ws://localhost:8080/08_TecWeb/actions"
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Connessione aperta al server.");
    }

    @OnMessage
    public void onMessage(String message) {
    	if (message.contains("valore")) //sto ricevendo una richiesta di aggiornamento
    	{
    		try {
				this.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else
    	{
    		this.richiesta = gson.fromJson(message, UpdateOperator.class);
	        this.risposta = new OperationResp();
	    	System.out.println(richiesta.toString());
	        risposta.setOp1(richiesta.getOp1());
	        risposta.setOp2(richiesta.getOp2());
	        risposta.setTipoOp(richiesta.getOperazione());
	        risposta.setValid(true);
	        try {
	        	compute();
	        	System.out.println(risposta.toString());
				this.sendMessage(gson.toJson(risposta));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    	for (Session s : session.getOpenSessions())
	        if (s != null && s.isOpen()) {
	            s.getBasicRemote().sendText(message);
	        }
    }
}