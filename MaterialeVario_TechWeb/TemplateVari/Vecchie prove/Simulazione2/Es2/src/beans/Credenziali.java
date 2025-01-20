package beans;

import javax.servlet.http.HttpSession;

public class Credenziali {
	private String user;
	private String password;
	private String idGroup;
	private boolean isAdmin;
	private int daysSinceLastModification;
	private int tries;
	private HttpSession session;
	
	public Credenziali() {
		this.user = "user";
		this.password = "password";
		this.idGroup = "idGroup";
		this.isAdmin = false;
		this.daysSinceLastModification = 0;
		this.session = null;
		this.tries = 0;
	}
	
	public int getTries()
	{
		return this.tries;
	}
	
	public void setTries(int value)
	{
		this.tries = value;
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

	public String getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isOnline) {
		this.isAdmin = isOnline;
	}

	public boolean isPasswordExpired() {
		return (daysSinceLastModification > 60);
	}

	public void setDaysSinceLastModification(int daysSinceLastModification) {
		this.daysSinceLastModification = daysSinceLastModification;
	}
	
	public int getDaysSinceLastModification() {
		return this.daysSinceLastModification;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public boolean authenticate(Credenziali secondoUtente)
	{
		return (this.getUser().equals(secondoUtente.getUser())) && (this.getPassword().equals(secondoUtente.getPassword())); 
	}
}
