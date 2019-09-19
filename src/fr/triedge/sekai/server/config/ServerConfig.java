package fr.triedge.sekai.server.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ServerConfig")
public class ServerConfig {

	private int port;

	public int getPort() {
		return port;
	}

	@XmlElement(name="port")
	public void setPort(int port) {
		this.port = port;
	}
}
