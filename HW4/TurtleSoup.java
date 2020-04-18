
/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */



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
	private static String lastTokenEver = "";
	private static String lastVariable = "";
    
	private static Integer nextInt(StringTokenizer blockTokens) {
		String variable = nextToken(blockTokens);
		Integer resultingInteger = null;
		if (variables.containsKey(variable)) {
			resultingInteger = variables.get(variable);
		}
		else {
			System.out.println(lastVariable + ": " + variable);
			resultingInteger = Integer.parseInt(variable);
		}
		return resultingInteger;
	}
	
	private static Integer nextInt() {
		return nextInt(tokens);
	}
	
	private static Double nextDouble(StringTokenizer blockTokens) {
		return new Double(nextInt(blockTokens));
	}
	
	private static String nextToken(StringTokenizer blockTokens){
		return blockTokens.nextToken();
	}
	
	private static String nextToken() {
		lastTokenEver = nextToken(tokens);
		return lastTokenEver;
	}
	
	private static String getBlockTokens(String beginToken , StringTokenizer blockTokens) { // had to create this since the while loop caused problems when the while loop happened twice
		String blockTokensStr = beginToken;
		String token = beginToken;
		Stack<String> blockBegins = new Stack<String>();
		do {
			if(token.equals("programEnd") || tokens.hasMoreElements() == false)
				throw new NoEndException("A loop doesn't have an end");
			if (token.equals("begin"))
				blockBegins.add(beginToken);
			else if(token.contentEquals("end"))
				blockBegins.pop();
			token = nextToken(blockTokens);
			blockTokensStr = blockTokensStr + " " + token;
		} while (blockBegins.size() > 1 || !token.equals("end"));
		System.out.println(blockTokensStr);
		return blockTokensStr;
	}
	
	private static String runToken(String token, StringTokenizer blockTokens) {
    	switch(token){
    	case "begin": 
    		begins.add(token);
    		break;
    	case "end":
    		String begin = begins.pop();
    		if (begin == null) {
    			throw new NoBeginException("There is an end without a begin.");
    		}
    		break;
    	case "loop":
    		int count = nextInt(blockTokens);
    		String beginToken = nextToken(blockTokens);
    		if (!beginToken.equals("begin")) {
    			//throw exception
    		}
    		String loopTokens = getBlockTokens(beginToken, blockTokens);
    		for (int i = 1; i <= count; i++){
    			StringTokenizer loopTokenizer = new StringTokenizer(loopTokens);
    			String loopToken = "";
    			while (!loopToken.equals("end")) {
    				loopToken = runToken(loopTokenizer.nextToken(), loopTokenizer);
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
			variables.put(variableName, nextInt(blockTokens));
			break;
    	default: // Gets wrong tokens (nextToken is giving problems)
    		lastVariable = token;
    		break;
    	}
    	return token;
    }
	
	private static String runToken(String token) {
		return runToken(token, tokens);
	}
    
    public static void compileTurtleProgram(FileReader file) {
    	turtle = new DrawableTurtle();
    	begins = new Stack<String>();
    	variables = new HashMap<String, Integer>(); // Keep track all of the variables the user declares
    	Scanner scan = new Scanner(file);
    	String fileToString = "";
    	while (scan.hasNextLine()) {
    		fileToString = fileToString + scan.nextLine() + "\n";
    	}
    	System.out.println(fileToString);
    	scan.close();
		tokens = new StringTokenizer(fileToString);
		String lastToken = runToken(nextToken());
		if (!lastToken.equals("begin")) {
			throw new NoBeginException("Turtle programs must start with a begin statement.");
		}
			
		while(!lastToken.equals("programEnd")) {
			lastToken = runToken(nextToken());
    	}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     */
    public static void main(String args[]) {
    	FileReader file = null;
		try {
			file = new FileReader("src/testProgramStep3.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		compileTurtleProgram(file);
		if (begins.contains("begin"))
			throw new NoEndException("There is a begin without an end!");
		System.out.println("Last Token: " + lastTokenEver);
		System.out.println("Variables used: " + variables);
		System.out.println("Turtle actions: " + turtle.actionList);
		turtle.draw();
    }
}
