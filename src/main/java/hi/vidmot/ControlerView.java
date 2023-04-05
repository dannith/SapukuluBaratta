package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ControlerView {
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
