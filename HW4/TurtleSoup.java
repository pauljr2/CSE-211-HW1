/**
 * Homework 4
 * CSE 211
 * 17 April 2020
 * 
 * @author Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 */

package turtle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream.GetField;
import java.lang.Math;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

public class TurtleSoup {
	private static HashMap<String, Integer> variables;
	private static Stack<String> begins;
	private static StringTokenizer tokens;
	private static DrawableTurtle turtle;
	private static String currentTokenEver = "";
	private static String lastTokenEver = "";
	private static String lastVariable = "";
    
	/**
	 * Retrieves the token that is the next integer from the blockTokens
	 * and handles errors by throwing exceptions 
	 * StringTokenizer object
	 * @param blockTokens StringTokenizer that contains all the tokens
	 * @return Integer that is the next integer from the blockTokens object
	 */
	private static Integer nextInt(StringTokenizer blockTokens) {
		String variable = nextToken(blockTokens);
		Integer resultingInteger = null;
		if (variables.containsKey(variable)) {
			resultingInteger = variables.get(variable);
		}
		else {
			try {
				resultingInteger = Integer.parseInt(variable);
			} catch (Exception e) {
				if (lastTokenEver.equals("loop"))
					throw new InvalidLoopStatementException("Last token: " + lastTokenEver + "\r\n" + currentTokenEver + " is not defined! \r\nREMEMBER: loops are created using this grammar:\r\nloop ::= “loop” count block\r\ncount ::= NUMBER");
				else if (lastTokenEver.equals("="))
					throw new InvalidVariableDeclarationException("Variables are declared using this grammar:\r\nassignment ::= variable “=” NUMBER\r\n" 
    					+ "variable ::= STRING");
				else
					throw new InvalidMethodCallException("Last token: " + lastTokenEver + "\r\n" + currentTokenEver + " is not defined! \r\nMethod " + lastTokenEver + " could not be called");
			}
			
		}
		return resultingInteger;
	}
	
	/**
	 * Parses the token that is the next integer as a double
	 * @param blockTokens 
	 * @return
	 */
	private static Double nextDouble(StringTokenizer blockTokens) {
		return new Double(nextInt(blockTokens));
	}
	
	/**
	 * Getter method that retrieves the next token 
	 * @param blockTokens StringTokenizer object
	 * @return next token in StringTokenizer object
	 */
	private static String nextToken(StringTokenizer blockTokens){
		lastTokenEver = currentTokenEver;
		currentTokenEver = blockTokens.nextToken();
		return currentTokenEver;
	}
	// Returns nextToken to overload
	private static String nextToken() {
		return nextToken(tokens);
	}
	
	/**
	 * Creates a new StringTokenizer object so it can be iterated through
	 * @param beginToken String that contains the first token in the StringTokenizer
	 * @param blockTokens StringTokenizer object that contains the original tokens
	 * @return StringTokenizer object that contains the same tokens as the original but
	 * allows for it to be iterated
	 */
	private static String getBlockTokens(String beginToken , StringTokenizer blockTokens) { // had to create this since the while loop caused problems when the while loop happened twice
		String blockTokensStr = beginToken;
		String token = beginToken;
		Stack<String> blockBegins = new Stack<String>();
		do {
			if(token.equals("programEnd") || tokens.hasMoreElements() == false)
				throw new NoEndException("Last token: " + lastTokenEver + "\r\n" + "A loop doesn't have an end");
			if (token.equals("begin"))
				blockBegins.add(beginToken);
			else if(token.contentEquals("end"))
				blockBegins.pop();
			token = nextToken(blockTokens);
			blockTokensStr = blockTokensStr + " " + token;
		} while (blockBegins.size() > 1 || !token.equals("end"));
		return blockTokensStr;
	}
	
	/**
	 * Determines what actions are read by the filereader and the turtle object
	 * performs the appropriate action
	 * @param token String that contains the current token being analyzed in the method
	 * @param blockTokens StringTokenizer object that contains all the tokens contained
	 * in the file
	 * @return String token object that is the last token in the file 
	 */
	private static String runToken(String token, StringTokenizer blockTokens) {
	    	switch(token){
		    	case "begin": 
		    		begins.add(token);
		    		break;
		    	case "end":
		    		if (begins.isEmpty())
		    			throw new NoBeginException("Last token: " + lastTokenEver + "\r\n" + "There is an end without a begin!");
		    		begins.pop();
		    		break;
		    	case "loop":
		    		int count = nextInt(blockTokens);
		    		String beginToken = nextToken(blockTokens);
		    		if (!beginToken.equals("begin"))
		    			throw new NoBeginException("Last token: " + lastTokenEver + "\r\n" + "There is an end without a begin!");
		    		String loopTokens = getBlockTokens(beginToken, blockTokens);
		    		for (int i = 1; i <= count; i++){
		    			StringTokenizer loopTokenizer = new StringTokenizer(loopTokens);
		    			String loopToken = "";
		    			while (!loopToken.equals("end")) {
		    				loopToken = runToken(nextToken(loopTokenizer), loopTokenizer);
		    			}
		    		}
		    		break;
		    	case "forward":
				turtle.forward(nextInt(blockTokens));
				break;
			case "turn":
				turtle.turn(nextDouble(blockTokens));
				break;
			case "programEnd":
				break;
			case "=":
				String variableName = lastVariable;
				try {
					Integer.parseInt(variableName);
					throw new InvalidVariableDeclarationException();
				} catch (Exception e) {
					if(e.getClass() == InvalidVariableDeclarationException.class){
						throw new InvalidVariableDeclarationException("Last token: " + lastTokenEver + "\r\n" + "Numbers cannot be variables! Variables are declared using this grammar:\r\nassignment ::= variable “=” NUMBER\r\n" 
		    					+ "variable ::= STRING");
					}
					variables.put(variableName, nextInt(blockTokens));
					break;
				}
	    	default: // Gets wrong tokens (nextToken is giving problems)
	    		lastVariable = token;
	    		String equalSign = nextToken(blockTokens);
	    		if (!equalSign.equals("="))
	    			throw new InvalidVariableDeclarationException("Last token: " + lastTokenEver + "\r\n" + "Variables are declared using this grammar:\r\nassignment ::= variable “=” NUMBER\r\n" 
	    					+ "variable ::= STRING");
	    		else
	    			runToken(equalSign,blockTokens);
	    		break;
	    	}
	    	return token;
    }
	// Returns runToken to overload
	private static String runToken(String token) {
		return runToken(token, tokens);
	}
    
	/**
	 * Reads the file line-by-line and stores the actions as tokens,
	 * handles errors with exceptions, and stores variables the user declares 
	 * @param FileReader object of the file being read
	 */
    public static void compileTurtleProgram(FileReader file) {
	    	turtle = new DrawableTurtle();
	    	begins = new Stack<String>();
	    	variables = new HashMap<String, Integer>(); // Keep track all of the variables the user declares
	    	Scanner scan = new Scanner(file);
	    	String fileToString = "";
	    	while (scan.hasNextLine()) {
	    		fileToString = fileToString + scan.nextLine() + "\n";
	    	}
	    	scan.close();
			tokens = new StringTokenizer(fileToString);
			String lastToken = runToken(nextToken());
			if (!lastToken.equals("begin")) {
				throw new NoBeginException("Last token: " + lastTokenEver + "\r\n" + "Turtle programs must start with a begin statement.");
			}
				
			while(!lastToken.equals("programEnd")) {
				lastToken = runToken(nextToken());
	    	}
    }

    /**
     * Driver that inputs a file and runs TurtleSoup
     * @param filename String that contains the name of the txt file
     * containing the actions to be performed by the turtle
     * @return actionList object of the turtle that contains the commands it performed
     */
    public static List<Action> runTurtleSoup(String filename) {
    		FileReader file = null;
		try {
			file = new FileReader(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		compileTurtleProgram(file);
		if (begins.contains("begin"))
			throw new NoEndException("Last token: " + lastTokenEver + "\r\n" + "There is a begin without an end!");
		System.out.println(turtle.actionList);
		turtle.draw();
		return turtle.actionList;
    }
}
