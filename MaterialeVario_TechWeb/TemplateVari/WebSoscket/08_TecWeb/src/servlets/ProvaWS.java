package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import beans.UpdateOperator;
import beans.OperationReq2;
import beans.OperationResp;
import java.util.*;

@ServerEndpoint("/actions")
public class ProvaWS{
	private Session session;
	private OperationReq2 richiesta;
	private OperationResp risposta;
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
        //System.out.println("Connessione aperta al server.");
    }

    @OnMessage
    public void onMessage(String message) {
        this.richiesta = gson.fromJson(message, OperationReq2.class);
        this.risposta = new OperationResp();
    	System.out.println(richiesta.toString());
        risposta.setOp1(richiesta.getOp1());
        risposta.setOp2(richiesta.getOp2());
        risposta.setTipoOp(richiesta.getOperazione());
        try {
        	compute();
        	System.out.println(risposta.toString());
			this.sendMessage(gson.toJson(risposta));
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
        if (session != null && session.isOpen()) {
            session.getBasicRemote().sendText(message);
        }
    }
}
