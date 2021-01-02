package endpointmethod.model;

import java.awt.geom.Point2D;

public class Triangle {

    private double verticeLength;
    private double height;

    private Point2D firstCorner;
    private Point2D secondCorner;
    private Point2D thirdCorner;

    public Triangle(double verticeLength) {
        this.verticeLength = verticeLength;

        // calculating height of triangle
        this.height = (this.verticeLength / 2) * Math.sqrt(3);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVerticeLength() {
        return verticeLength;
    }

    public void setVerticeLength(double verticeLength) {
        this.verticeLength = verticeLength;
    }

    public Point2D getFirstCorner() {
        return firstCorner;
    }

    public void setFirstCorner(Point2D firstCorner) {
        this.firstCorner = firstCorner;
    }

    public Point2D getSecondCorner() {
        return secondCorner;
    }

    public void setSecondCorner(Point2D secondCorner) {
        this.secondCorner = secondCorner;
    }

    public Point2D getThirdCorner() {
        return thirdCorner;
    }

    public void setThirdCorner(Point2D thirdCorner) {
        this.thirdCorner = thirdCorner;
    }
}
