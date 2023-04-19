package hi.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BarView extends Pane {
    @FXML
    ProgressBar fxMyProgressbar;
    @FXML
    Label fxLivesLeftLabel;
    @FXML
    Label fxStigLabel;


    public BarView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bar-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ProgressBar getFxMyProgressbar() {
        return fxMyProgressbar;
    }

    public Label getFxLivesLeftLabel() {
        return fxLivesLeftLabel;
    }

    public Label getFxStigLabel() {
        return fxStigLabel;
    }
}
