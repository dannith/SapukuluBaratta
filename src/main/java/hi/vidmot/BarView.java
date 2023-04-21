package hi.vidmot;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BarView extends Pane {
    @FXML
    public ProgressBar fxProgressBar;
    @FXML
    Label fxLivesLeftLabel;
    @FXML
    Label fxStigLabel;

    /**
     * Barview Constructor sem sér um að lesa inn fxml skrána fyrir sérhæfða klasann bar-view
     */
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
    /**
     * Aðferð sem sér um að binda viðmótshluti
     */

    public void initBinds(IntegerProperty lives, IntegerProperty score, DoubleProperty levelProgress) {
        fxLivesLeftLabel.textProperty().bind(lives.asString());
        fxStigLabel.textProperty().bind(score.asString());
        fxProgressBar.progressProperty().bind(levelProgress);
    }
}