/* Player.java
 * @author Karina Lin, Olivia Salomon, Teresa Li
 * 
 * Class contains information for Player object
 */

public class Player {
  
  //instance variables
  
  //general info
  String name;
  String token;
  
  int position;
  
  /** Constructor: Creates Player object
    * 
    * @param name user-inputted name
    * @param token user-chosen token
    */
  public Player(String name, String token) {
    
    this.name = name;
    this.token = token;
    
    position = 0;
  }
  
   /**
   * Gets the position of the player on the board
   * 
   * @return integer corresponding to player position on board
   */
  public int getPosition() {
    
    return position;
  }
  
  /**
   * Gets the name of the player
   * 
   * @return String of name of player
   */
  public String getName() {
    return name;
  }
  
   /**
   * Sets the position of the player on the board for use in gameplay
   */
  public void setPosition(int destination) {
    
    this.position = destination;
    
  }
  
  /**
   * Returns image based on user-chosen token piece
   * 
   * @return String of name of token chosen
   */
  public String getToken() {
    return token;
  }
}