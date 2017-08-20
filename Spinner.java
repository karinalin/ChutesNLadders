/* Spinner.java
 * @author Teresa Li
 * Based off of Die.java from CS230 Instructors
 * 
 * Spinner class to simulate spinning of spinner
 */

public class Spinner
{
   private final int MAX = 6;  // maximum spinner value

   private int spinValue;  // current value showing on the spinner

   /** 
   * Constructor: sets the initial spinner value.
   */
   public Spinner()
   {
      spinValue = 1;
   }

   
   /**
    * Spins the spinner and returns the result.
    * 
    * @return integer of value spun
    */
   
   public int spin()
   {
      spinValue = (int) (Math.random() * MAX) + 1;

      return spinValue;
   }

   /**
    * Spin value mutator
    * 
    * @param value
    */
   public void setSpinValue (int value)
   {
      spinValue = value;
   }

   /**
    * Spin value accessor
    * 
    * @return integer of value
    */
   public int getSpinValue()
   {
      return spinValue;
   }

   /**
    * Returns a string representation of this spinner
    * 
    * @return String representation of the spinner
    */
   public String toString()
   {
      String result = Integer.toString(spinValue);

      return result;
   }
}
