package hi.vidmot;

import hi.vidmot.Bubble;
import hi.vidmot.LevelOne;
import hi.vinnsla.GameManager;
import hi.vinnsla.GameState;

public class Stig {
    private LevelOne levelOne;
    private GameState gameState;
    private Bubble bubble;
    private double stig;


    public double getStig() {
        setStig(timaBonus() + kuluStig());
        return stig;
    }

    public void setStig(double stig) {
        this.stig = stig;
    }

    public double timaBonus() {
        stig = 0.0;
        if ((gameState != GameState.ongoing && levelOne.levelTimer > 0)) {
            stig += levelOne.levelTimer;
        }
        return stig;
    }

    public double kuluStig() {
        while (bubble.isDisabled()) {
            stig += 50;
        }
        return stig;
    }
}
