
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

public class TurtleSoup2 {
	private static HashMap<String, Integer> variables;
	private static Stack<String> begins;
	private static StringTokenizer tokens;
	private static DrawableTurtle turtle;
	private static int tokenCounter = 0;
	private static String lastTokenEver = "";
	private static String lastVariable = "";
    
	private static Integer nextInt(StringTokenizer blockTokens) {
		String variable = nextToken(blockTokens);
		Integer resultingInteger = null;
		if (variables.containsKey(variable)) {
			resultingInteger = variables.get(variable);
		}
		else {
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
		tokenCounter++;
		lastTokenEver = nextToken(tokens);
		return lastTokenEver;
	}
	
	private static String getBlockTokens(String beginToken) { // had to create this since the while loop caused problems when the while loop happened twice
		String blockTokensStr = beginToken;
		String token = beginToken;
		while (!token.equals("end")) {
			token = nextToken();
			blockTokensStr = blockTokensStr + " " + token;
		}
		return blockTokensStr;
	}
	
	private static String runToken(String token, StringTokenizer blockTokens) {
    	switch(token){
    	case "begin": 
    		begins.add(token);
    		break;
    	case "end":
    		begins.pop();
    		break;
    	case "loop":
    		int count = nextInt();
    		String beginToken = nextToken();
    		String loopTokens = getBlockTokens(beginToken);
    		for (int i = 1; i <= count; i++){
    			StringTokenizer loopTokenizer = new StringTokenizer(loopTokens);
    			String loopToken = "";
    			while (!loopToken.equals("end")) {
    				loopToken = runToken(loopTokenizer.nextToken(), loopTokenizer);
    				System.out.println("LoopToken: " + loopToken);
    			}
    			System.out.println("Loop" + i);
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
			variables.put(variableName, nextInt());
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
		while(lastToken.equals("programEnd")) {
			lastToken = runToken(nextToken());
    	}
		scan.close();
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
		try {
			translateGrammar(file);
		} catch (Exception e) {
			System.out.println("Token number: " + tokenCounter + " Last Token: " + lastTokenEver);
			String outStr = "Next tokens = ";
			while(tokens.hasMoreTokens()) {
				outStr = outStr + " " + tokens.nextToken();
			}
			System.out.println(outStr);
			System.out.println(variables);
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
		turtle.draw();
    }
}
