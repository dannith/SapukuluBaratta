package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControlsView extends Pane {
    public ControlsView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("controler-view.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni
        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    public Text fxTexti;

    @FXML
    public void onClickDown(ActionEvent actionEvent) {
        fxTexti.setText("Player moves down");
    }

    @FXML
    public void onClickLeft(ActionEvent actionEvent) {
        fxTexti.setText("Player moves Left");
    }

    @FXML
    public void onClickRight(ActionEvent actionEvent) {
        fxTexti.setText("Player moves Right");
    }

    @FXML
    public void onClickUp(ActionEvent actionEvent) {
        fxTexti.setText("Player moves Up");
    }
}
