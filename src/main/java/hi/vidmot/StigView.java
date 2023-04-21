package hi.vidmot;

import hi.vinnsla.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class StigView extends Pane {

    @FXML
    private ListView<Integer> fxScoreBoard;

    public StigView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("score-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //fxScoreBoard.getItems().add(GameManager.getScore());

    }
}