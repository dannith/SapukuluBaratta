package hi.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class StigView extends Pane {
    public StigView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("score-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
