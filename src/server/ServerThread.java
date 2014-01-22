package src.server;

import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import src.client.Bubble;

public class ServerThread extends Thread {
  private Socket socket;
  private ObjectInputStream in;    //citanje iz socketa
  private ObjectOutputStream out;  //pisanje u socket
  
  public ServerThread(Socket arg){
    try{
      socket=arg;
      in=new ObjectInputStream(socket.getInputStream());
      out=new ObjectOutputStream(socket.getOutputStream());
    }
    catch(IOException e){
      e.printStackTrace();
    }
    start();  //pokrecemo nit!
  }
  
  @Override
  public void run(){
    try{
      System.out.println("SERVER - Name of client: "+(String)in.readObject());
      Bubble bubble=(Bubble)in.readObject();
      System.out.println("SERVER - Object:\n"+bubble);
      
      System.out.println("SERVER - Now, I will send bubble back!");
      out.writeObject(bubble);
      System.out.println((String)in.readObject());
      out.writeObject("SERVER - Bye.");
      
      in.close();
      out.close();
      socket.close();
    }
    catch(ClassNotFoundException e){
      e.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}