/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        for(int i = 0; i < 4; i++){
            turtle.forward(sideLength);
            turtle.turn(90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        double numSides = sides;
        return (numSides - 2) * 180 / numSides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX currentY current location
     * @param targetX targetY target point
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360.
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        int resultY = (targetY - currentY);
        int resultX = (targetX - currentX);
        double northAngle = Math.atan2(resultX, resultY) * 180 / Math.PI;
        double angle = northAngle - currentHeading;
        if(angle < 0){
            angle += 360;
        }
        return angle;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size (# of points) - 1.
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> coordChanges = new ArrayList<Double>();
        int numCoords = xCoords.size();
        double currentHeading = 0;
        for(int i = 1; i < numCoords; i++){
            double adjust = calculateHeadingToPoint(currentHeading, xCoords.get(i-1), yCoords.get(i-1), xCoords.get(i), xCoords.get(i));
            currentHeading += adjust;
            coordChanges.add(adjust);
        }
        return coordChanges;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * We'll be peer-voting on the different images, and the highest-rated one will win a prize. 
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        turtle.color(PenColor.BLUE);
        for(int i = 0; i < 20; i++) {
            turtle.forward(20);
            turtle.turn(30);
            turtle.forward(40);
            turtle.turn(-160);
            turtle.forward(100);
            turtle.turn(30);
        }
        turtle.color(PenColor.ORANGE);
        turtle.forward(-200);
        for(int i = 0; i < 20; i++) {
            turtle.forward(10);
            turtle.turn(30);
            turtle.forward(20);
            turtle.turn(-160);
            turtle.forward(50);
            turtle.turn(30);
        }
        turtle.color(PenColor.RED);
        turtle.turn(135);
        turtle.forward(100);
        for(int i = 0; i < 20; i++) {
            turtle.forward(5);
            turtle.turn(30);
            turtle.forward(10);
            turtle.turn(-160);
            turtle.forward(25);
            turtle.turn(30);
        }
        turtle.color(PenColor.PINK);
        turtle.turn(-150);
        turtle.forward(150);
        for(int i = 0; i < 20; i++) {
            turtle.forward(13);
            turtle.turn(30);
            turtle.forward(27);
            turtle.turn(-160);
            turtle.forward(67);
            turtle.turn(30);
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);

        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }

}
