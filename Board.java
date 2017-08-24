/* Olivia Salomon
 * 12/3/16
 * CS230 Final Project
 * Board.java
 */

import java.io.*;
import javafoundations.*;

public class Board {
  // instance vars
  String tgfFile;
  AdjMatGraph<String> board;
  
  
  // constructor
  public Board(String tgfFile) {
    
    this.tgfFile = tgfFile;
    
    board = new AdjMatGraph<String>(tgfFile);
    
  }
  
  public String getDestinationString(String tile) {
    for (int i = 0; i < board.n(); i++) {
      if (board.isArc(tile, board.getVertex(i))) {
        return board.getVertex(i);
      }
    }
    return tile;
  }
  
  //
  public int getDestinationInt(String tile) {
    for (int i = 0; i < board.n(); i++) {
      if (board.isArc(tile, board.getVertex(i))) {
        return getLocation(board.getVertex(i));
      }
    }
    return getLocation(tile);
  }
  
  public int getNetEffect(String tile) {
    return (getDestinationInt(tile) - getLocation(tile));
  }
  
  public int getLocation(String tile) {
    return Integer.parseInt(tile);
  }
  
  public String getType(String tile) {
    int netEffect = getNetEffect(tile);
    String type = "";
    if (netEffect == 0) {
      type = "normal";
    }
    else if (netEffect < 0) {
      type = "chute";
    }
    else if (netEffect > 0) {
      type = "ladder";
    }
    return type;
  }
  
  public String toString(){
    return board.toString();
  }
  
  public static void main(String[] args) {
    Board board1 = new Board("data/board63.tgf");
    System.out.println(board1);
    
    System.out.println(board1.getType("1"));
    System.out.println(board1.getType("4"));
    System.out.println(board1.getType("56"));
    
    
  }
  
}