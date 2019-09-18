package fr.triedge.sekai.client;

import com.jme3.app.SimpleApplication;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.JmeContext;

import fr.triedge.sekai.common.net.MSGClientAskLoginServer;

public class SekaiClient extends SimpleApplication {

	public static void main(String[] args) {
		SekaiClient app = new SekaiClient();
		app.start(JmeContext.Type.Canvas);
	}

	@Override
	public void simpleInitApp() {
		Serializer.registerClass(MSGClientAskLoginServer.class);
	}
}
