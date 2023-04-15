package hi.vidmot;


import hi.vinnsla.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.File;
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
    public AnchorPane fxContent;
    @FXML
    public HBox fxAnchorRoot;
    @FXML
    public Pane fxpane;
    @FXML
    public ImageView fxmynd;
    @FXML
    public VBox fxmenus;
    @FXML
    public ControlsView fxControls;
    @FXML
    public StigView fxStigview;

    private boolean uppiControles;
    private boolean uppiScoreBoard;


    public boolean ready = true;
    public boolean onmute = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Hljod.play();

        fxControls.setTranslateY(500);
        fxStigview.setTranslateY(500);
        uppiControles = false;
        uppiScoreBoard =false;

        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(fxContent, xPosition.get()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -30000))
        );
        timeline.play();
    }

    private void setBackgroundPositions(AnchorPane fxContent, double xPosition) {
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
        File file1 = new File("src/main/resources/hi/Myndir/soundOn.png");
        Image unmute = new Image(file1.toURI().toString());

        File file2 = new File("src/main/resources/hi/Myndir/soundOff.png");
        Image mute = new Image(file2.toURI().toString());


        if(!onmute){
            fxmynd.setImage(mute);
            Hljod.mute();
            onmute = true;
        }else{
            fxmynd.setImage(unmute);
            Hljod.unmute();
            onmute = false;
        }
    }

    public void onPlay(ActionEvent event) throws IOException {
        Millisena.lesa(this, fxContent, fxAnchorRoot, fxPlayButton, "level-one.fxml");
        fxpane.setVisible(false);
        fxmenus.setVisible(false);
        GameManager.getPlayer().initKeys();
    }

    public void onScoreBoard(ActionEvent event) {
        if (ready){
            if (uppiControles){
                faraUpp(fxControls);
                uppiControles = false;
            }
                faraUpp(fxStigview);
                uppiScoreBoard=!uppiScoreBoard;
        }
    }
    /*
    public void uppfaeraStig(ActionEvent event){
        LevelOne levelOne = new LevelOne();
        if(levelOne.levelTimer > 0){

        }
    }

     */

    public void onControles(ActionEvent event) {
        if (ready){
            if (uppiScoreBoard){
                faraUpp(fxStigview);
                uppiScoreBoard = false;
            }
            faraUpp(fxControls);
            uppiControles=!uppiControles;
        }
    }

    private void faraUpp(Pane klasi){
        ready = false;
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(klasi.translateYProperty(), klasi.getTranslateY() - 500, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(2), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> {
            ready = true;
            if (klasi.getTranslateY() < 0) {
                klasi.setTranslateY(500);
            }
        });
        timeline.play();
    }
}