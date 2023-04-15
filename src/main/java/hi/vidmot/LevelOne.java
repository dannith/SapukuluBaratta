package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.LevelInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Rectangle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LevelOne implements LevelInfo, Initializable {
    @FXML
    public ProgressBar fxMyProgressBar;
    @FXML
    Bubble fxBubble1, fxBubble2, fxBubble3;
    @FXML
    Player fxPlayer;

    List<Bubble> bubbles = new ArrayList<>();
    List<Rectangle> collidables = new ArrayList<>();

    final double levelTimer = 20; // sec per level
    final double xBounderies = 800;
    final double yBounderies = 450;


    @Override
    public List<Bubble> GetBubbles() {
        return bubbles;
    }

    @Override
    public double GetTimer() {
        return levelTimer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bubbles.add(fxBubble1);
        bubbles.add(fxBubble2);
        bubbles.add(fxBubble3);
        //GameManager.setBubbles(bubbles);
        GameManager.sendLevelInfo(bubbles, xBounderies, yBounderies, levelTimer, collidables, fxPlayer, fxPlayer.getScene(), fxMyProgressBar);
    }


}
