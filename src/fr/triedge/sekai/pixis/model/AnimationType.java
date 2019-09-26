package fr.triedge.sekai.pixis.model;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum AnimationType {

	IDLE_DOWN,
	IDLE_UP,
	IDLE_RIGHT,
	IDLE_LEFT,
	WALK_DOWN,
	WALK_UP,
	WALK_RIGHT,
	WALK_LEFT,
	ATK_DOWN,
	ATK_UP,
	ATK_RIGHT,
	ATK_LEFT
}
