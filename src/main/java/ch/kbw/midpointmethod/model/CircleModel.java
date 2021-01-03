package ch.kbw.midpointmethod.model;

import java.util.ArrayList;
import java.util.Random;

public class CircleModel {

    private double r;
    private double x;
    private double y;

    public CircleModel() {

    }

    public CircleModel(double r, double x, double y) {
        this.r = r;
        this.x = x / 2;
        this.y = y / 2;
    }

    /**
     * generate random Points
     * @param iterations amount of Points who should be generated
     * @return Returns ArrayList of Points
     */
    public ArrayList<Point> generatePointsInCircleModel(int iterations) {
        ArrayList<Point> pl = new ArrayList<>();
        int sum = 0;
        while (sum < iterations) {
            Random random = new Random();
            double randomValueX = x - r + (x + r * 2 - x) * random.nextDouble();
            double randomValueY = y - r + (y + r * 2 - y) * random.nextDouble();

            Point p = new Point();
            p.setX(randomValueX);
            p.setY(randomValueY);

            double c = Math.hypot(p.getX() - x, p.getY() - y);

            if (c < r) {

                double check = r / 2;

                p.setCheck(true);

                if (c < check) {
                    p.setInInnerCircle(true);
                    sum++;

                } else {
                    p.setInInnerCircle(false);
                    sum++;

                }
                pl.add(p);
            }
        }

        return pl;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x / 2;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y / 2;
    }
}
