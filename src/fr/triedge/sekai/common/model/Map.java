package fr.triedge.sekai.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Map")
public class Map {

	private String mapName;
	
	private String mapImage;
	
	@XmlElement(name="MapName")
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	@XmlElement(name="MapImage")
	public String getMapImage() {
		return mapImage;
	}
	public void setMapImage(String mapImage) {
		this.mapImage = mapImage;
	}
}
