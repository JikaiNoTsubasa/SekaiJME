package fr.triedge.sekai.pixis.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CharacterAnimation")
public class CharacterAnimation {

	private ArrayList<CharacterAnimationFrame> frames = new ArrayList<>();
	private AnimationType animationType;

	public ArrayList<CharacterAnimationFrame> getFrames() {
		return frames;
	}

	@XmlElementWrapper(name="FrameList")
	@XmlElement(name="Frame")
	public void setFrames(ArrayList<CharacterAnimationFrame> frames) {
		this.frames = frames;
	}

	public AnimationType getAnimationType() {
		return animationType;
	}

	@XmlElement(name="Type")
	public void setAnimationType(AnimationType animationType) {
		this.animationType = animationType;
	}
}
