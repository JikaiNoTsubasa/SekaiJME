package fr.triedge.sekai.server.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="model")
public class Model {

	private ArrayList<User> users = new ArrayList<>();

	public ArrayList<User> getUsers() {
		return users;
	}

	@XmlElementWrapper
    @XmlElement(name="users")
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
