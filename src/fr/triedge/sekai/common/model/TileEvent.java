package fr.triedge.sekai.common.model;

import javax.xml.bind.annotation.XmlElement;

public class TileEvent {

	private TileEventType eventType;

	public TileEventType getEventType() {
		return eventType;
	}

	@XmlElement(name="EventType")
	public void setEventType(TileEventType eventType) {
		this.eventType = eventType;
	}
}
