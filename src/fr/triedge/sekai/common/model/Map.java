package fr.triedge.sekai.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Map")
public class Map {

	private String mapName;
	private String mapImage;
	private String chipset;
	
	public String getMapName() {
		return mapName;
	}
	@XmlElement(name="MapName")
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	public String getMapImage() {
		return mapImage;
	}
	@XmlElement(name="MapImage")
	public void setMapImage(String mapImage) {
		this.mapImage = mapImage;
	}
	public String getChipset() {
		return chipset;
	}
	@XmlElement(name="Chipset")
	public void setChipset(String chipset) {
		this.chipset = chipset;
	}
}
