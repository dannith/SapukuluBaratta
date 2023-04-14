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
    final private static int maxLives = 5;
    private static int lives;

    private static List<Bubble> levelBubbles;

    public static GameState state;


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
        setState(GameState.reset);
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
        switch (state){
            case starting:
                if(countdown > 0) {
                    countdown -= deltaTime;
                }
                else setState(GameState.ongoing);
                break;
            case ongoing:
                timer -= deltaTime;
                for(Bubble bubble : levelBubbles)
                    bubble.update(deltaTime);
                if(timer <= 0)
                    setState(GameState.lose);
                break;
        }
    }

    public static void setState(GameState nextState){
        switch(nextState){
            case reset:
                lives = maxLives;
                setState(GameState.starting);
                break;
            case starting:
                countdown = 3;

                System.out.println("Starting..");
                state = GameState.starting;
                break;
            case ongoing:
                System.out.print("Round started");
                timer = 5;

                state = GameState.ongoing;
                break;
            case lose:
                lives--;
                System.out.println("Lost round");
                if(lives == 0){
                    setState(GameState.reset);
                    System.out.println("Game Over");
                } else setState(GameState.starting);
                break;
        }
    }

    public static void setBubbles(List<Bubble> bubbles) {
        levelBubbles = bubbles;
    }
}
