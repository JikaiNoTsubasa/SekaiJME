package fr.triedge.sekai.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="UpdateElement")
public class UpdateElement {

	private String sitePath;
	private String localPath;
	
	public UpdateElement() {
	}
	
	
	public UpdateElement(String sitePath, String localPath) {
		super();
		this.sitePath = sitePath;
		this.localPath = localPath;
	}

	public String getSitePath() {
		return sitePath;
	}
	@XmlElement(name="SitePath")
	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}
	public String getLocalPath() {
		return localPath;
	}
	@XmlElement(name="LocalPath")
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
}
