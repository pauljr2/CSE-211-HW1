/**
 * Homework 4
 * CSE 211
 * 17 April 2020
 * 
 * @author Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 */

package turtle;

public class NoEndException extends RuntimeException {

	/**
	 * Throws an exception when there's not an "end" token
	 * with a "begin" token
	 * @param message String that contains error message
	 */
	public NoEndException(String message) {
		super(message);
	}
	// Empty constructor
	public NoEndException() {
		
	}
	
}
