package hi.vidmot;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;

/**
 * Grunnklasi fyrir allar búbblur
 */
public class Bubble extends Pane {
    @FXML
    public Circle fxBubble;
    private boolean enabled;
    private double radius;
    private double gravity = 98;
    private double bounce;  // nýr hraði kúlu eftir hún rekst á gólf/loft
    private double xStartPos;
    private double yStartPos;
    private DoubleProperty xPosition;
    private DoubleProperty yPosition;
    static final double OUT_OF_BOUNDS = 20000; // Hvergi sjáanlegt á skjá.
    private double xStartSpeed;
    private double yStartSpeed;
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
        xStartSpeed = 200;
        yStartSpeed = 0;
        xStartPos = 0;
        yStartPos = 0;
        bounce = 500;
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
    public void update(double deltaTime, List<Rectangle> levelCollidables){
        if(enabled){
            xPosition.set(xPosition.get() + xSpeed * deltaTime);
            yPosition.set(yPosition.get() + ySpeed * deltaTime);
            ySpeed += gravity * deltaTime;
            checkCollision(levelCollidables);
        }
    }
    public void checkCollision(List<Rectangle> levelCollidables) {
        double radius = fxBubble.getRadius();
        double realXPos = xPosition.get() + getLayoutX();
        double realYPos = yPosition.get() + getLayoutY();
        for(Rectangle rect : levelCollidables){

            double leftOverlap = rect.getLayoutX() + rect.getWidth() - realXPos - radius;
            boolean touchingLeft = leftOverlap < rect.getWidth() && leftOverlap > 0;
            double rightOverlap = realXPos + radius - rect.getLayoutX();
            boolean touchingRight = rightOverlap < rect.getWidth() && rightOverlap > 0;
            double topOverlap = rect.getLayoutY() + rect.getHeight() - realYPos - radius;
            boolean touchingTop = topOverlap < rect.getHeight() && topOverlap > 0;
            double bottomOverlap = realYPos + radius - rect.getLayoutY();
            boolean touchingBottom = bottomOverlap < rect.getHeight() && bottomOverlap > 0;

            if(touchingLeft != touchingRight){
                xSpeed *= -1;
                if(touchingLeft) xPosition.set(xPosition.get() + leftOverlap);
                else xPosition.set(xPosition.get() + rightOverlap);
            }
            if(touchingBottom != touchingTop){
                if(touchingBottom){
                    ySpeed = bounce;
                    yPosition.set(yPosition.get() - bottomOverlap);
                }
                else{
                    ySpeed *= -0.9;
                    yPosition.set(yPosition.get() + topOverlap);
                }
            }
        }

    }
    public void blowUp() {

    }  // Eftir að kúla skemmist, hverfur (og býr til 2 nýjar kúlur ef á við)
    protected void disable(){
        enabled = false;
        xPosition.set(OUT_OF_BOUNDS);
        yPosition.set(OUT_OF_BOUNDS);
    }
    protected void handleCollision() {

    }  // Uppfæra stefnu kúlu ef hún klessir á.
}