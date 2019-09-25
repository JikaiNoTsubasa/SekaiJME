package fr.triedge.sekai.pixis.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Sprite")
public class Sprite {

	private int posXonSheet,posYonSheet;
	private String imageData;

	public int getPosXonSheet() {
		return posXonSheet;
	}

	@XmlAttribute(name="posXonSheet")
	public void setPosXonSheet(int posXonSheet) {
		this.posXonSheet = posXonSheet;
	}

	public int getPosYonSheet() {
		return posYonSheet;
	}

	@XmlAttribute(name="posYonSheet")
	public void setPosYonSheet(int posYonSheet) {
		this.posYonSheet = posYonSheet;
	}

	public String getImageData() {
		return imageData;
	}

	@XmlElement(name="ImageData")
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
}
