package ch.kbw.endpointmethod;


import ch.kbw.endpointmethod.model.*;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author nicola.bächi
 * @version 26.11.2020
 */

public class EndPointController implements Initializable {

    private final Circle circle;
    private final Triangle triangle;
    private final LineManager lineManager;
    private final PointManager pointManager;
    @FXML
    private Canvas canvas;
    @FXML
    private TextField lineAmountTextfield;
    private int lineAmount;
    @FXML
    private TextField delayTextfield;
    private long delay;
    @FXML
    private Label probability;
    @FXML
    private Button startButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox randomStartpoint;
    private GraphicsContext gc;

    public EndPointController() {
        this.triangle = new Triangle(250.0);
        triangle.setFirstCorner(new Point2D.Double(250, 100));
        triangle.setSecondCorner(new Point2D.Double(triangle.getFirstCorner().getX() - (triangle.getFirstCorner().getX() / 2), triangle.getFirstCorner().getY() + triangle.getHeight()));
        triangle.setThirdCorner(new Point2D.Double(triangle.getSecondCorner().getX() + triangle.getVerticeLength(), triangle.getFirstCorner().getY() + triangle.getHeight()));
        circle = new Circle();

        // initializing default values so it can run without user input
        delay = 0;
        lineAmount = 1000;

        lineManager = new LineManager();
        pointManager = new PointManager();
    }

    @FXML
    public void handleButtonMenu(ActionEvent event) throws IOException {
        Parent startScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenView.fxml")));
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        oldStage.setScene(new Scene(startScreen));
        oldStage.setTitle("Bertrands Paradox - Main Menu");
    }

    public void start() throws InterruptedException {
        clearComponents();

        // check if user input present to prevent null pointer
        if (!delayTextfield.getText().equals("")) {
            delay = Long.parseLong(delayTextfield.getText());
        }

        // check if user input present to prevent null pointer
        if (!lineAmountTextfield.getText().equals("")) {
            lineAmount = Integer.parseInt(lineAmountTextfield.getText());
        }

        lineManager.setRandomStartpoint(randomStartpoint.isSelected());

        lineManager.generateLines(lineAmount, this.pointManager, this.triangle, this.circle);

        drawBaseComponents();
        drawLines();
    }

    public void drawBaseComponents() {
        drawTriangle();
        drawCircle();
    }

    public void drawTriangle() {
        double[] xs = {triangle.getFirstCorner().getX(), triangle.getSecondCorner().getX(), triangle.getThirdCorner().getX()};
        double[] ys = {triangle.getFirstCorner().getY(), triangle.getSecondCorner().getY(), triangle.getThirdCorner().getY()};
        gc.setStroke(Color.rgb(54, 57, 59));
        gc.setLineWidth(1.5);
        gc.strokePolygon(xs, ys, 3);
    }

    public void drawCircle() {
        gc.setStroke(Color.rgb(54, 57, 59));
        gc.setLineWidth(1.5);
        gc.strokeOval(circle.getMidpoint().getX() - circle.getRadius(), circle.getMidpoint().getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
    }

    // function that gets invalid lines from LineManager and displays them with threading
    public void drawLines() throws InterruptedException {
        AtomicReference<Integer> shorterIterations = new AtomicReference<>(0);
        AtomicReference<Integer> longerIterations = new AtomicReference<>(0);

        // disabling GUI while thread is running
        disableGUI(true);

        new Thread(() -> {
            for (Line line : lineManager.getLines()) {
                try {
                    // delaying thread with value from user input
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    if (lineManager.IsValid(line, this.triangle)) {
                        gc.setStroke(Color.rgb(150, 25, 0));
                        longerIterations.updateAndGet(v -> v + 1);
                    } else {
                        gc.setStroke(Color.rgb(200, 211, 230));
                        shorterIterations.updateAndGet(v -> v + 1);
                    }
                    // constructing and drawing line
                    gc.setLineWidth(1.0);
                    gc.strokeLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(), line.getEndPoint().getY());

                    // calculate probability of the Line being longer and set to label
                    double calculatedProbability = 100 * (longerIterations.get().doubleValue() / (longerIterations.get().doubleValue() + shorterIterations.get().doubleValue()));
                    probability.setText(calculatedProbability + "%");
                });
            }

            drawBaseComponents();

            // reenable GUI after thread finished
            disableGUI(false);

        }).start();
    }

    // function that gets valid lines from LineManager and displays them with threading
    public void disableGUI(boolean state) {
        startButton.setDisable(state);
        resetButton.setDisable(state);
        backButton.setDisable(state);
        randomStartpoint.setDisable(state);
        lineAmountTextfield.setDisable(state);
        delayTextfield.setDisable(state);
    }

    // resets the canvas and draws shapes onto it
    public void clearComponents() throws InterruptedException {
        gc.clearRect(0, 0, 1500, 1500);
        lineManager.getLines().clear();
        drawBaseComponents();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        circle.construct(this.triangle);
        gc = canvas.getGraphicsContext2D();
    }
}