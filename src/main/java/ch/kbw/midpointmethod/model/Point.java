package ch.kbw.midpointmethod.model;

public class Point {
    private double x;
    private double y;
    private boolean check;
    private boolean inInnerCircle;

    public Point() {

    }

    public boolean isInInnerCircle() {
        return inInnerCircle;
    }

    public void setInInnerCircle(boolean inInnerCircle) {
        this.inInnerCircle = inInnerCircle;
    }


    public double calculateIntersection(double xCircle, double yCircle, double rCircle) {
        double ankath = Math.sqrt(Math.pow((xCircle - x), 2) + Math.pow((yCircle - y), 2));

        double hypo = rCircle;

        double powOfAnkath = Math.pow(ankath, 2);
        double powOfHypo = Math.pow(hypo, 2);

        return Math.sqrt(powOfHypo - powOfAnkath);
    }

    public Line getLine(double xCircle, double yCircle, double rCircle) {


        double angle = Math.atan2(x - xCircle, y - yCircle) * 180 / Math.PI;
        angle = angle + 90;
        double angle2 = angle + 180;

        angle = angle * Math.PI / 180;
        angle2 = angle2 * Math.PI / 180;


        double startX = x + calculateIntersection(xCircle, yCircle, rCircle) * Math.sin(angle2);
        double startY = y + calculateIntersection(xCircle, yCircle, rCircle) * Math.cos(angle2);

        double endX = x + calculateIntersection(xCircle, yCircle, rCircle) * Math.sin(angle);
        double endY = y + calculateIntersection(xCircle, yCircle, rCircle) * Math.cos(angle);

        return new Line(startX, startY, endX, endY);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
