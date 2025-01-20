package beans;

public class Credenziali {
	private String user;
	private String password;
	private String ruolo;

	public Credenziali() {
		this.user = "user";
		this.password = "password";
		this.ruolo = "cliente";
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
	
	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public boolean authenticate(Credenziali secondoUtente)
	{
		return (this.getUser().equals(secondoUtente.getUser())) && (this.getPassword().equals(secondoUtente.getPassword())); 
	}
	
	public boolean equals(Credenziali secondoUtente)
	{
		return this.authenticate(secondoUtente);
	}
}
