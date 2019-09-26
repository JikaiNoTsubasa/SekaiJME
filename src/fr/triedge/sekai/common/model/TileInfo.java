package fr.triedge.sekai.common.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TileInfo")
public class TileInfo {

	private boolean walkable;
	private int x,y;
	private ArrayList<TileEvent> tileEvents = new ArrayList<>();

	public boolean isWalkable() {
		return walkable;
	}

	@XmlElement(name="IsWalkable")
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public ArrayList<TileEvent> getTileEvents() {
		return tileEvents;
	}

	@XmlElementWrapper(name="TileEventList")
	@XmlElement(name="TileEvent")
	public void setTileEvents(ArrayList<TileEvent> tileEvents) {
		this.tileEvents = tileEvents;
	}

	public int getX() {
		return x;
	}

	@XmlAttribute(name="TileX")
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	@XmlAttribute(name="TileY")
	public void setY(int y) {
		this.y = y;
	}
}
