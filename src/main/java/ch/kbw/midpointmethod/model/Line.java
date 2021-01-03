package ch.kbw.midpointmethod.model;

public class Line {
    private double startX;
    private double startY;
    private double endX;
    private double endY;


    public Line(double startX, double startY, double endX, double endY) {
        this.endX = endX;
        this.endY = endY;
        this.startY = startY;
        this.startX = startX;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    @Override
    public String toString() {
        return "Line{" +
                "endX=" + endX +
                ", endY=" + endY +
                ", startY=" + startY +
                ", startX=" + startX +
                '}';
    }
}
