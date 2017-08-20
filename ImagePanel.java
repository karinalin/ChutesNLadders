/* QuestionHash.java
 * @author http://www.java2s.com/Code/Java/Swing-JFC/Panelwithbackgroundimage.htm
 * 
 * Creates ImagePanel object
 */


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

  private Image img;

  /**
   * Constructor: Generates ImagePanel object that takes in fileName
   * 
   * @param img fileName from which ImagePanel will be generated
   */
  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }
  
    /**
   * Constructor: Generates ImagePanel object that takes in an Image object
   * 
   * @param img Image from which ImagePanel will be generated
   */
  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }
  
  /**
   * Returns ImagePanel
   * 
   * @return JPanel
   */
  public JPanel getImagePanel() {
    return this;
  }

  /**
   * Modifies graphics
   * 
   * @param graphics
   */
 public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }
 
 /**
   * main method for testing
   */
 public static void main(String[] args) {
   ImagePanel img = new ImagePanel("board63img.jpg");
   img.setVisible(true);
 }
}
