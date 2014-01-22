package src.server;

import java.net.ServerSocket;
import java.io.IOException;

public class Server {
  private static final int SERVER_PORT=444;
  
  public static void main(String[] args){
    try{
      ServerSocket serverSocket=new ServerSocket(SERVER_PORT);
      while(true)
        new ServerThread(serverSocket.accept());
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}