package ch.kbw.radialpointmethod.model;

import java.awt.geom.Point2D;

/**
 * @author oliverthoma
 * @version 12.10.2020
 * <p>
 * Model Class responsible for generating (and calculation) Points on the circumference of the Circle
 */

public class CircumferencePointModel {

    private Point2D.Double radialPoint;

    public CircumferencePointModel() {

    }

    // sets radialPoint to a random point on the circumference of the given circle
    public void setRandomCircumferencePoint(CircleModel c) {
        double currentAngle = Math.random() * Math.PI * 2.0;
        this.radialPoint = new Point2D.Double(
                Math.cos(currentAngle) * c.getRadius() + c.getxPos(),
                Math.sin(currentAngle) * c.getRadius() + c.getyPos()
        );
    }

    // sets radialPoint to a point on the circumference of the given circle => Vertical on view
    public void setAngleCircumferencePoint(CircleModel c, double angle) {
        this.radialPoint = new Point2D.Double(
                Math.cos(angle * Math.PI / 180) * c.getRadius() + c.getxPos(),
                Math.sin(angle * Math.PI / 180) * c.getRadius() + c.getyPos()
        );
    }

    // returns the current random point on circumference of the circle
    public Point2D.Double getRadialPoint() {
        return radialPoint;
    }
}