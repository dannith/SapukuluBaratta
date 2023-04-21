package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.LevelInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LevelOne implements Initializable{
    @FXML
    Pane fxBubbles;
    @FXML
    Pane fxExtraBubbles;
    @FXML
    Player fxPlayer;
    @FXML
    BarView fxBarView;
    final double levelTimer = 20; // sec per level
    final double xBounderies = 800;
    final double yBounderies = 450;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("dafea");
        GameManager.sendLevelInfo(fxPlayer, fxBubbles, fxExtraBubbles, xBounderies, yBounderies, levelTimer, fxBarView);
    }
}
