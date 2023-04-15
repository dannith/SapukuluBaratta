package hi.vidmot;

import hi.vinnsla.GameManager;
import hi.vinnsla.GameState;

public class Stig {
    private LevelOne levelOne;
    private GameState gameState;

    private GameManager gameManager;
    private double Stig;

    public void setStig(double stig) {
        Stig = stig;
    }
    public void getStig(double stig){

    }

    public double timaBonus(){
        double stig = 0.0;
        if((gameState != GameState.ongoing && levelOne.levelTimer>0)) {
            stig = levelOne.levelTimer;
        }
        return stig;
    }


}
