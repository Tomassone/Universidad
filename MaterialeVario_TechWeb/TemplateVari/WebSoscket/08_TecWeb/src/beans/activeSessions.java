package beans;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.*;

public class activeSessions {
	private List<Session> sessions;
	
	public activeSessions()
	{
		this.sessions = new ArrayList<Session>();
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	public boolean addSession(Session session)
	{
		return this.sessions.add(session);
	}
	
	public boolean removeSession(Session session)
	{
		return this.sessions.remove(session);
	}
}
