package fr.triedge.sekai.client.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ClientConfig")
public class ClientConfig {

	private int serverPort;
	private String serverHost, linkUpdate, version;
	
	public int getServerPort() {
		return serverPort;
	}
	@XmlElement(name="ServerPort")
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public String getServerHost() {
		return serverHost;
	}
	@XmlElement(name="ServerHost")
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public String getLinkUpdate() {
		return linkUpdate;
	}
	@XmlElement(name="LinkUpdate")
	public void setLinkUpdate(String linkUpdate) {
		this.linkUpdate = linkUpdate;
	}
	public String getVersion() {
		return version;
	}
	@XmlElement(name="ClientVersion")
	public void setVersion(String version) {
		this.version = version;
	}
	
}
