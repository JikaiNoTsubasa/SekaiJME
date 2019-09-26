package fr.triedge.sekai.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement(name="Character")
public class Character {

	private int id;
	private String name;
	private String currentMap;
	private String chipset;
	private int height, width;

	public String getName() {
		return name;
	}

	@XmlElement(name="Name")
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

	public String getCurrentMap() {
		return currentMap;
	}

	@XmlElement(name="CurrentMap")
	public void setCurrentMap(String currentMap) {
		this.currentMap = currentMap;
	}

	public String getChipset() {
		return chipset;
	}

	@XmlElement(name="Chipset")
	public void setChipset(String chipset) {
		this.chipset = chipset;
	}

	public int getWidth() {
		return width;
	}

	@XmlElement(name="Width")
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	@XmlElement(name="Height")
	public void setHeight(int height) {
		this.height = height;
	}

}
