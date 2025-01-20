package beans;

import javax.servlet.http.HttpSession;

public class Credenziali {
	private String user;
	private String password;
	private boolean isVip;
	private int tempoAttesa;
	private int tipoRichiesta; //1 concessioni edilizie; 2 quello di iscrizioni scolastiche;
	private HttpSession session;
	
	public Credenziali() {
		this.user = "user";
		this.password = "password";
		this.isVip = false;
		this.session = null;
		this.tempoAttesa = 0;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isVip() {
		return isVip;
	}

	public void setIsVip(boolean isOnline) {
		this.isVip = isOnline;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public int getTempoAttesa() {
		return tempoAttesa;
	}

	public void setTempoAttesa(int tempoAttesa) {
		this.tempoAttesa = tempoAttesa;
	}

	public int getTipoRichiesta() {
		return tipoRichiesta;
	}

	public void setTipoRichiesta(int tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}

	public boolean authenticate(Credenziali secondoUtente)
	{
		return (this.getUser().equals(secondoUtente.getUser())) && (this.getPassword().equals(secondoUtente.getPassword())); 
	}
}
