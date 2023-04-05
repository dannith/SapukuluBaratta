package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
    @FXML
    public void onExitButton(ActionEvent actionEvent) {
        System.exit(2);
    }
}