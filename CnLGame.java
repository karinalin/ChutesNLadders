/* CnLGame.java
 * @author Karina Lin, Olivia Salomon, Teresa Li
 * 
 * Class that runs Chutes N Ladders gameplay
 */

import java.io.*;
import java.util.Random;
import java.util.Scanner;

// creates a single-player chutes and ladders game
public class CnLGame {
  
  //instance variables 
  int num; //spin number
  String[] result = new String[2];
  
  Player player;
  Board board;
  
  Spinner spinner;
  QuestionHash questions;
  
  //player stats
  int numTurns;
  int numCorrect;
  int numChutes;
  int numLadders;
  int totalMovement;
  
  /** Constructor: Instantiates the CnL Game by creating board, player, spinner, and QuestionHash
    * 
    * @param player object
    * @throws IOException
    */
  public CnLGame(Player player) throws IOException {
        
    this.player = player;
    this.numTurns = 0;
    this.numCorrect = 0;
    this.numChutes = 0;
    this.numLadders = 0;


        
    board = new Board("board63.tgf");
    spinner = new Spinner();
    questions = new QuestionHash("additionTables.csv");
  }
  
  /** 
   * Spins spinner by calling on method in Spinner.java
   * 
   * @return integer random integer between 1 and 6 inclusive
   */
  public int spin() {
    
    num = spinner.spin();
    
    System.out.println("\nYou spun a " + num + "!");
    numTurns++;
    return num;
  }
  
  /** 
   * Opens scanner that asks for user input via text field
   * 
   * @return String player-inputted answer
   */
  public String answerQuestion() {
    
    Scanner scan = new Scanner(System.in);
    return scan.nextLine();
  }
  
  /** 
   * Opens scanner that asks for user input via text field
   * 
   * @return String[] player-inputted answer
   */
  public String[] askQuestion() {
    
    //questionAnswer array is from the getRandomQuestion method in QuestionHash.java
    //and returns an array with index 0 as the question and index 1 as the answer
    
    String[] result = questions.getRandomQuestion();
    
    System.out.println("Question: " + result[0]);
    System.out.println("Answer: " + result[1]);
    
    return result;
  }
  
  /** 
   * Checks to see if the user-inputted answer is correct
   * 
   * @param questionAnswer String array with question and answer pair
   * @param answer user-inputted answer
   * @return boolean whether user-inputted answer is correct or not
   */
  public boolean checkQuestion(String[] questionAnswer, String answer) {
    
    if (answer.equals(questionAnswer[1])) {
      
      numCorrect++;
      
      System.out.println("You answered correctly.");
      return true;
    }
    
    else {
      
      System.out.println("You answered incorrectly.");
      return false;
    }
  }
  
    /** 
   * Returns the type of the tile which is taken as a parameter
   * 
   * @param currentPosition the tile which we want to know the type of
   * @return String of the type of this tile
   */
  public String getType(int currentPosition) {
    String type = board.getType(Integer.toString(currentPosition));
    return type;
  }
  
  /** 
   * Moves token by reassigning position
   * 
   * @param boolean from checkQuestion method
   * @return integer of the position of this token after this method is complete
   */
  public int moveToken(boolean correct) {
    
    int position = player.getPosition();
    
    System.out.println("num " + num);
    System.out.println("position " + position);
    
    if (correct) { //only moves forward if the answer is correct
      
      player.setPosition(position += num);
      int newPosition = player.getPosition();
      
      System.out.println("You are now on tile " + newPosition);
      return newPosition;
    }
    
    else {
      
      System.out.println("You are still on tile " + position);
      
      return position;
    }
  }
  
  /** 
   * Checks the type of the destination tile after the move after the spin and moves the token
   * the necessary tiles if the tile is a chute or a ladder
   * 
   * @param currentPosition current position of token after initial movement after spin
   * @return integer final positioning of player after turn
   */
  public int secondaryMoveToken(int currentPosition) {
    
    String type = board.getType(Integer.toString(currentPosition));
    
    System.out.println("Tile " + currentPosition + " is a " + type + ". ");
    
    if (type.equals("normal")) {
      
      return currentPosition;
    }
    else {
      if (type.equals("chute")) {
        numChutes++;
        System.out.println("numchutes: " + numChutes);
      }
      
      if (type.equals("ladder")) {
        numLadders++;
        System.out.println("numLadders: " + numLadders);
      }
      
      int newPosition = board.getDestinationInt(Integer.toString(currentPosition));
      player.setPosition(newPosition);
      
      System.out.println("You are now on tile " + newPosition);
      


      
      return newPosition;
    }
  }
  
  /** 
   * Will return false until player reaches position 63 on the board and wins
   * 
   * @return boolean whether game is over yet or not
   */
  public boolean gameOver() {
   
    boolean over = (player.getPosition() >= 63);
    
    if (over) {
    
      System.out.println("You finished the game!");
    }
    return over;
  }
  
  /**
   * Will print a line to celebrate user win
   */
  public void celebrate() {
    
    System.out.println("YOU WON THE GAME!");
  }
  
  /**
   * Will return integer of position of player
   * 
   * @return int of player position on board
   */
  public int getPlayerPosition() {
    return player.getPosition();
  }
  
  /**
   * Gets the name of the player
   * 
   * @return String of name of player
   */
  public String getName() {
    return player.getName();
  }
  
  /**
   * Gets number of times that the spinner is spun
   * 
   * @returns integer number of times spun
   */
  public int getNumTurns() {
    
    return numTurns;
  }
  
  /**
   * Gets number of questions answered correctly
   * 
   * @returns integer number of questions answered correctly
   */
  public int getNumCorrect() {
    
    return numCorrect;
  }
  
  /**
   * Gets number of times player has landed on a chute
   * 
   * @returns integer number of times player has landed on chute
   */
  public int getNumChutes() {
    
    return numChutes;
  }
  
  /**
   * Gets number of times player has landed on a ladder
   * 
   * @returns integer number of times player has landed on ladder
   */
  public int getNumLadders() {
   
    return numLadders;
  }
  
  /**
   * Gets net movement of player throughout the game
   * 
   * @returns integer number of total tiles moved by player in the game
   */
  public int getTotalMovement() {
    
    return totalMovement;
  }
  
  /**
   * Returns image based on user-chosen token piece
   * 
   * @return String image file name
   */
  public String getToken() {
    String result = "";
    
    if (player.getToken().equals("Purple Dot")) {
      
      result = "circle.jpg";
    }
    
    if (player.getToken().equals("Sunset")) {
      result = "sunset.jpg";
    }
    
    if (player.getToken().equals("Harry Styles")) {
      result = "harry_styles.jpg";
    }
    return result;
  }
  
  /** 
   * goes through gameplay for testing in main method
   */
  public void playGame() {
    
    while (!gameOver()) {
    
      spin();
      
      String[] currentQ = askQuestion();
      String playerAnswer = answerQuestion();
      
      boolean correct = checkQuestion(currentQ, playerAnswer);
      
      int position = moveToken(correct);
      position =secondaryMoveToken(position);
    }
    celebrate();
  }
  
  /** 
   * main method for testing
   */
  public static void main(String[] args) {
    
    Player player = new Player("Teresa", "red");
    
    try{
      
      CnLGame game = new CnLGame(player);
      game.playGame();
    }
    
    catch(IOException ex) {
      
      System.out.println("file does not exist.");
    }
  }
}