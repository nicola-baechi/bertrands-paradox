package ch.kbw.midpointmethod;

import ch.kbw.midpointmethod.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MidPointController implements Initializable {

    private final PointList pl;
    private final CircleModel cm;
    @FXML
    private Canvas canvas;
    @FXML
    private TextField delay, iterations;
    @FXML
    private Label probLabel;
    @FXML
    private Button startbutton, resetButton, menubutton;
    private GraphicsContext gc;

    public MidPointController() {
        cm = new CircleModel();
        pl = new PointList();
    }

    @FXML
    public void handleButtonMenu(ActionEvent event) throws IOException {
        Parent startScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenView.fxml")));
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        oldStage.setScene(new Scene(startScreen));
        oldStage.setTitle("Bertrands Paradox - Main Menu");
    }

    /**
     * starts the whole procedure
     */

    public void generateProject() {
        setupParams();
        resetGUI();
        generatePoints(Integer.parseInt(iterations.getText()));
        runApplication();
    }

    /**
     * clears all Point in ArrayList pl
     */
    public void setupParams() {
        pl.clearList();
    }

    /**
     * Resets the GUI Back
     */
    public void resetGUI() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        cm.setR(150);
        cm.setX(canvas.getWidth());
        cm.setY(canvas.getHeight());

        TriangleModel triangleModel = new TriangleModel(cm);

        drawAllShapes(triangleModel);
        probLabel.setText("NaN");
    }

    /**
     * draws the triangle and circleModel onto the canvas
     *
     * @param t is the Triangle
     */
    public void drawAllShapes(TriangleModel t) {
        gc.setLineWidth(1.5);
        gc.setStroke(Color.rgb(54, 57, 59));

        gc.strokeOval(cm.getX() - cm.getR(), cm.getY() - cm.getR(), cm.getR() * 2, cm.getR() * 2);
        gc.strokeOval(cm.getX() - cm.getR() / 2, cm.getY() - cm.getR() / 2, cm.getR(), cm.getR());
        gc.strokePolygon(t.getXPoints(), t.getYPoints(), 3);
    }


    /**
     * generate Random Points
     *
     * @param iterations The Amount of generated Points
     */
    public void generatePoints(int iterations) {
        pl.setPoints(cm.generatePointsInCircleModel(iterations));
    }

    /**
     * Runs The Application
     */
    public void runApplication() {
        resetGUI();

        new Thread(() -> {
            disableGUI(true);

            double totalCounter = 0;
            double longerCounter = 0;

            for (Point p : pl.getPoints()) {
                if (p.isInInnerCircle()) {
                    longerCounter++;
                }
                totalCounter++;
                try {
                    Thread.sleep((long) Double.parseDouble(delay.getText()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                double result = longerCounter / totalCounter;
                Platform.runLater(() -> {
                    drawPoint(p);
                    probLabel.setText((double) Math.round(100000 * (result)) / 1000 + "%");
                });
            }

            TriangleModel triangleModel = new TriangleModel(cm);

            drawAllShapes(triangleModel);

            disableGUI(false);

        }).start();

    }

    /**
     * Disables or Enables the Gui during the Animation
     *
     * @param state true for enable GUI, false for disable GUI
     */
    public void disableGUI(boolean state) {
        startbutton.setDisable(state);
        menubutton.setDisable(state);
        iterations.setDisable(state);
        delay.setDisable(state);
        resetButton.setDisable(state);
    }

    /**
     * Draws a Point
     *
     * @param p Point which should get drawn on the canvas
     */
    public void drawPoint(Point p) {
        gc.setLineWidth(1.0);

        if (p.isInInnerCircle()) {
            gc.setStroke(Color.rgb(150, 25, 0));
        } else {
            gc.setStroke(Color.rgb(200, 211, 230));
        }
        Line line = p.getLine(cm.getX(), cm.getY(), cm.getR());
        gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }


    @FXML
    public void resetCanvas() {
        resetGUI();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
    }
}
