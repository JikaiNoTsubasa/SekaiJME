package fr.triedge.sekai.server.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement
public class User {

	private int id;
	private String username;
	private String password;
	private ArrayList<Character> characters = new ArrayList<>();
	
	public String getUsername() {
		return username;
	}
	@XmlElement(name="username")
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	@XmlElement(name="password")
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Character> getCharacters() {
		return characters;
	}
	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}
}
