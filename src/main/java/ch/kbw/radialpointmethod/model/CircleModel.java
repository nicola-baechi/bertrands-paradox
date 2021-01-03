package ch.kbw.radialpointmethod.model;

/**
 * @author oliverthoma
 * @version 22.10.2020
 * <p>
 * Model class representing the drawn circle on the canvas
 */

public class CircleModel {

    private double radius;
    private double xPos;
    private double yPos;

    public CircleModel(double radius) {
        this.radius = radius;
    }

    // calculates new xPos to center the circle on given points
    public double getXPosCenter() {
        return xPos - radius;
    }

    // calculates new yPos to center the circle on given points
    public double getyPosCenter() {
        return yPos - radius;
    }

    // centers the Circle on given x and y points
    public void setPos(double xPos, double yPos) {
        this.xPos = xPos / 2;
        this.yPos = yPos / 2;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getxPos() {
        return xPos;
    }

    public void setPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
}
