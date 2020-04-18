/**
 * Homework 4
 * CSE 211
 * 17 April 2020
 * 
 * @author Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 */

package turtle;

public class InvalidMethodCallException extends RuntimeException {

	/**
	 * Throws an exception when the user tries to call an invalid
	 * method or action
	 * @param message String that contains error message
	 */
	public InvalidMethodCallException(String message) {
		super(message);
	}
	// Empty constructor
	public InvalidMethodCallException() {
		
	}
	
}
