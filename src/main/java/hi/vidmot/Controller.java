package hi.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import hi.vinnsla.Hljod;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    public Region content;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Hljod.spila();
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(content, xPosition.get()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -30000))
        );
        timeline.play();
    }




    private void setBackgroundPositions(Region region, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition/6 + "px bottom," +
                "left " + xPosition/5 + "px bottom," +
                "left " + xPosition/4 + "px bottom," +
                "left " + xPosition/3 + "px bottom," +
                "left " + xPosition/2 + "px bottom," +
                "left " + xPosition + "px bottom;";
        region.setStyle(style);
    }

    @FXML
    public void onExitButton(ActionEvent actionEvent) {
        System.exit(2);
    }


}