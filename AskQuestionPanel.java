import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AskQuestionPanel extends JPanel {
  // instance vars
  CnLGame game;
  String [] pair;
  String question;
  String answer;
  JTextField response = new JTextField("");
  JPanel centerPanel;
  JButton submit;
  boolean correct = false;
  
  public AskQuestionPanel(CnLGame game, String[] pair, int spin) {
    this.game = game;
    this.pair = pair;
    question = pair[0];
    answer = pair[1];
   
    setLayout(new BorderLayout());
    
    // creates the header and footer
    JLabel topLine = new JLabel("You spun a " + spin + "!\n" + "Here is a question for you! Please submit integers only.");
    
    // create the center panel
    centerPanel = new JPanel();
    
    // create label with the question
    JLabel questionLabel = new JLabel(question);
    // create label with your response
    JLabel respond = new JLabel(" = ");
    // create text field done in instance vars!
    response.setColumns(8);
    // create submit button
    JButton submit = new JButton("submit");
    submit.addActionListener(new ButtonListener());
    
    // add to panel
    centerPanel.add(questionLabel);
    centerPanel.add(respond);
    centerPanel.add(response);
    centerPanel.add(submit);
    
    // add everything 
    add(topLine, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
  }
  
  public boolean isCorrect() {
    return correct;
  }
  
  
  // listeners, etc
 class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      if (event.getSource() == submit) {
        String playerAnswer = response.getText();
        if (playerAnswer == answer) {
          correct = true;
        }

        }
      }
    }
  
}
