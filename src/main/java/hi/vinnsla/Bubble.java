package hi.vinnsla;

import javafx.beans.property.DoubleProperty;

/**
 * Grunnklasi fyrir allar búbblur
 */
public abstract class Bubble {
    protected boolean enabled;
    protected double radius;
    protected double gravity; // hversu hratt kúlan fellur að jörðu : 0 fyrir þyngdarleysi
    protected double bounce;  // nýr hraði kúlu eftir hún rekst á gólf/loft
    protected double xStartPos;
    protected double yStartPos;
    protected DoubleProperty xPosition;
    protected DoubleProperty yPosition;
    static final double OUT_OF_BOUNDS = 20000; // Hvergi sjáanlegt á skjá.
    protected double xStartSpeed;
    protected double yStartSpeed;
    protected double xSpeed;    // láréttur hraði kúlu
    protected double ySpeed;    // lóðréttur hraði kúlu

    abstract void init(); // stilla bindings og kalla á reset().

    public void reset(){
        enabled = true;
        xPosition.set(xStartPos);
        yPosition.set(yStartPos);
        xSpeed = xStartSpeed;
        ySpeed = yStartSpeed;
    }
    public void update(double deltaTime){
        if(enabled){
            xPosition.set(xPosition.get() + xSpeed * deltaTime);
            yPosition.set(yPosition.get() + ySpeed * deltaTime);
            ySpeed += gravity * deltaTime;
            checkCollision();
        }
    }
    abstract void checkCollision(/* listi af Collidables veit ekki núna type, né return type*/);
    public abstract void blowUp();  // Eftir að kúla skemmist, hverfur (og býr til 2 nýjar kúlur ef á við)
    protected void disable(){
        enabled = false;
        xPosition.set(OUT_OF_BOUNDS);
        yPosition.set(OUT_OF_BOUNDS);
    }
    protected abstract void handleCollision();  // Uppfæra stefnu kúlu ef hún klessir á.
}
