package hi.vinnsla;

import hi.vidmot.Bubble;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

enum GameState{
    reset,
    starting,
    ongoing,
    lose
}

public class GameManager {
    private static double deltaTime;
    private static long lastTime;
    private static double elapsedTime = 0;
    private static int elapsedSeconds = 0;
    private static double countdown;
    private static double timer;

    private static List<Bubble> levelBubbles;

    public static GameState state = GameState.reset;


    public static void initManager(){
        deltaTime = 0;
        lastTime = System.nanoTime();
        initGameLoop();
    }

    private static void initGameLoop() {
        KeyFrame k = new KeyFrame(Duration.millis(20),
                e-> {
                    gameLoop();
                });
        Timeline t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        init();
    }

    public static void init(){
        /*
            Tæmir núverandi hluti í arraylist.
            Setja hluti úr fxml skrá núverandi borðs í arraylist sem verður svo unnið með
            Vistar staðsetningu þeirra.
         */
        state = GameState.reset;
        setup();
    }
    private static void setup(){
        /*
            Reset leikhluti (upphafsstilla þá)
            Stilla líf ef á við
            Taka út aukabubbles
            Reset leikjatimer
            Reset countdown.
         */
        countdown = 3;
        timer = 10;
    }

    private static void gameLoop() {
        long time = System.nanoTime();
        deltaTime = (time - lastTime) / 1000000000.0; // Seconds
        lastTime = time;
        elapsedTime += deltaTime;
        if (elapsedTime > 1) elapsedSeconds += elapsedTime--;
        update();
    }

    private static void update(){
        if(countdown > 0) countdown -= deltaTime;
        else if(timer > 0){
            System.out.println("Leikur byrjaður");
            timer -= deltaTime;
            for(Bubble bubble : levelBubbles)
                bubble.update(deltaTime);
        }
        else{
            System.out.println("Game Over, kalla á setup");
            setup();
        }
    }

    public static void setBubbles(List<Bubble> bubbles) {
        levelBubbles = bubbles;
    }
}
