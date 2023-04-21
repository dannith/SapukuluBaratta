package hi.vidmot;

import hi.vinnsla.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StigView extends Pane {

    @FXML
    private ListView<Integer> fxScoreBoard;
    /**
     * Listi sem heldur utan um stig leikmanns í hverri tilraun
     */
    private static List<Integer> score = new ArrayList<>();

    /**
     * Aðferð sem bætir við stigum leikmanns í listann
     * @param stig
     */
    public static void addScore(int stig){
        score.add(stig);
    }

    /**
     * Aðferð sem loadar sérhæfða klasanum score-view ásamt því að uppfæra stigatöflu
     */
    public StigView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("score-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        addScore(GameManager.score.getValue());
        fxScoreBoard.getItems().addAll(score);

    }
}