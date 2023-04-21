package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.Hljod;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    private int scoreValue;
    final private double gravity = 980;
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
    }

    public Bubble(String id){
        this();
        setId(id);
    }

    public void init() {
        if(getId() != null){
            switch(getId().substring(0,1)){
                case "l":
                    scoreValue = 400;
                    fxBubble.setRadius(35);
                    fxBubble.setFill(Color.RED);
                    bounce = -600;
                    break;
                case "m":
                    scoreValue = 200;
                    fxBubble.setRadius(25);
                    fxBubble.setFill(Color.GREEN);
                    bounce = -450;
                    break;
                case "s":
                    scoreValue = 50;
                    fxBubble.setRadius(10);
                    fxBubble.setFill(Color.BLUE);
                    bounce = -360;
                    break;
                default:
                    System.out.println("MISSING ID ON: "+this);
                    break;

            }
            if(getId().substring(1,2).equals("r")) xStartSpeed = -150;
            else xStartSpeed = 150;
            if(getId().length() == 3 && getId().substring(2,3).equals("b")) yStartSpeed = bounce;
            else yStartSpeed = 0;
        } else{
            System.out.println("MISSING ID ON: "+this);
        }
        xStartPos = 0;
        yStartPos = 0;
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
        return getLayoutX() + fxBubble.getLayoutX() + fxBubble.getCenterX();
    }

    public double getWorldCenterY(){
        return getLayoutY() + fxBubble.getLayoutY() + fxBubble.getCenterY();
    }

    public void blowUp() {
        GameManager.increaseScore(scoreValue);
        Hljod.pop();
        disable();
    }  // Eftir að kúla skemmist, hverfur (og býr til 2 nýjar kúlur ef á við)
    protected void disable(){
        String id ="";
        if(getId() != null) {
            switch (getId().substring(0, 1)) {
                case "l":
                    id += "m";
                    break;
                case "m":
                    id += "s";
                    break;
                default:
                    System.out.println("MISSING ID ON: " + this);
                    break;
            }
            if (id.equals("m") || id.equals("s")) {
                GameManager.spawnBubble(fxBubble.getCenterX() + getLayoutX(), fxBubble.getCenterY() + getLayoutY(), id + "rb");
                GameManager.spawnBubble(fxBubble.getCenterX() + getLayoutX(), fxBubble.getCenterY() + getLayoutY(), id + "lb");
            }
            enabled = false;
            xPosition.set(OUT_OF_BOUNDS);
            yPosition.set(OUT_OF_BOUNDS);
        }
    }

    public boolean isEnabled(){
        return enabled;
    }
}