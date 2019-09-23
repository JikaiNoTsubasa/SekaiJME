package fr.triedge.sekai.pixis.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class EditableMap {

	
	private ArrayList<Tile> goundTiles = new ArrayList<>();
	private ArrayList<Tile> objectTiles = new ArrayList<>();
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

	public ArrayList<Tile> getGoundTiles() {
		return goundTiles;
	}

	@XmlElementWrapper(name="GroundTiles")
	@XmlElement(name="GroundTile")
	public void setGoundTiles(ArrayList<Tile> goundTiles) {
		this.goundTiles = goundTiles;
	}

	public ArrayList<Tile> getObjectTiles() {
		return objectTiles;
	}

	@XmlElementWrapper(name="ObjectTiles")
	@XmlElement(name="ObjectTile")
	public void setObjectTiles(ArrayList<Tile> objectTiles) {
		this.objectTiles = objectTiles;
	}
}
