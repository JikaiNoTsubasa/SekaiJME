package fr.triedge.sekai.common.model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Map")
public class Map {

	private String mapName;
	private String mapImage;
	private String chipset;
	private ArrayList<TileInfo> tileInfos = new ArrayList<>();
	
	public void add(TileInfo info) {
		removeInfoAt(info.getX(), info.getY());
		getTileInfos().add(info);
	}
	
	private void removeInfoAt(int x, int y) {
		Iterator<TileInfo> it = getTileInfos().iterator();
		while(it.hasNext()) {
			TileInfo i = it.next();
			if (i.getX() == x && i.getY() == y)
				it.remove();
		}
	}
	
	public TileInfo getInfoAt(int x, int y) {
		for (TileInfo info : getTileInfos())
			if (info.getX() == x && info.getY() == y)
				return info;
		return null;
	}
	
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
	public ArrayList<TileInfo> getTileInfos() {
		return tileInfos;
	}
	@XmlElementWrapper(name="TileInfoLit")
	@XmlElement(name="TileInfo")
	public void setTileInfos(ArrayList<TileInfo> tileInfos) {
		this.tileInfos = tileInfos;
	}
}
