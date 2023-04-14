package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.LevelInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LevelOne implements LevelInfo, Initializable {

    @FXML
    Bubble fxBubble1;
    @FXML
    Player fxPlayer;
    @FXML
    public Rectangle rect1, rect2, rect3, rect4, rect5, rect6;

    List<Bubble> bubbles = new ArrayList<>();
    List<Rectangle> collidables = new ArrayList<>();

    double levelTimer = 100; // sec per level


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
        collidables.add(rect1);
        collidables.add(rect2);
        collidables.add(rect3);
        collidables.add(rect4);
        collidables.add(rect5);
        collidables.add(rect6);
        GameManager.setBubbles(bubbles);
        GameManager.sendLevelInfo(bubbles, 10, collidables, fxPlayer, fxPlayer.getScene());
    }
}
