
/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Math;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

public class TurtleSoup2 {
	private static HashMap<String, Integer> variables;
	private static Stack<String> begins;
	private static StringTokenizer tokens;
	private static DrawableTurtle turtle;
    
	private static Integer nextInt() {
		String variable = tokens.nextToken();
		Integer resultingInteger = null;
		if (variables.containsKey(variable)) {
			resultingInteger = variables.get(variable);
		}
		else {
			resultingInteger = Integer.parseInt(variable);
		}
		return resultingInteger;
	}
	
	private static Double nextDouble() {
		return new Double(nextInt());
	}

    
	private static String runToken(String token) {
    	switch(token){
    	case "begin": 
    		begins.add(token);
    		break;
    	case "end":
    		begins.pop();
    	case "loop":
    		for (int i = 1; i <= nextInt(); i++){
    			String lastToken = "";
    			while (lastToken != "end") {
    				lastToken = runToken(tokens.nextToken());
    			}
    		}
    		break;
    	case "forward":
			turtle.forward(nextInt());
			break;
		
		case "turn":
			turtle.turn(nextDouble());
			break;
		case "programEnd":
			break;
		case "=":
			break;
    	default: // Gets wrong tokens (nextToken is giving problems)
    		String variableName = token;
    		String nextToken = tokens.nextToken();
    		if (nextToken == "=") {
    			variables.put(variableName, nextInt());
    		}
    		else {
    			System.out.println(token);
    		}
    		// TO DO
    		break;
    	}
    	return token;
    }
    
    public static void translateGrammar(FileReader file) {
    	turtle = new DrawableTurtle();
    	begins = new Stack<String>();
    	variables = new HashMap<String, Integer>(); // Keep track all of the variables the user declares
    	Scanner scan = new Scanner(file);
    	String fileToString = "";
    	while (scan.hasNextLine()) {
    		fileToString = fileToString + scan.nextLine() + "\n";
    	}
    	System.out.println(fileToString);
		tokens = new StringTokenizer(fileToString);
		String lastToken = "";
		while(lastToken != "pro") {
			lastToken = runToken(tokens.nextToken());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		translateGrammar(file);
    }
}
