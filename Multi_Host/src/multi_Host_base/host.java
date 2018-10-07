package multi_Host_base;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class host {
	private Map<String, ServerThread> map = new HashMap<String, ServerThread>();
	
	public static void main(String[] args) throws IOException {
		new host().start();
	}
	
	@SuppressWarnings("null")
	public void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		Socket socket = null;
		try {
			while(true) {
				System.out.println(serverSocket.getLocalPort());
				socket = serverSocket.accept();
				ServerThread st = new ServerThread(socket);
				new Thread(st).start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(serverSocket != null) {
					serverSocket.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class ServerThread implements Runnable{
		private Socket socket = null;
		private String name = null;
		private BufferedReader in = null;
		private PrintWriter out = null;
		private boolean flag = true;
		
		public ServerThread(Socket socket) throws IOException {
			this.socket = socket;
			this.out = new PrintWriter(socket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.name = in.readLine();
			System.out.println(name + "已准备！");
			map.put(name, this);
			send(name+"上线了");
		}
		
		public void send(String msg) {
			for(ServerThread thread : map.values()) {
				thread.out.println(msg);
			}
		}
		
		public void Receiver() throws IOException{
			String str = null;
			while((str = in.readLine()) != null) {
				if("quit".equalsIgnoreCase(str)){
					stop();
					out.println("disconnect");
					break;
				}
				send(name+str);
			}
		}

		public void stop() {
			map.remove(name);
			send(name+"已经离开！");
			this.flag = false;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(this.flag) {
					Receiver();
				}
			}catch(SocketException e) {
				stop();
			}catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(socket != null) {
						socket.close();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
