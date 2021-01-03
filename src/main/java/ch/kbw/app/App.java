package ch.kbw.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("StartScreenView.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Bertrands Paradox - Main Menu");
        primaryStage.getIcons().add(new Image("BertrandsParadox-Logo.png"));

        Scene newScene = new Scene(root, 500, 200);

        primaryStage.setScene(newScene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
