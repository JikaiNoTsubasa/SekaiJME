package fr.triedge.sekai.common.net;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class MSGClientAskLoginServer extends AbstractMessage{

	private String username;
	private String password;
	
	public MSGClientAskLoginServer() {
	}
	
	public MSGClientAskLoginServer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
