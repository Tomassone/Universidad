package beans;

import javax.servlet.http.HttpSession;

public class Articolo {
	private String path;
	private HttpSession wrSession;
	
	public Articolo()
	{
		this.path = "";
		this.wrSession = null;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public HttpSession getWrSession() {
		return wrSession;
	}

	public void setWrSession(HttpSession wrSession) {
		this.wrSession = wrSession;
	}
}
