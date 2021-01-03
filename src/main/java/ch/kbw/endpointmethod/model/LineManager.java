package ch.kbw.endpointmethod.model;

import java.util.ArrayList;
import java.util.Random;

public class LineManager {

    private final ArrayList<Line> lines = new ArrayList<>();
    private boolean randomStartpoint;
    private float probability;

    public LineManager() {
        randomStartpoint = false;
    }

    public void generateLines(int lineAmount, PointManager pointManager, Triangle triangle, Circle circle) {
        // generating points on circle to use for line construction
        pointManager.generatePointsOnCircle(lineAmount, circle);
        for (int i = 0; i < lineAmount; i++) {

            Line line = new Line();
            // randomly setting both points of line
            Random random = new Random();
            if (randomStartpoint) {
                line.setStartPoint(pointManager.getOnCirclePoints().get(random.nextInt(pointManager.getOnCirclePoints().size())));
            } else {
                line.setStartPoint(triangle.getFirstCorner());
            }
            line.setEndPoint(pointManager.getOnCirclePoints().get(random.nextInt(pointManager.getOnCirclePoints().size())));
            lines.add(line);
        }
    }

    // boolean to check if line is valid or not
    public boolean IsValid(Line line, Triangle triangle) {
        // returns true if line crosses the bottom side of triangle
        return line.getLength() >= triangle.getVerticeLength();
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public float getProbability() {
        return probability;
    }

    public void setRandomStartpoint(boolean randomStartpoint) {
        this.randomStartpoint = randomStartpoint;
    }
}