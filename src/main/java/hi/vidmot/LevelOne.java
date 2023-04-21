package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.LevelBase;
import hi.vinnsla.LevelInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LevelOne extends LevelBase implements Initializable{
    @FXML
    Pane fxBubbles;
    @FXML
    Pane fxExtraBubbles;
    @FXML
    Player fxPlayer;
    @FXML
    BarView fxBarView;
    @FXML
    Label fxInfoText;
    final double levelTimer = 20; // sec per level
    final double xBounderies = 800;
    final double yBounderies = 450;
    final String nextLevel = "level-two.fxml";

    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameManager.sendLevelInfo(fxPlayer, fxBubbles, fxExtraBubbles, xBounderies, yBounderies, levelTimer, fxBarView, this, fxInfoText, true, 1);
    }

    public void loadNextLevel() throws IOException {
        FXMLLoader level = new FXMLLoader(this.getClass().getResource(nextLevel));
        Parent parent = (Parent)level.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage)fxPlayer.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void loadNextLevel(String levelToLoad) throws IOException {
        FXMLLoader level = new FXMLLoader(this.getClass().getResource(levelToLoad));
        Parent parent = (Parent)level.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage)fxPlayer.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
