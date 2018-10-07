package host_base;

import java.net.*;
import java.io.*;
import java.util.*;

public class host extends Thread {
	private ServerSocket serverSocket;
	public int[] cards = new int[55];
	
	public host(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		for(int i=1; i<=54; i++) {
			this.cards[i] = 1;
		}
	}
	
	public void printInts() {
		for(int i=1; i<=54; i++) {
			System.out.println(this.cards[i]);
		}
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println(this.serverSocket.getLocalSocketAddress());
				System.out.println("wait for connecting, port: " + this.serverSocket.getLocalPort());
				Socket server = serverSocket.accept();
				System.out.print("the client is: " + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println(in.readUTF());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("thanks for connecting " + server.getLocalSocketAddress() + "\nGoodBye!");
				
				byte[] bytes = new byte[1024];
				int size = in.read(bytes);
				printByteArray(bytes, size);
				server.close();
			}catch(SocketTimeoutException s) {
				System.out.println("Time out!");
				break;
			}catch(IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	
	public static void printByteArray(byte[] data_byte, int length) {
		for(int i=0; i<length; i++) {
			System.out.println(data_byte[i]);
		}
	}
	
	public static void main(String[] args) {
		int port = 8080;
		try {
			Thread t = new host(port);
			t.run();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
