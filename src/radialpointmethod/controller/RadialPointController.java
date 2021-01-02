package radialpointmethod.controller;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import radialpointmethod.model.CircleModel;
import radialpointmethod.model.CircumferencePointModel;
import radialpointmethod.model.RandomPointModel;
import radialpointmethod.model.TriangleModel;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author oliverthoma
 * @version 22.10.2020
 * <p>
 * The main EndPointController class for RadialPointView.fxml
 */

public class RadialPointController implements Initializable {

    // randomPoint model class for generating and managing random points on the drawn radius
    private final RandomPointModel randomPoint;
    // radialPoint model class for generating and managing the random points on circumference (=> radius)
    private final CircumferencePointModel radialPoint;
    // circle and Triangle model classes corresponding with drawn shapes on canvas
    private final CircleModel circle;
    private final TriangleModel triangle;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    @FXML
    private TextField iterations, delay, angle;
    @FXML
    private Button startbutton, resetbutton, menubutton;
    @FXML
    private Label probLabel;
    @FXML
    private RadioButton randomRad, manualRad;

    public RadialPointController() {
        this.randomPoint = new RandomPointModel();
        this.radialPoint = new CircumferencePointModel();
        this.circle = new CircleModel(150);
        this.triangle = new TriangleModel();
    }

    // start button on bottom of GUI (=> click event)
    @FXML
    public void handleButtonStart() {
        // draws shapes onto canvas and clears it
        drawAndClear();

        // generates random points and draws the lines
        drawRandomLines(circle, triangle);
    }

    // handles the reset button on bottom of GUI (=> click event)
    // clears the canvas and draws initial shapes
    @FXML
    public void handleButtonReset() {
        drawAndClear();
    }

    // menu button on bottom of GUI (=> click event)
    // sets the stage back to menu stage
    @FXML
    public void handleButtonMenu(ActionEvent event) throws IOException {
        Parent startScreen = FXMLLoader.load(getClass().getResource("../../startscreen/view/StartScreenView.fxml"));
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        oldStage.setScene(new Scene(startScreen));
        oldStage.setTitle("Bertrands Paradox - Main Menu");
    }

    // enables the angle TextField (=> click event)
    @FXML
    public void radioButtonManual() {
        angle.setDisable(false);
    }

    // disables the angle TextField (=> click event)
    @FXML
    public void radioButtonRandom() {
        angle.setDisable(true);
    }

    // draws shapes onto canvas and clears it
    public void drawAndClear() {
        // clears the canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // position of drawn Circle and Triangle shapes (=> center of canvas)
        circle.setPos(canvas.getWidth(), canvas.getHeight());
        triangle.setPosInCircle(circle);

        // draw initial shapes onto gc
        drawTriangle(triangle);
        drawCircle(circle);
    }

    // draws the circle object on graphicsContext (with color)
    public void drawCircle(CircleModel c) {
        gc.setLineWidth(1.5);
        gc.setStroke(Color.rgb(54, 57, 59));
        gc.strokeOval(c.getXPosCenter(), c.getyPosCenter(), c.getDiameter(), c.getDiameter());
    }

    // draws the triangle object onto graphicsContext (with color)
    public void drawTriangle(TriangleModel t) {
        gc.setLineWidth(1.5);
        gc.setStroke(Color.rgb(54, 57, 59));
        gc.strokePolygon(t.getXPoints(), t.getYPoints(), 3);
    }

    // generates random points on circumference and draws the radius into the given circle
    public void drawRadius(CircleModel c) {
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1.5);

        // if the Checkbox is selected draw the radius within the circle randomly, else specified (from input TextField)
        if (randomRad.isSelected()) {
            radialPoint.setRandomCircumferencePoint(c);
        } else if (manualRad.isSelected()) {
            radialPoint.setAngleCircumferencePoint(c, Double.parseDouble(angle.getText()));
            gc.strokeLine(c.getxPos(), c.getyPos(), radialPoint.getRadialPoint().getX(), radialPoint.getRadialPoint().getY());
        }
    }

    // draws the lines randomly and calculates probability
    public void drawRandomLines(CircleModel c, TriangleModel t) {
        AtomicReference<Integer> shorterIterations = new AtomicReference<>(0);
        AtomicReference<Integer> longerIterations = new AtomicReference<>(0);

        new Thread(() -> {
            // diable the GUI while drawing the lines
            boolean radio = false;
            if (randomRad.isSelected()) radio = true;
            disableGUI(true);

            for (int i = 0; i < Integer.parseInt(iterations.getText()); i++) {
                Platform.runLater(() -> {
                    // single random Point on new Radius
                    drawRadius(c);
                    Point2D.Double pointOnRadius = randomPoint.createSingleVectorPoint(c, radialPoint.getRadialPoint());

                    // checks if the calculated line is longer than one side of triangle and updates the counters correspondingly
                    // longer = RED, shorter = WHITE
                    if (randomPoint.isLineLonger(pointOnRadius, c)) {
                        gc.setStroke(Color.rgb(200, 211, 230));
                        longerIterations.updateAndGet(v -> v + 1);
                    } else {
                        gc.setStroke(Color.rgb(150, 25, 0));
                        shorterIterations.updateAndGet(v -> v + 1);
                    }

                    // draw lines from the random point on radius to circumference (left and right side)
                    Point2D.Double leftPoint = randomPoint.getEndPoint(c, pointOnRadius).get(0);
                    Point2D.Double rightPoint = randomPoint.getEndPoint(c, pointOnRadius).get(1);
                    gc.setLineWidth(1.0);
                    gc.strokeLine(pointOnRadius.getX(), pointOnRadius.getY(), leftPoint.getX(), leftPoint.getY());
                    gc.strokeLine(pointOnRadius.getX(), pointOnRadius.getY(), rightPoint.getX(), rightPoint.getY());

                    // calculate probability of the Line being longer and set to label
                    double probability = 100 * (longerIterations.get().doubleValue() / (longerIterations.get().doubleValue() + shorterIterations.get().doubleValue()));
                    probLabel.setText(probability + "%");
                });

                // delay of drawing lines (=> delay specified on "delay" input on GUI)
                try {
                    Thread.sleep(Integer.parseInt(delay.getText()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // enable the GUI
            drawTriangle(t);
            drawCircle(c);

            disableGUI(false);
            if (radio) angle.setDisable(true);
        }).start();
    }

    // disables certain GUI elements during the simulation
    public void disableGUI(boolean state) {
        iterations.setDisable(state);
        delay.setDisable(state);
        angle.setDisable(state);

        startbutton.setDisable(state);
        menubutton.setDisable(state);
        resetbutton.setDisable(state);

        randomRad.setDisable(state);
        manualRad.setDisable(state);
    }

    // initialization of graphicsContext
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
    }
}
