package endpointmethod.model;

import java.awt.geom.Point2D;

public class Circle {

    private Point2D midpoint;
    private double radius;

    public void construct(Triangle triangle) {
        // constructing midpoint
        this.midpoint = new Point2D.Double((triangle.getFirstCorner().getX() + triangle.getSecondCorner().getX() + triangle.getThirdCorner().getX()) / 3,
                (triangle.getFirstCorner().getY() + triangle.getSecondCorner().getY() + triangle.getThirdCorner().getY()) / 3);

        // radius calculation
        double circumference = triangle.getVerticeLength() * 3;
        double s = circumference / 2;
        double area = 0.5 * triangle.getVerticeLength() * triangle.getHeight();
        this.radius = triangle.getVerticeLength() / (2 * Math.sin(Math.toRadians(60)));
    }

    public Point2D getMidpoint() {
        return midpoint;
    }

    public double getRadius() {
        return radius;
    }
}