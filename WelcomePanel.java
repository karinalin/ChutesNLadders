/* Player.java
 * @author Olivia Salomon, Karina Lin, Teresa Li
 * 
 * Builds welcome GUI
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class WelcomePanel extends JPanel {
  
  // instance vars
  
  String playerName;
  String tokenChoice = "Harry Styles";
  String[] options;
  
  Player player1;
  
  JButton submit;
  JComboBox token;
  JTextField name = new JTextField("Steve Jobs");
  JPanel centerPanel;
  JFrame frame;
  
  public boolean infoCollected = false;
  
  /** 
   * Constructor: Creates Welcome GUI
   */
  public WelcomePanel(JFrame frame) {
    this.frame = frame;
    setLayout(new BorderLayout());
    
    // creates the header and footer
    JLabel topLine = new JLabel("Welcome to Chutes N Ladders!" + 
                                  " Please fill out your information below.");
    JLabel bottomLine = new JLabel("Made by Olivia, Karina, and Teresa!");
    
    // create the center panel
    centerPanel = new JPanel();
    
    // create elements on the center panel
    
    // name
    JLabel nameLabel = new JLabel("Player name: ");

    name.setColumns(10);
    
    //token
    JLabel tokenLabel = new JLabel("Player token: ");
    options = new String[4];
    options[0] = "Default (Harry Styles)";
    options[1] = "Sunset";
    options[2] = "Harry Styles";
    options[3] = "Purple Dot";
    
    // combo box for tokens
    token = new JComboBox(options);
    token.addActionListener (new ComboBoxListener());

    // submit button
    submit = new JButton("submit");
    submit.addActionListener(new ButtonListener());
    
    // add to panel
    centerPanel.add(nameLabel);
    centerPanel.add(name);
    centerPanel.add(tokenLabel);
    centerPanel.add(token);
    centerPanel.add(submit);
    // add everything 
    add(topLine, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
    add(bottomLine, BorderLayout.SOUTH);
  }
  
  /** 
   * Class that builts listener that listens to Combo Box
   */
  private class ComboBoxListener implements ActionListener {
    
   /** 
   * method taking in the values the user enters for each combo box
   */
    
    public void actionPerformed (ActionEvent event) {
      String temp = (String)token.getItemAt(token.getSelectedIndex());
      if (!temp.equals("Default (Harry Styles)")) {
      tokenChoice = temp;
      System.out.println(tokenChoice);
    }
    }
  }
  
  
  /** 
   * Class that builts listener that listens to "submit" button
   */
  class ButtonListener implements ActionListener {
    
    /** 
     * method that prompts action when the submit button is clicked
     */
    public void actionPerformed (ActionEvent event) {
      
      if (event.getSource() == submit) {
        System.out.println("You pressed submit");
        
        String playerName = name.getText();
        System.out.println(playerName);
        
        JLabel errorMessage = new JLabel("");
        centerPanel.add(errorMessage);

        
        // if the user hasn't selected anything:
        while (playerName.length() == 0 || tokenChoice.equals("choose token") || tokenChoice == null) {
          errorMessage.setText("You didn't submit all your information.");
      
        }
        
        // creates and shows other panels?? after the welcome panel...
        player1 = new Player(playerName, tokenChoice);
        JFrame mainFrame = new JFrame("Chutes n' Ladders");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
          
          CnLGame game = new CnLGame(player1); 
          JPanel mainPanel = new MainPanel(game);
          
          mainFrame.getContentPane().add(mainPanel);
        }
        
        catch (IOException ex) {
          
        }
        
        mainFrame.pack();
        mainFrame.setVisible(true);
        
      }
      
    }
  }
}
