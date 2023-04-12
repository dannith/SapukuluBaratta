package hi.vidmot;


import hi.vinnsla.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    @FXML
    public Button fxPlayButton;
    @FXML
    public Button fxScoreBoardButton;
    @FXML
    public Button fxControlsButton;
    @FXML
    public StackPane fxContent;
    @FXML
    public AnchorPane fxAnchorRoot;

    public boolean opid = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Hljod.play();

        // er hægt að setja þetta í annan klasa ?
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(fxContent, xPosition.get()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -30000))
        );
        timeline.play();
    }




    private void setBackgroundPositions(StackPane fxContent, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition/6 + "px bottom," +
                "left " + xPosition/5 + "px bottom," +
                "left " + xPosition/4 + "px bottom," +
                "left " + xPosition/3 + "px bottom," +
                "left " + xPosition/2 + "px bottom," +
                "left " + xPosition + "px bottom;";
        fxContent.setStyle(style);
    }

    @FXML
    public void onMuteButton(ActionEvent actionEvent) {
        Hljod.mute();
    }

    public void onSoundHandler(ActionEvent event) {
        Hljod.unmute();
    }

    public void onPlay(ActionEvent event) {
        //í leik
    }

    public void onScoreBoard(ActionEvent event) throws IOException {
        if(opid == false){
            Millisena.lesa(this, fxContent, fxAnchorRoot, fxScoreBoardButton, "score-view.fxml");
            opid = true;
        }
    }

    public void onControles(ActionEvent event) throws IOException {
        if (opid == false){
            Millisena.lesa(this,fxContent,fxAnchorRoot, fxControlsButton, "controler-view.fxml");
            opid = true;
        }
    }
}