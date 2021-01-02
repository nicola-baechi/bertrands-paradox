package radialpointmethod.model;

import java.awt.geom.Point2D;

/**
 * @author oliverthoma
 * @version 26.10.2020
 * <p>
 * Model class representing the drawn triangle on the canvas
 */

public class TriangleModel {
    // all points (X/Y coordinates) of the triangle
    private Point2D.Double C;
    private Point2D.Double B;
    private Point2D.Double A;

    // height and length of triangle
    private double height;
    private double length;

    public TriangleModel() {

    }

    //Centers the Triangle into the given CircleModel
    public void setPosInCircle(CircleModel c) {
        this.height = 1.5 * c.getRadius();
        this.length = 2 * height / Math.sqrt(3);

        this.C = new Point2D.Double(c.getxPos(), c.getyPos() - c.getRadius());
        this.B = new Point2D.Double(c.getxPos() + (length / 2), height + c.getyPos() - c.getRadius());
        this.A = new Point2D.Double(c.getxPos() - (length / 2), height + c.getyPos() - c.getRadius());
    }

    //Returns double array with all X coordinates (for StrokePolygon function)
    public double[] getXPoints() {
        return new double[]{C.getX(), B.getX(), A.getX()};
    }

    //Returns double array with all Y coordinates (for StrokePolygon function)
    public double[] getYPoints() {
        return new double[]{C.getY(), B.getY(), A.getY()};
    }

    public Point2D.Double getC() {
        return C;
    }

    public void setC(Point2D.Double c) {
        C = c;
    }

    public Point2D.Double getB() {
        return B;
    }

    public void setB(Point2D.Double b) {
        B = b;
    }

    public Point2D.Double getA() {
        return A;
    }

    public void setA(Point2D.Double a) {
        A = a;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
