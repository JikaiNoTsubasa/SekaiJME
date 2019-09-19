package fr.triedge.sekai.client.controller;

import java.io.IOException;

import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;

import fr.triedge.sekai.client.ui.UI;
import fr.triedge.sekai.common.net.MSGClientAskLoginServer;
import fr.triedge.sekai.common.utils.LoginToken;

public class OldSekaiClient {//extends SimpleApplication {
	
	private Client connection;

	public static void main(String[] args) {
		OldSekaiClient app = new OldSekaiClient();
		app.init();
		//app.start(JmeContext.Type.Canvas);
	}

	public void init() {
		// Register messages
		Serializer.registerClass(MSGClientAskLoginServer.class);
		
		// Contact server
		connectToServer();
		
		// Request login
		requestloginToServer();
	}
	
	public void requestloginToServer() {
		LoginToken token = UI.showLoginScreen();
		if (token == null) {
			UI.error("Login token is null");
			return;
		}
		MSGClientAskLoginServer msg = new MSGClientAskLoginServer(token.username, token.password);
		connection.send(msg);
		// JmeCanvasContext ctx = (JmeCanvasContext) getContext();
	}
	
	public void connectToServer() {
		try {
			connection = Network.connectToServer("localhost", 1989);
			connection.start();
		} catch (IOException e) {
			UI.error("Cannot contact server", e);
		}
	}
}
