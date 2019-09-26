package fr.triedge.sekai.pixis.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CharacterAnimationFrame")
public class CharacterAnimationFrame {

	private int chipsetX, chipsetY;

	public int getChipsetX() {
		return chipsetX;
	}

	@XmlAttribute(name="ChipsetX")
	public void setChipsetX(int chipsetX) {
		this.chipsetX = chipsetX;
	}

	public int getChipsetY() {
		return chipsetY;
	}

	@XmlAttribute(name="ChipsetY")
	public void setChipsetY(int chipsetY) {
		this.chipsetY = chipsetY;
	}
}
