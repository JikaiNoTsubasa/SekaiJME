package fr.triedge.sekai.common.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Update")
public class Update {

	private String version;
	private ArrayList<UpdateElement> elements = new ArrayList<>();
	
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

	public ArrayList<UpdateElement> getElements() {
		return elements;
	}

	@XmlElementWrapper(name="Elements")
	@XmlElement(name="Element")
	public void setElements(ArrayList<UpdateElement> elements) {
		this.elements = elements;
	}
}
