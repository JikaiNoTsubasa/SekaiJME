package fr.triedge.sekai.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement(name="Character")
public class Character {

	private int id;
	private String name;

	public String getName() {
		return name;
	}

	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
}
