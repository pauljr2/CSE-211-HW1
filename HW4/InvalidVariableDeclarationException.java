/**
 * Homework 4
 * CSE 211
 * 17 April 2020
 * 
 * @author Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 */

package turtle;

public class InvalidVariableDeclarationException extends RuntimeException {

	/**
	 * Throws an exception when the user tries to declare an invalid
	 * variable
	 * @param message String that contains error message
	 */
	public InvalidVariableDeclarationException(String message) {
		super(message);
	}
	// Empty constructor
	public InvalidVariableDeclarationException() {
		
	}
	
}
