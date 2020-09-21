package RandomEndpoint.controller;

<<<<<<< HEAD
import javafx.event.ActionEvent;
=======
>>>>>>> 5d36ac796fe19704a55904687f4e50fdc375dc19
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
<<<<<<< HEAD
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.awt.*;
=======
>>>>>>> 5d36ac796fe19704a55904687f4e50fdc375dc19
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
<<<<<<< HEAD
    private Point middlepoint;
    double firstY = Math.sqrt((3.0 * Math.pow(200.0, 2.0))/4.0);
=======
>>>>>>> 5d36ac796fe19704a55904687f4e50fdc375dc19

    public Controller(){
    }

<<<<<<< HEAD
    private void drawTriangle() {

        int firstX = 250;
        int secondX = 150;
        int thirdX = 350;


        int secondY = 300;
        int thirdY = 300;

        gc.setStroke(Color.BLUE);
        gc.strokePolygon(new double[]{firstX, secondX,thirdX},
                new double[]{firstY, secondY, thirdY}, 3);

    }

    private void constructionLines(){
        gc.setStroke(Color.BLUE);
        gc.strokeLine(150, 300, 250, firstY/2);
    }

    private void drawOutterCircle(){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        drawTriangle();
        constructionLines();

=======
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
>>>>>>> 5d36ac796fe19704a55904687f4e50fdc375dc19
    }
}
