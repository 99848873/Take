package com.take6.system;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建主机
 * @version 1.0
 * @author 余周锦
 * 2015-1-25
*/
public class CreatHost {
	
	private ServerSocket server;
//	private Socket client;
	
	public void creat(){
		
		try {
			
			server = new ServerSocket(8998);
			
			while(true){
//				client = server.accept();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	
	}
	
//	private class SocketThread implements Runnable{
//		
//		private Socket socket;
//		
//		public SocketThread( Socket socket){
//			this.socket = socket;
//		}
//
//		@Override
//		public void run() {
//			
//			
//		}
//		
//		
//	}

}
