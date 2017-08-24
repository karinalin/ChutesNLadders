/* QuestionHash.java
 * @author Karina Lin
 * 
 * Generates hashtable of questions for gameplay.
 */

import java.util.*;
import java.io.*;

public class QuestionHash{
  
  Hashtable<String, String> qHash;
  String[] keys;
//  boolean[] visited;
  int numElements;
  int numElementsLeft;
  
  /** Constructor: Populates the hashtable from a .csv file
    * and fills a boolean array "visited" with false
    * 
    * @param fileName file from which hashtable is populated
    * @throws IOException
    */
  public QuestionHash(String fileName) throws IOException{
    
    numElements = 0;
    
    qHash = new Hashtable<String, String>();
    keys = new String[1];
//    visited = new boolean[1];
    
    Scanner scan = new Scanner(new File(fileName));
    while (scan.hasNext()) {
      
      String nextLine = scan.nextLine();

      String[] temp = nextLine.split(",");  
      
      qHash.put(temp[0], temp[1]);
      
      if (keys.length == numElements) {
        doubleArray();
      }
      
      keys[numElements] = temp[0];
//      visited[numElements] = false;
      numElements ++;
    }
    numElementsLeft=numElements;
  }
  
  /**
   * Checks if all questions have been used.  If so, resets visited boolean array.
   */
  
  public void checkAndResetVisited() {
    
    if (numElementsLeft==0) {
      
//      for (int i=0; i<visited.length; i++) {
//        visited[i] = false;
//      }
      numElementsLeft=numElements;
    }
  }
  
  /**
   * Generates random integer that corrlates to an unused question.
   * To be used in getRandomQuestion() function.
   * 
   * @return integer for use in getRandomQuestion()
   */
  
  public int getRandomQuestionInt() {
    
    this.checkAndResetVisited();
    
    Random rand = new Random();
    
    int result = rand.nextInt(numElementsLeft);
    System.out.println(result);
    
//    while (visited[result] == true) {
      result = rand.nextInt(numElementsLeft);
//    }
    
    return result;
  }
  
  /**
   * Generates random question and answer to be used in gameplay.
   * 
   * @return String[] containing question and answer
   */
  
  public String[] getRandomQuestion() {
    int n = getRandomQuestionInt();
//    System.out.println(n);
    
    String qKey = keys[n];
    System.out.println(qKey);
    
    String qValue = qHash.get(qKey);
    System.out.println(qValue);
    
    String[] result = new String[2];
    
    result[0] = qKey;
    result[1] = qValue;
    
//    visited[n] = true;
    numElementsLeft --;
    
    //swap null to end
    keys[n] = keys[numElementsLeft];
    
    return result;
  }
  
  /**
   * Doubles capacity of the array.
   */
  
  public void doubleArray() {
    
    String[] keys2 = (String[]) new String[keys.length * 2];
//    boolean[] visited2 = (boolean[]) new boolean[keys.length*2];
    
    for (int i=0; i<keys.length; i++) {
      
      if (keys[i] != null) {
        
//        visited2[i] = visited[i];
        keys2[i] = keys[i];
      }
    }
    keys = keys2;
//    visited = visited2;
  }
  
  /**
   * Used to print the contents of visited for testing purposes.
   * 
   * @return String containing contents of visited boolean[] array.
   */
  
//  public String printVisited() {
//    String result = "";
//    for (int i=0; i<visited.length; i++) {
//      result += i+ " " + visited[i] + ", \n";
//    }
//    return result;
//  }
  
  /**
   * Provides a string representation of contents of Hashtable
   * 
   * @return A string representing this hashtable
   */
  
  public String toString() {
    return qHash.toString();
  }
  
  /**
   * main method for testing
   */
  
  public static void main(String[] args) {
    try {
      QuestionHash qHash = new QuestionHash("data/additionTables.csv");
      
      for (int i=0; i<200; i++) {
        System.out.println(i + ": ");
        qHash.getRandomQuestion();
      }
    }
    catch (IOException ex) {
      System.out.println("File not found.");
    }
  }
}