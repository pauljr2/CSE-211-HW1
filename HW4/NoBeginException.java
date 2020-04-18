/**
 * Homework 4
 * CSE 211
 * 17 April 2020
 * 
 * @author Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 */

package turtle;

public class NoBeginException extends RuntimeException {

	/**
	 * Throws an exception when there's not a "begin" token
	 * with an "end" token
	 * @param message String that contains error message
	 */
	public NoBeginException(String message) {
		super(message);
	}
	// Empty constructor
	public NoBeginException() {
		
	}
	
}
