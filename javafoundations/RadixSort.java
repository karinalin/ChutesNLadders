/* Olivia Salomon and Teresa Li
 * CS 230 Lab 7
 * 10/19/16
 * Task 2
 * RadixSort.java
 */
import java.util.Random;
import java.util.Vector;
import javafoundations.*;

public class RadixSort {
  private final int NUMBERLENGTH = 3;
  private final int BASE = 10;
  private Vector<Integer> input, output;
  
  public RadixSort(){
    Random rand = new Random();
    
    input = new Vector<Integer>();
    output = new Vector<Integer>();
    input.addElement(345);
    input.addElement(234);
    input.addElement(410);
    input.addElement(230);
    
    for (int i = 0; i < input.size(); i++ ) {
      output.addElement(input.elementAt(i));
    }
    
  }
  
  public String toString() {
    String result = "Input: \n";
    for (int i = 0; i < input.size(); i++) {
      result += input.elementAt(i) + "\n";
      
    }
    
    result += "Output: \n";
    for (int i = 0; i < output.size(); i++) {
      result += output.elementAt(i) + "\n";
      
    }
    return result;
    
  }
  
  private void processOnePosition(int pos) {
    Vector<ArrayQueue> digits = new Vector<ArrayQueue>(9);
   
    for (int i = 0; i < output.size(); i++) {
      int tens = (int)(output.elementAt(i)/(Math.pow(10 , pos)) % 10);
      
      
      
    }
  }
  
  
  public static void main(String [] args) {
    RadixSort test = new RadixSort();
    System.out.println(test);
    
    
  }
    
    
  }



