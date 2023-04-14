package hi.vidmot;

import hi.vinnsla.GameManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Player extends Pane {


    @FXML
    public Rectangle fxPlayer;
    public static Map<KeyCode, Boolean> inputKeys = new HashMap<>();

    private double xSpeed = 300;

    public void initKeys(){
            inputKeys.put(KeyCode.W, false);
            inputKeys.put(KeyCode.A, false);
            inputKeys.put(KeyCode.S, false);
            inputKeys.put(KeyCode.D, false);
            inputKeys.put(KeyCode.SPACE, false);

            fxPlayer.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if(inputKeys.containsKey(keyEvent.getCode())) inputKeys.put(keyEvent.getCode(), true);
                }
            });
            fxPlayer.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if(inputKeys.containsKey(keyEvent.getCode())) inputKeys.put(keyEvent.getCode(), false);
                }
            });
    }

    public Player(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni
        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void reset(){
        fxPlayer.setX(0);
        fxPlayer.setY(0);
    }

    public void update(double deltaTime) {
        if(inputKeys.get(KeyCode.A) != inputKeys.get(KeyCode.D)){
            if(inputKeys.get(KeyCode.A)) fxPlayer.setX(fxPlayer.getX() - xSpeed * deltaTime);
            else fxPlayer.setX(fxPlayer.getX() + xSpeed * deltaTime);
        }
    }
}
