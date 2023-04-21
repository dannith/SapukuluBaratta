package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.GameState;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.sqrt;

public class Player extends Pane {


    @FXML
    public Rectangle fxPlayer;
    @FXML
    public Rectangle fxHook;
    private boolean hookOut = false;
    private double hookSpeed = 600;

    public static Map<KeyCode, Boolean> inputKeys;

    private double xSpeed = 240;

    public void initKeys(){
            inputKeys = new HashMap<>();
            inputKeys.put(KeyCode.UP, false);
            inputKeys.put(KeyCode.LEFT, false);
            inputKeys.put(KeyCode.DOWN, false);
            inputKeys.put(KeyCode.RIGHT, false);

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
        fxHook.setY(10000);
        hookOut = false;
    }

    public void update(double deltaTime, List<Bubble> bubbles, List<Bubble> extraBubbles) {
        if(inputKeys.get(KeyCode.LEFT) != inputKeys.get(KeyCode.RIGHT)){
            if(inputKeys.get(KeyCode.LEFT)) fxPlayer.setX(fxPlayer.getX() - xSpeed * deltaTime);
            else fxPlayer.setX(fxPlayer.getX() + xSpeed * deltaTime);
        }
        if(inputKeys.get(KeyCode.UP) && !hookOut){
            fxHook.setX(fxPlayer.getX());
            fxHook.setY(fxPlayer.getY() + fxPlayer.getHeight());
            hookOut = true;
        }
        for(int i = 0; i < extraBubbles.size(); i++){
            if(circleRect(extraBubbles.get(i).getWorldCenterX(), extraBubbles.get(i).getWorldCenterY(), extraBubbles.get(i).fxBubble.getRadius(),
                    getHookWorldX(), getHookWorldY(), fxHook.getWidth(), fxHook.getHeight()) && hookOut){
                fxHook.setY(10000);
                hookOut = false;
                extraBubbles.get(i).blowUp();
            }
            if(circleRect(extraBubbles.get(i).getWorldCenterX(), extraBubbles.get(i).getWorldCenterY(), extraBubbles.get(i).fxBubble.getRadius(),
                    getPlayerWorldX(), getPlayerWorldY(), fxPlayer.getWidth(), fxPlayer.getHeight())){
                GameManager.setState(GameState.lose);
                return;
            }
        }

        for(Bubble bubble : bubbles){
            if(circleRect(bubble.getWorldCenterX(), bubble.getWorldCenterY(), bubble.fxBubble.getRadius(),
                    getHookWorldX(), getHookWorldY(), fxHook.getWidth(), fxHook.getHeight()) && hookOut){
                fxHook.setY(10000);
                hookOut = false;
                bubble.blowUp();
            }
            if(circleRect(bubble.getWorldCenterX(), bubble.getWorldCenterY(), bubble.fxBubble.getRadius(),
                    getPlayerWorldX(), getPlayerWorldY(), fxPlayer.getWidth(), fxPlayer.getHeight())){
                GameManager.setState(GameState.lose);
                return;
            }
        }

        if(hookOut){
            fxHook.setY(fxHook.getY() - hookSpeed * deltaTime);
            if(fxHook.getY() + getLayoutY() < 0){
                fxHook.setY(10000);
                hookOut = false;
            }
        }
    }

    public double getPlayerWorldX(){
        return getLayoutX() + fxPlayer.getX();
    }
    public double getPlayerWorldY(){
        return getLayoutY() + fxPlayer.getY();
    }
    public double getHookWorldX(){
        return getLayoutX() + fxHook.getX();
    }
    public double getHookWorldY(){
        return getLayoutY() + fxHook.getY();
    }
    boolean circleRect(double cx, double cy, double radius, double rx, double ry, double rw, double rh) {

        // temporary variables to set edges for testing
        double testX = cx;
        double testY = cy;

        // which edge is closest?
        if (cx < rx)         testX = rx;      // test left edge
        else if (cx > rx+rw) testX = rx+rw;   // right edge
        if (cy < ry)         testY = ry;      // top edge
        else if (cy > ry+rh) testY = ry+rh;   // bottom edge

        // get distance from closest edges
        double distX = cx-testX;
        double distY = cy-testY;
        double distance = sqrt( (distX*distX) + (distY*distY) );

        // if the distance is less than the radius, collision!
        if (distance <= radius) {
            return true;
        }
        return false;
    }
    //andri
}
