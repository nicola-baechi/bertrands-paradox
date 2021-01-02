package endpointmethod.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PointManager {

    // contains points on circle and is passed to LineManager for line construction
    private final ArrayList<Point2D> onCirclePoints = new ArrayList<Point2D>();

    // stores as many points on circle as the param lineAmount is in ArrayList onCirclePoints
    public void generatePointsOnCircle(int lineAmount, Circle circle) {
        for (int i = 0; i < lineAmount; ++i) {

            // cosinus = ankathete / hypo | sinus = gegenkathete / hypo
            final double angle = Math.toRadians(((double) i / lineAmount) * 360d);

            // generating new point and declaring calulated coordinates to it
            onCirclePoints.add(new Point2D.Double(Math.cos(angle) * circle.getRadius() + circle.getMidpoint().getX(),
                    Math.sin(angle) * circle.getRadius() + circle.getMidpoint().getY()));
        }
    }

    public ArrayList<Point2D> getOnCirclePoints() {
        return onCirclePoints;
    }

}