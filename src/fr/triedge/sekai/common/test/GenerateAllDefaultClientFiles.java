package fr.triedge.sekai.common.test;

import fr.triedge.sekai.client.controller.DefaultFactory;

public class GenerateAllDefaultClientFiles {

	public static void main(String[] args) {
		DefaultFactory.generateDefaultClientConfigFile("client/config/client.xml");
		System.out.println("Generated client/config/client.xml");
	}

}
