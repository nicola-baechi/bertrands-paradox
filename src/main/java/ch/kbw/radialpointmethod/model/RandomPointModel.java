package ch.kbw.radialpointmethod.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author oliverthoma
 * @version 22.10.2020
 * <p>
 * Model class responsible for creating random points with their corresponding x and y (double) coordinates
 * and other functions regarding the generated points
 */

public class RandomPointModel {

    public RandomPointModel() {

    }

    // creates a single random point along the given radius
    public Point2D.Double createSingleVectorPoint(CircleModel c, Point2D.Double radialPoint) {
        double random = Math.random();

        //randomly generated point on circumference of circle
        return new Point2D.Double(c.getxPos() + (random * (radialPoint.getX() - c.getxPos())), c.getyPos() + (random * (radialPoint.getY() - c.getyPos())));
    }

    // returns true or false if the drawn line is longer than one side of the triangle (1/2 of Radius)
    public boolean isLineLonger(Point2D.Double p2d, CircleModel c) {
        double a = Math.pow(p2d.getX() - c.getxPos(), 2);
        double b = Math.pow(p2d.getY() - c.getyPos(), 2);

        return Math.sqrt(a + b) > c.getRadius() / 2;
    }

    // returns a List with the two endpoints of perpendicular line (to radius)
    public List<Point2D.Double> getEndPoint(CircleModel c, Point2D.Double p2d) {
        double firstAngle = 90 + Math.atan2(p2d.getX() - c.getxPos(), p2d.getY() - c.getyPos()) * 180 / Math.PI;
        double secondAngle = firstAngle + 180;

        firstAngle *= Math.PI / 180;
        secondAngle *= Math.PI / 180;

        double firstEndX = p2d.getX() + calculateIntersection(c, p2d) * Math.sin(firstAngle);
        double firstEndY = p2d.getY() + calculateIntersection(c, p2d) * Math.cos(firstAngle);

        double secondEndX = p2d.getX() + calculateIntersection(c, p2d) * Math.sin(secondAngle);
        double secondEndY = p2d.getY() + calculateIntersection(c, p2d) * Math.cos(secondAngle);

        return new ArrayList<>(Arrays.asList(
                new Point2D.Double(firstEndX, firstEndY),
                new Point2D.Double(secondEndX, secondEndY)
        ));
    }

    // calculates the intersection of line and circle
    public double calculateIntersection(CircleModel c, Point2D.Double p2d) {
        double ankath = Math.sqrt((c.getxPos() - p2d.getX()) * (c.getxPos() - p2d.getX()) + (c.getyPos() - p2d.getY()) * (c.getyPos() - p2d.getY()));

        double hypo = c.getRadius();

        double powOfAnkath = Math.pow(ankath, 2);
        double powOfHypo = Math.pow(hypo, 2);

        return Math.sqrt(powOfHypo - powOfAnkath);
    }
}