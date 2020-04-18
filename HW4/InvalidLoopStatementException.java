/**
 * Homework 4
 * CSE 211
 * 17 April 2020
 * 
 * @author Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 */

package turtle;

public class InvalidLoopStatementException extends RuntimeException {

	/**
	 * Throws an exception when the user tries to use an invalid loop
	 * @param message String that contains error message
	 */
	public InvalidLoopStatementException(String message) {
		super(message);
	}
	// Empty constructor
	public InvalidLoopStatementException() {
		
	}
	
}
