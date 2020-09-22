package RandomEndpoint.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private Point middlepoint;
    double firstY = Math.sqrt((3.0 * Math.pow(200.0, 2.0)) / 4.0);

    public Controller() {
    }

    private void drawTriangle() {

        int firstX = 250;
        int secondX = 150;
        int thirdX = 350;


        int secondY = 300;
        int thirdY = 300;

        gc.setStroke(Color.BLUE);
        gc.strokePolygon(new double[]{firstX, secondX, thirdX},
                new double[]{firstY, secondY, thirdY}, 3);

    }

    private void constructionLines() {
        gc.setStroke(Color.BLUE);
        gc.strokeLine(150, 300, 250, firstY / 2);
    }

    private void drawOutterCircle() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        drawTriangle();
        constructionLines();

    }
}

