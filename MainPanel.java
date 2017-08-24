/* MainPanel.java
 * @author Karina Lin, Olivia Salomon, Teresa Li
 * 
 * Class that runs Chutes N Ladders gameplay
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import boardelements.*;



public class MainPanel extends JPanel {
  
    // panels
  JPanel spinnerPanel;
  JPanel gamePanel;
  JPanel statsPanel;
  JPanel interactionsPanel;
  JPanel judgementPanel;
  
  // game play stuff
  int currentSpin;
  CnLGame game;
  String[] currentQ = new String[2];
  String userAnswer;
  int position; 
  
  // buttons
  JButton spinButton = new JButton("SPIN!!!");
  JButton okayButton = new JButton("Okay");
  JButton submitAnswer = new JButton("submit");
  JTextField playerAnswer = new JTextField();
  JButton questionSubmit = new JButton("submit answer");
  
  // labels
  JLabel feedbackSpin;
  JLabel labelQuestion;
  JLabel feedbackJudgement;
  JLabel feedbackPosition;
  JLabel feedbackType;
  JLabel feedbackSecondaryPosition;
  
  // elements for statsPanel
  JLabel numTurnsLabel;
  JLabel numCorrectLabel;
  JLabel numChutesLabel;
  JLabel numLaddersLabel;

  // other elements
  JPanel boardPanel;
  String tokenFile;
  JTabbedPane rightPanel;
  int boardPosition;
  
  /** Constructor: constructor that takes in CnL game, adds each panel using the functions below
    * 
    * @param CnLGame passes game as parameter
    */
  public MainPanel(CnLGame game) {
    this.game = game;
    this.tokenFile = game.getToken();
    setLayout(new BorderLayout(10,10));
    
    // create and add panels    
    add(makeTitlePanel(), BorderLayout.NORTH);
    add(makeBottomPanel(), BorderLayout.SOUTH);
    
    boardPanel = makeBoardPanel();
    add(boardPanel, BorderLayout.WEST);
    
    rightPanel = makeRightPanel();
    add(rightPanel, BorderLayout.EAST);
   
  }
  
  /** Creates panel for Title
    * 
    * @return JPanel that is the Title panel
    */
  private JPanel makeTitlePanel(){
    
    // make panel
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new BorderLayout());
    ImagePanel panel = new ImagePanel("imagecomponents/graphics/titleImg.png");
    
    // add elements to title
    JLabel title = new JLabel("Chutes N Ladders!"); // we will want to format this!
    
    JLabel greeting = new JLabel("Welcome, " + game.getName() + "! Cute token :)");

    titlePanel.add(panel, BorderLayout.NORTH);
    titlePanel.add(greeting,  BorderLayout.CENTER);
    
    return titlePanel;
  }
  
  /** Creates panel for bottom text
    * 
    * @return JPanel that is the bottom panel
    */
  private JPanel makeBottomPanel(){
    
    // make panel
    JPanel bottomPanel = new JPanel();
    
    // add elements to title
    JLabel bottomLabel = new JLabel("Game made by Olivia, Karina, and Teresa");
    bottomPanel.add(bottomLabel);
    
    return bottomPanel;
  }
  
  /** Creates panel for Board
    * 
    * @return JPanel that is the Board panel
    */
  private JPanel makeBoardPanel(){
    
    // make panel
    JPanel board = new JPanel();
    board.setLayout (new GridLayout(8,8));
    
    // helps translate between panels in the grid and indices
    int[] fileNameToIndexArray = new int[] {
      57,58,59,60,61,62,63,64,
        56,55,54,53,52,51,50,49,
        41,42,43,44,45,46,47,48,
        40,39,38,37,36,35,34,33,
        25,26,27,28,29,30,31,32,
        24,23,22,21,20,19,18,17,
        9,10,11,12,13,14,15,16,
        8,7,6,5,4,3,2,1};
    
    int index = fileNameToIndexArray[boardPosition];
    
    // add elements to panel: adds the player token to the spot where it is on the board
    
    for (int i=1; i<10; i++) { // creates and adds elements 001-009
      if (i != index){
        String fileName = ("imagecomponents/boardelements/image_part_00" + i + ".jpg");
        ImagePanel img = new ImagePanel(fileName);
        board.add(img);
      }
      else {
        ImagePanel img = new ImagePanel(tokenFile);
        board.add(img);
      }
    }
    
    for (int i=10; i<65; i++) { // creates and adds elements 010-063 
      if (i != index){
        String fileName = ("imagecomponents/boardelements/image_part_0" + i + ".jpg");
        ImagePanel img = new ImagePanel(fileName);
        board.add(img);
      }
      else {
        ImagePanel img = new ImagePanel(tokenFile);
        board.add(img);
      }
    }
    return board; 
  }
  
  /** Creates panel for right tabbed panel with gameplay and stats
    * 
    * @return JTabbedPanel that is the tabbed panel
    */
  private JTabbedPane makeRightPanel(){
    
    // make tabbed pane
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setPreferredSize(new Dimension(350, 190));
    
    // make gamePanel panel  
    gamePanel = new JPanel();      
    gamePanel.setLayout(new GridLayout(3,1));
    
    // add gamePanel tab  
    tabbedPane.addTab("Game Play", gamePanel);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    
    // create spinnerPanel
    JPanel spinnerPanel = new JPanel();
    
    //create spinner icon
    spinnerPanel.setLayout(new BorderLayout());
    
    ImageIcon spinnerIcon = new ImageIcon(new ImageIcon("imagecomponents/graphics/spinner1.jpg").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));

    JLabel spinnerImage = new JLabel();
    spinnerImage.setIcon(spinnerIcon);
    
    //define spin button
    spinButton.setPreferredSize(new Dimension(350, 50));
    spinButton.addActionListener(new ButtonListener());
    
    // add spinner and spinButton
    spinnerPanel.add(spinnerImage, BorderLayout.CENTER);
    spinnerPanel.add(spinButton, BorderLayout.SOUTH);
    
    gamePanel.add(spinnerPanel);
    
    // interactionsPanel
    interactionsPanel = new JPanel();
    gamePanel.add(interactionsPanel);
    
    // judgementPanel
    judgementPanel = new JPanel();
    gamePanel.add(judgementPanel);
    
    // labels for interactions and judgement
    feedbackSpin = new JLabel("");
    labelQuestion = new JLabel("");
    feedbackJudgement = new JLabel("");
    feedbackPosition = new JLabel("");
    feedbackType = new JLabel("");
    feedbackSecondaryPosition = new JLabel("");
    
    // fix buttons for interactions and judgement
    playerAnswer.setColumns(8);
    submitAnswer.addActionListener(new ButtonListener());
    okayButton.addActionListener(new ButtonListener());
    
    // make statsPanel panel
    statsPanel = new JPanel();
    statsPanel.setLayout(new GridLayout(10,1));
    
    // make labels 
    numTurnsLabel = new JLabel();
    numCorrectLabel = new JLabel();
    numChutesLabel = new JLabel();
    numLaddersLabel = new JLabel();
    
    refreshStats();
    
    statsPanel.add(numTurnsLabel);
    statsPanel.add(numCorrectLabel);
    statsPanel.add(numChutesLabel);
    statsPanel.add(numLaddersLabel);
    
    refreshStats();
    // add statsPanel tab
    tabbedPane.addTab("Game Stats", statsPanel);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    
    return tabbedPane;
    
  }
  
  /** 
   * Refreshes stats
   */
  public void refreshStats() {
    numTurnsLabel.setText("Total number of turns: " + game.getNumTurns());
    numChutesLabel.setText("Total number of chutes taken: " + game.getNumChutes());
    numLaddersLabel.setText("Total number of ladders taken: " + game.getNumLadders());

    numCorrectLabel.setText("Number of correct questions: " + (game.getNumCorrect()));
    
    statsPanel.validate();
    statsPanel.repaint();
    gamePanel.validate();
    gamePanel.repaint();
  }
  
  
  
  /** 
   * Creates and returns an ImageIcon out of an image file.
   * From lab.
   * @param path The path to the imagefile relevant to the current file.
   * @param description A short description to the image.
   * @return ImageIcon An ImageIcon, or null if the path was invalid. 
   */
  private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = MainPanel.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
  /** 
   * Creates button listener for buttons
   */
  class ButtonListener implements ActionListener {
    
   /** 
   * prompts action when event occurs
   */
    public void actionPerformed(ActionEvent event) {
      
      if (event.getSource() == spinButton) {
       
        // turn off the spin button so the user cannot respin
        spinButton.setEnabled(false);

        currentSpin = game.spin();
        currentQ = game.askQuestion();
        
        // adds the labels for interactionsPanel
        interactionsPanel.add(feedbackSpin);
        interactionsPanel.add(labelQuestion);
        interactionsPanel.add(playerAnswer);
        interactionsPanel.add(submitAnswer);
        
        // adds text to the labels for interactionsPanel
        feedbackSpin.setText("You spun a " + currentSpin + "!");
        labelQuestion.setText("Your question is: " + currentQ[0]);
        
        // refreshes
        gamePanel.validate(); 
        interactionsPanel.validate();
        interactionsPanel.repaint();
      }
      
      if (event.getSource() == submitAnswer) {
        // disables the submitAnswer button so the user can't submit a different answer
        submitAnswer.setEnabled(false);
        userAnswer = playerAnswer.getText(); // collects the user's answer
        playerAnswer.setText(""); // clears the JTextField
       
        // adds buttons and feedback labels
        judgementPanel.add(feedbackJudgement);
        judgementPanel.add(feedbackPosition);
        judgementPanel.add(feedbackType);
        judgementPanel.add(feedbackSecondaryPosition);
        judgementPanel.add(okayButton);
        
        boolean correct = game.checkQuestion(currentQ, userAnswer);
        
        // adds text to feedback labels
        if (correct) {
          feedbackJudgement.setText("Congratulations! You are correct.");
          int position = game.moveToken(correct);
          feedbackPosition.setText("You are now at Tile " + position + ".");
          String type = game.getType(position);
          int newPosition = game.secondaryMoveToken(position);
          
          // allows for the statement following to be gramatically correct 
          String s = "";
          if (!type.equals("normal")) {
            s = " so you are now on Tile " + newPosition + ".";
          }
          else {
            s = ".";
          }
          
          feedbackType.setText("Tile " + position + " is a " + type + " tile" + s);
    
          // adjusts the global variable of boardPosition
          boardPosition = newPosition;
          position = newPosition;
          
          if (game.gameOver()) {
            spinButton.setEnabled(false);
            okayButton.setEnabled(false);
            
            System.out.println("game over");
            
            JFrame congratsFrame = new JFrame("Game over!");
            congratsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            ImagePanel congratsPanel = new ImagePanel("imagecomponents/graphics/congratulations.png");
            congratsFrame.getContentPane().add(congratsPanel);
            
            congratsFrame.pack();
            congratsFrame.setVisible(true);
          }
          
          try {
          // replaces the board with a new board where the proper image is covered
         
          JPanel boardPanel2 = makeBoardPanel();
          remove(boardPanel);
          add(boardPanel2, BorderLayout.WEST);
          
          boardPanel2.validate();
          boardPanel2.repaint();
          judgementPanel.validate();
          judgementPanel.repaint();
          
          System.out.println(game.gameOver());
                      System.out.println(game.getPlayerPosition());
          }
          catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("exception thrown");
          }
          
        }
        else {
          
          feedbackJudgement.setText("You got it wrong. Spin again.");
          judgementPanel.remove(feedbackPosition);
          judgementPanel.remove(feedbackType);
        }
        
      }
      if (event.getSource() == okayButton) {
       
        interactionsPanel.removeAll();
        judgementPanel.removeAll();
        spinButton.setEnabled(true);
        submitAnswer.setEnabled(true);
        
        interactionsPanel.validate();
        judgementPanel.validate();
        interactionsPanel.repaint();
        judgementPanel.repaint();
        refreshStats();
      }
    }
  }
}