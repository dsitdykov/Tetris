module com.example.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.tetris to javafx.fxml;
    exports com.tetris;
}