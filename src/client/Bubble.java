package src.client;

import java.util.Random;

import java.io.Serializable;

enum Color { 
  RED,GREEN,BLUE;
  public static Color getRandom(){
    return values()[new Random().nextInt(values().length)];
  }
}

public class Bubble implements Serializable {
  private Color color;
  
  public Bubble(){
    color=Color.getRandom();
  }
  
  @Override
  public String toString(){
    return "Color: "+color;
  }
}