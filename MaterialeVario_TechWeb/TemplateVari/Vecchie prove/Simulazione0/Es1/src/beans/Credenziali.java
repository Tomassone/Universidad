package beans;

public class Credenziali {
	private String user;
	private String password;
	private String idGroup;
	private boolean hasFinalized;
	
	public Credenziali() {
		this.user = "user";
		this.password = "password";
		this.idGroup = "idGroup";
		this.hasFinalized = false;
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
	
	public boolean hasFinalized() {
		return hasFinalized;
	}

	public void setHasFinalized(boolean isOnline) {
		this.hasFinalized = isOnline;
	}

	public boolean authenticate(Credenziali secondoUtente)
	{
		return (this.getUser().equals(secondoUtente.getUser())) && (this.getPassword().equals(secondoUtente.getPassword())); 
	}
}
