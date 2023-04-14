package hi.vinnsla;

import hi.vidmot.Controller;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;

public class Millisena {
    public static void lesa(Controller th, AnchorPane parentContainer, HBox anchorRoot, Button takki, String s) throws IOException {
        try {
            Parent root = FXMLLoader.load(th.getClass().getResource(s));
            Scene scene = takki.getScene();

            root.translateYProperty().set(scene.getHeight());
            parentContainer.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(2), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(event -> {
                parentContainer.getChildren().remove(anchorRoot);
            });
            timeline.play();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
