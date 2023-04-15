package hi.vidmot;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * Grunnklasi fyrir allar búbblur
 */
public class Bubble extends Pane {
    @FXML
    public Circle fxBubble;
    private boolean enabled;
    private double radius;
    final private double gravity = 980;
    private double bounce;  // nýr hraði kúlu eftir hún rekst á gólf/loft
    private double xStartPos;
    private double yStartPos;
    private DoubleProperty xPosition;
    private DoubleProperty yPosition;
    static final double OUT_OF_BOUNDS = 20000; // Hvergi sjáanlegt á skjá.
    private double xStartSpeed;
    private double yStartSpeed;

    public String yoyoyo;
    private double xSpeed;    // láréttur hraði kúlu
    private double ySpeed;    // lóðréttur hraði kúlu



    public Bubble(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bubble-medium.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni
        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        init();
    }

    public void init() {
        xStartSpeed = 150;
        yStartSpeed = 0;
        xStartPos = 0;
        yStartPos = 0;
        bounce = -600;
        xPosition = new SimpleDoubleProperty(xStartPos);
        yPosition = new SimpleDoubleProperty(yStartPos);
        fxBubble.centerXProperty().bind(xPosition);
        fxBubble.centerYProperty().bind(yPosition);
        reset();
    } // stilla bindings og kalla á reset().

    public void reset(){
        enabled = true;
        xPosition.set(xStartPos);
        yPosition.set(yStartPos);
        xSpeed = xStartSpeed;
        ySpeed = yStartSpeed;
    }
    public void update(double deltaTime, double xBounderies, double yBounderies){
        if(enabled){
            xPosition.set(xPosition.get() + xSpeed * deltaTime);
            yPosition.set(yPosition.get() + ySpeed * deltaTime);
            ySpeed += gravity * deltaTime;
            checkCollision(xBounderies, yBounderies);
        }
    }
    public void checkCollision(double xBounderies, double yBounderies) {
        double xPos = fxBubble.getCenterX() + getLayoutX();
        double yPos = fxBubble.getCenterY() + getLayoutY();
        if((xPos + fxBubble.getRadius() >= xBounderies && xSpeed > 0) ||
                (xPos - fxBubble.getRadius() < 0 && xSpeed < 0) ){
            xSpeed *= -1;
        }
        if(yPos + fxBubble.getRadius() >= yBounderies && ySpeed > 0){
            ySpeed = bounce;
        }
    }

    public double getWorldCenterX(){
        return getLayoutX() + fxBubble.getCenterX();
    }

    public double getWorldCenterY(){
        return getLayoutY() + fxBubble.getCenterY();
    }

    public void blowUp() {
        disable();
    }  // Eftir að kúla skemmist, hverfur (og býr til 2 nýjar kúlur ef á við)
    protected void disable(){
        enabled = false;
        xPosition.set(OUT_OF_BOUNDS);
        yPosition.set(OUT_OF_BOUNDS);
    }

    public boolean isEnabled(){
        return enabled;
    }
}