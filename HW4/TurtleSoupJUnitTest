/**
 * Homework 4
 * CSE 211
 * 17 April 2020
 * 
 * @author Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 */

package turtle;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TurtleSoupJUnitTest {

	@Test
	void Step1Test() {
		
		// Create expected result of turtle actions
		DrawableTurtle t = new DrawableTurtle();
		t.forward(10);
		t.turn(90);
		t.forward(10);
		t.turn(90);
		t.forward(10);
		t.turn(90);
		t.forward(10);
		t.turn(90);
		
		// Compare the action lists of both turtles
		String target = t.actionList.toString();
		String result = TurtleSoup.runTurtleSoup("testProgramStep1.txt").toString();
		assertEquals(target, result);
	}
	
	@Test
	void Step2Test() {
		
		// Create expected result of turtle actions
		DrawableTurtle t = new DrawableTurtle();
		t.forward(10);
		t.turn(90);
		for (int i = 0; i < 4; i++) {
			t.forward(40);
			t.turn(90);
		}
		t.turn(60);
		t.turn(60);
		t.forward(100);
		
		// Compare the action lists of both turtles
		String target = t.actionList.toString();
		String result = TurtleSoup.runTurtleSoup("testProgramStep2.txt").toString();
		assertEquals(target, result);
	}
	
	@Test
	public void Step3Test() {
		
		// Create expected result of turtle actions
		DrawableTurtle t = new DrawableTurtle();
		int times = 4;
		t.forward(10);
		t.turn(90);
		int dist = 40;
		int angle = 60;
		for (int i = 0; i < times; i++) {
			t.forward(dist);
			t.turn(angle);
		}
		angle = 90;
		t.turn(angle);
		t.turn(angle);
		t.forward(dist);
		
		// Compare the action lists of both turtles
		String target = t.actionList.toString();
		String result = TurtleSoup.runTurtleSoup("testProgramStep3.txt").toString();
		assertEquals(target, result);
	}
	
	

}
