package ch.kbw.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author oliverthoma
 * @version 19.12.2020
 * <p>
 * The main StartScreen of the Application which enables navigation to all three parts of this project
 */

public class StartScreenController implements Initializable {

    @FXML
    public void handleButton50(ActionEvent event) throws IOException {
        Parent radialPointMethod = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RadialPointView.fxml")));
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        oldStage.setScene(new Scene(radialPointMethod));
        oldStage.setTitle("Bertrands Paradox - RadialPoint Method (1/2)");
    }

    @FXML
    public void handleButton33(ActionEvent event) throws IOException {
        Parent endPointMethod = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("EndPointView.fxml")));
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        oldStage.setScene(new Scene(endPointMethod));
        oldStage.setTitle("Bertrands Paradox - EndPoint Method (1/3)");
    }

    @FXML
    public void handleButton25(ActionEvent event) throws IOException {
        Parent midPointMethod = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MidPointView.fxml")));
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        oldStage.setScene(new Scene(midPointMethod));
        oldStage.setTitle("Bertrands Paradox - MidPoint Method (1/4)");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
