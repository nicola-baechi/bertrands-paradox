package ch.kbw.endpointmethod.model;

import java.awt.geom.Point2D;

public class Line {

    private Point2D startPoint;
    private Point2D endPoint;


    public double getLength() {
        double result = Math.sqrt((Math.pow(startPoint.getX() - endPoint.getX(), 2) + Math.pow(startPoint.getY() - endPoint.getY(), 2)));
        return result;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point2D endPoint) {
        this.endPoint = endPoint;
    }
}