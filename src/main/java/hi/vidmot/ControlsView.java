package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.IOException;

public class ControlsView extends Pane {
    public ControlsView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("controler-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
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
