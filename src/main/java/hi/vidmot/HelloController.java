package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    public void onExitButton(ActionEvent actionEvent) {
        System.exit(2);
    }
}