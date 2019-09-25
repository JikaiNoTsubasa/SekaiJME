package fr.triedge.sekai.pixis.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Tile")
public class Tile {

	private int x,y;
	private TileType type;
	private int chipsetX, chipsetY;
	
	public Tile() {
	}
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public TileType getType() {
		return type;
	}

	@XmlAttribute(name="Type")
	public void setType(TileType type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	@XmlAttribute(name="x")
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	@XmlAttribute(name="y")
	public void setY(int y) {
		this.y = y;
	}

	public int getChipsetY() {
		return chipsetY;
	}

	@XmlAttribute(name="chipY")
	public void setChipsetY(int chipsetY) {
		this.chipsetY = chipsetY;
	}

	public int getChipsetX() {
		return chipsetX;
	}

	@XmlAttribute(name="chipX")
	public void setChipsetX(int chipsetX) {
		this.chipsetX = chipsetX;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tile [x=").append(x).append(", y=").append(y).append(", type=").append(type)
				.append(", chipsetX=").append(chipsetX).append(", chipsetY=").append(chipsetY).append("]");
		return builder.toString();
	}
	
	public Tile copy() {
		Tile tile = new Tile();
		tile.setX(this.x);
		tile.setY(this.y);
		tile.setChipsetX(this.chipsetX);
		tile.setChipsetY(this.chipsetY);
		tile.setType(this.type);
		return tile;
	}
}
