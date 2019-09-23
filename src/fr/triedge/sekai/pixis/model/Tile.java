package fr.triedge.sekai.pixis.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Tile")
public class Tile {

	private int x,y;
	private TileType type;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public TileType getType() {
		return type;
	}

	//@XmlElement(name="Type")
	public void setType(TileType type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	@XmlElement(name="x")
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	@XmlElement(name="y")
	public void setY(int y) {
		this.y = y;
	}
	
}
