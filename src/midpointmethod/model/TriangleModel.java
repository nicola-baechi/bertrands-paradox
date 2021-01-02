package midpointmethod.model;

import java.awt.geom.Point2D;

/**
 * @author stefan.hohl
 * @version 05.11.2020
 */
public class TriangleModel {

    private Point2D.Double C;
    private Point2D.Double B;
    private Point2D.Double A;
    //height of triangle
    private double h;
    //length of one side
    private double length;

    public TriangleModel(CircleModel c) {
        this.h = 1.5 * c.getR();
        this.length = 2 * h / Math.sqrt(3);

        this.C = new Point2D.Double(c.getX(), c.getY() - c.getR());
        this.B = new Point2D.Double(c.getX() + (length / 2), h + c.getY() - c.getR());
        this.A = new Point2D.Double(c.getX() - (length / 2), h + c.getY() - c.getR());
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

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
