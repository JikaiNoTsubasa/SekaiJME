package fr.triedge.sekai.server;

public class RunServer {

	public static void main(String[] args) {
		SekaiServer server = new SekaiServer();
		Thread th = new Thread(server);
		th.start();
	}

}
