module hi.vidmot {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens hi.vidmot to javafx.fxml;
    exports hi.vidmot;
    exports hi.vinnsla;
    opens hi.vinnsla to javafx.fxml;
}