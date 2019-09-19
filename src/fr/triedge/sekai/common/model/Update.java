package fr.triedge.sekai.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Update")
public class Update {

	private String version;
	private String zipPath;
	
	public Update() {
		this("0");
	}
	
	public Update(String version) {
		setVersion(version);
	}

	public String getVersion() {
		return version;
	}

	@XmlElement(name="Version")
	public void setVersion(String version) {
		this.version = version;
	}

	public String getZipPath() {
		return zipPath;
	}

	@XmlElement(name="ZipPath")
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
}
