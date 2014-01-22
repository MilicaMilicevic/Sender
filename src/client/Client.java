package src.client;

import java.net.Socket;
import java.net.InetAddress;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.util.Random;

public class Client extends Thread {
  private static final int SERVER_PORT=444;
  
//Objekti klasa ObjectOutputStream i ObjectInputStream se koriste i za upise u mreznu konekciju!
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private Socket clientSocket;
  
  private String name;
  
  public Client(){
    try{
      clientSocket=new Socket(InetAddress.getLocalHost(),SERVER_PORT);
      out=new ObjectOutputStream(clientSocket.getOutputStream());
      in=new ObjectInputStream(clientSocket.getInputStream());
   
      name="Client"+new Random().nextInt(5); 
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args){
    new Client().start();
    new Client().start();
  }
  
  @Override
  public void run(){
    try{
      sendString(name);
      System.out.println(name+" - I've just sent my name to Server.");
      sendBubble(new Bubble());
      System.out.println(name+" - Bubble is also sent.");
      System.out.println(name+" - I've got Bubble - "+(Bubble)in.readObject()+" back.");
      out.writeObject(name+" - Bye.");
      System.out.println((String)in.readObject());
      
      out.close();
      in.close();
      clientSocket.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
    catch(ClassNotFoundException e){
      e.printStackTrace();
    }
  }
  
  private void sendString(String arg) throws IOException {
    out.writeObject(arg);
  }
  
  private void sendBubble(Bubble arg) throws IOException {
    out.writeObject(arg);
  }  
}