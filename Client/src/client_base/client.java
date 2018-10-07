package client_base;

import java.net.*;
import java.io.*;

public class client {
	public static void main(String[] args) {
		String serverName = "localhost";
		int port = 8080;
		try {
			System.out.println("connect to: " + serverName + " Port: " + port);
			Socket client = new Socket(serverName, port);
			System.out.println("Host: " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			
			out.writeUTF("Hello from " + client.getLocalAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			System.out.println("Host reply: " + in);
			
			
			int[] testIntArray = {1, 2, 3, 4, 5};
			byte[] data = new byte[100];
			data = int2byte(testIntArray, testIntArray.length);
			out.write(data, 0, data.length);
			client.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] int2byte(int[] input, int length) {
		byte[] data = new byte[length];
		for(int i=0; i<length; i++) {
			data[i] = (byte) input[i];
		}
		return data;
	}
}
