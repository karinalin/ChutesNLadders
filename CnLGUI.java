/* CnLGUI.java
 * @author Karina Lin, Olivia Salomon, Teresa Li
 * 
 * Driver class for Chutes N' Ladders game
 */

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javafoundations.exceptions.*;

public class CnLGUI {
  
  /** 
   * main method for testing
   */
  public static void main (String[] args) {
    
    // creates and shows a Frame 
    JFrame frame = new JFrame("Welcome");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel welcomePanel = new WelcomePanel(frame);
    frame.getContentPane().add(welcomePanel);
    
    frame.pack();
    frame.setVisible(true);
    
  }
}