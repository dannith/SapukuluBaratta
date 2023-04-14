package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.LevelInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LevelOne implements LevelInfo, Initializable {

    @FXML
    Bubble fxBubble1;
    List<Bubble> bubbles = new ArrayList<>();

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
        GameManager.setBubbles(bubbles);
        GameManager.initManager();
    }
}
