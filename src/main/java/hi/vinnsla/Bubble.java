package hi.vinnsla;

import javafx.beans.property.DoubleProperty;

/**
 * Grunnklasi fyrir allar búbblur
 */
public abstract class Bubble {

    protected double radius;
    protected double gravity; // hversu hratt kúlan fellur að jörðu : 0 fyrir þyngdarleysi
    protected double bounce;  // nýr hraði kúlu eftir hún rekst á gólf/loft
    protected DoubleProperty xPosition;
    protected DoubleProperty yPosition;
    protected double xSpeed;    // láréttur hraði kúlu
    protected double ySpeed;    // lóðréttur hraði kúlu
    public abstract void update(double deltaTime);
    public abstract void checkCollision(/* listi af Collidables veit ekki núna type, né return type*/);
    public abstract void blowUp();  // Eftir að kúla skemmist, hverfur (og býr til 2 nýjar kúlur ef á við)
    protected abstract void handleCollision();  // Uppfæra stefnu kúlu ef hún klessir á.
}
