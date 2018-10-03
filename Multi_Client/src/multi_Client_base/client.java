package multi_Client_base;

import java.io.*;
import java.net.*;

public class client {
	private BufferedReader sysin = null;
	
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Socket s = null;
	
	private boolean flag = true;
	
	public static void main(String[] args) {
		new client().start();
	}
	
	public void start() {
		try {
			sysin = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
			System.out.print("请输入用户名：");
			String name = sysin.readLine();
			s = new Socket("localhost", 8080);
			
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(name);
			
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			ClientThread ct = new ClientThread();
			new Thread(ct).start();
			
			String str = null;
			while(flag && (str = sysin.readLine()) != null) {
				out.println(str);
			}
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(sysin != null) {
				try {
					sysin.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(s != null) {
				try {
					s.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class ClientThread implements Runnable{
		private void Receiver() {
			try {
				String str = in.readLine();
				if(str!=null) {
					if("disconnect".equalsIgnoreCase(str)) {
						stop();
						System.out.println("回车退出。");
					}
					else {
						System.out.println(str);
					}
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		private void stop() {
			flag = false;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(flag) {
				Receiver();
			}
		}
	}
}