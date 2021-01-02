package midpointmethod.model;

import java.util.ArrayList;

public class PointList {
    private ArrayList<Point> points;

    public PointList() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {

        points.add(point);
    }

    public void clearList() {
        points.clear();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public double calculateHitRate() {

        int miss = 0;
        int hit = 0;

        for (Point p : points) {
            if (p.isInInnerCircle()) {
                hit++;
            } else if (!p.isInInnerCircle()) {
                miss++;
            }
        }


        return (double) Math.round(((double) ((float) hit / (miss + hit)) * 100) * 1000) / 1000;
    }


    public int getSizeofPoints() {
        return points.size();
    }

    public Point getPoint(int x) {

        return points.get(x);
    }
}
