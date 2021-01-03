module ch.kbw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ch.kbw.app to javafx.fxml;
    exports ch.kbw.app;

    opens ch.kbw.endpointmethod to javafx.fxml;
    exports ch.kbw.endpointmethod;

    opens ch.kbw.midpointmethod to javafx.fxml;
    exports ch.kbw.midpointmethod;

    opens ch.kbw.radialpointmethod to javafx.fxml;
    exports ch.kbw.radialpointmethod;
}