package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.GameState;
import hi.vinnsla.Hljod;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView fxPlayerImage;
    @FXML
    public Rectangle fxPlayer;
    @FXML
    public Rectangle fxHook;
    private boolean hookOut = false;
    private double hookSpeed = 600;

    public static Map<KeyCode, Boolean> inputKeys;

    private double xSpeed = 240;

    /**
     * Aðferð sem sér um að skipta og snúa leikmanni eftir örvatökkum sem hann ýtir á
     * @return mynd af leikmanni í réttri stöðu m.v. takka sem er ýtt á
     */
    private Image switchImage(){
        Image image;
        String sides = "playerguy.png";
        String forward = "aftanmynd_karlinn.png";
        if(inputKeys.get(KeyCode.LEFT) != inputKeys.get(KeyCode.RIGHT)){
            if(inputKeys.get(KeyCode.RIGHT)){
                image = new Image(sides);
                fxPlayerImage.setScaleX(1);
            } else {
                image = new Image(sides);
                fxPlayerImage.setScaleX(-1);
            }
            return image;
        }else
            return new Image(forward);
    }

    /**
     * Stillir takkana fyrir núverandi senu
     */
    public void initKeys() {
        inputKeys = new HashMap<>();
        inputKeys.put(KeyCode.UP, false);
        inputKeys.put(KeyCode.LEFT, false);
        inputKeys.put(KeyCode.DOWN, false);
        inputKeys.put(KeyCode.RIGHT, false);

        fxPlayer.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (inputKeys.containsKey(keyEvent.getCode())) inputKeys.put(keyEvent.getCode(), true);
            }
        });
        fxPlayer.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (inputKeys.containsKey(keyEvent.getCode())) inputKeys.put(keyEvent.getCode(), false);
            }
        });
    }

    /**
     * Constructor fyrir sérhæfða klasann player, sem sér einnig um að binda mynd við Rectangle.
     */
    public Player() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni
        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        fxPlayerImage.xProperty().bind(fxPlayer.xProperty());
    }

    public void reset() {
        fxPlayer.setX(0);
        fxPlayer.setY(0);
        fxHook.setY(10000);
        hookOut = false;
    }

    public void update(double deltaTime, List<Bubble> bubbles, List<Bubble> extraBubbles) {
        fxPlayerImage.setImage(switchImage());
        if (inputKeys.get(KeyCode.LEFT) != inputKeys.get(KeyCode.RIGHT)) {
            if (inputKeys.get(KeyCode.LEFT)) fxPlayer.setX(fxPlayer.getX() - xSpeed * deltaTime);
            else fxPlayer.setX(fxPlayer.getX() + xSpeed * deltaTime);
        }
        if (inputKeys.get(KeyCode.UP) && !hookOut) {
            fxHook.setX(fxPlayer.getX());
            fxHook.setY(fxPlayer.getY() + fxPlayer.getHeight());
            Hljod.shoot();
            hookOut = true;
        }

        for (int i = 0; i < extraBubbles.size(); i++) {
            if (circleRect(extraBubbles.get(i).getWorldCenterX(), extraBubbles.get(i).getWorldCenterY(), extraBubbles.get(i).fxBubble.getRadius(),
                    getHookWorldX(), getHookWorldY(), fxHook.getWidth(), fxHook.getHeight()) && hookOut) {
                fxHook.setY(10000);
                hookOut = false;
                extraBubbles.get(i).blowUp();
            }
            if (circleRect(extraBubbles.get(i).getWorldCenterX(), extraBubbles.get(i).getWorldCenterY(), extraBubbles.get(i).fxBubble.getRadius(),
                    getPlayerWorldX(), getPlayerWorldY(), fxPlayer.getWidth(), fxPlayer.getHeight())) {
                GameManager.setState(GameState.lose);
                return;
            }
        }

        for (Bubble bubble : bubbles) {
            if (circleRect(bubble.getWorldCenterX(), bubble.getWorldCenterY(), bubble.fxBubble.getRadius(),
                    getHookWorldX(), getHookWorldY(), fxHook.getWidth(), fxHook.getHeight()) && hookOut) {
                fxHook.setY(10000);
                hookOut = false;
                bubble.blowUp();
            }
            if (circleRect(bubble.getWorldCenterX(), bubble.getWorldCenterY(), bubble.fxBubble.getRadius(),
                    getPlayerWorldX(), getPlayerWorldY(), fxPlayer.getWidth(), fxPlayer.getHeight())) {
                GameManager.setState(GameState.lose);
                return;
            }
        }

        if (hookOut) {
            fxHook.setY(fxHook.getY() - hookSpeed * deltaTime);
            if (fxHook.getY() + getLayoutY() < 0) {
                fxHook.setY(10000);
                hookOut = false;
            }
        }
    }

    public double getPlayerWorldX() {
        return getLayoutX() + fxPlayer.getLayoutX() + fxPlayer.getX();
    }

    public double getPlayerWorldY() {
        return getLayoutY() + fxPlayer.getLayoutY() + fxPlayer.getY();
    }

    public double getHookWorldX() {
        return getLayoutX() + fxHook.getLayoutX() + fxHook.getX();
    }

    public double getHookWorldY() {

        return getLayoutY() + fxHook.getLayoutY() + fxHook.getY();
    }

    boolean circleRect(double cx, double cy, double radius, double rx, double ry, double rw, double rh) {

        radius *= 0.8;

        double testX = cx;
        double testY = cy;


        if (cx < rx) testX = rx;
        else if (cx > rx + rw) testX = rx + rw;
        if (cy < ry) testY = ry;
        else if (cy > ry + rh) testY = ry + rh;

        double distX = cx - testX;
        double distY = cy - testY;
        double distance = sqrt((distX * distX) + (distY * distY));


        if (distance <= radius) {
            return true;
        }
        return false;
    }
}
