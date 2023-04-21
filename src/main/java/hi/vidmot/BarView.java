package hi.vidmot;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BarView extends Pane {
    @FXML
    public ProgressBar fxProgressBar;
    @FXML
    Label fxLivesLeftLabel;
    @FXML
    Label fxStigLabel;


    public BarView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bar-view.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni
        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void initBinds(IntegerProperty lives, IntegerProperty score, DoubleProperty levelProgress) {
        fxLivesLeftLabel.textProperty().bind(lives.asString());
        fxStigLabel.textProperty().bind(score.asString());
        fxProgressBar.progressProperty().bind(levelProgress);
    }
}