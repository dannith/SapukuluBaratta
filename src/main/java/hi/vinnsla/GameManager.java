package hi.vinnsla;

import hi.vidmot.BarView;
import hi.vidmot.Bubble;
import hi.vidmot.LevelOne;
import hi.vidmot.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private static double deltaTime;
    private static long lastTime;
    private static double elapsedTime = 0;
    private static int elapsedSeconds = 0;
    private static double countdown;
    final private static int maxLives = 2;
    public static IntegerProperty lives= new SimpleIntegerProperty(0);
    public static IntegerProperty score = new SimpleIntegerProperty(0);
    private static Player levelPlayer;
    public static Player getPlayer(){
        return levelPlayer;
    }
    private static List<Bubble> levelBubbles;
    private static List<Bubble> levelBubblesSpawned;
    private static Pane levelExtraBubbles;
    private static double levelTimerMax;
    private static double levelTimer;
    private static BarView levelBarView;
    private static DoubleProperty levelProgress;
    private static double levelXBounderies;
    private static double levelYBounderies;


    public static GameState state;

    //andri
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

    }

    public static void init(){
        levelBubblesSpawned = new ArrayList<>();
        for(Bubble bubble : levelBubbles)
            bubble.init();
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
                levelTimer -= deltaTime;
                levelProgress.set(levelTimer/levelTimerMax);
                boolean gameWon = true;
                for(Bubble bubble : levelBubbles) {
                    if(bubble.isEnabled()) gameWon = false;
                    bubble.update(deltaTime, levelXBounderies, levelYBounderies);
                }
                for(Bubble bubble : levelBubblesSpawned){
                    if(bubble.isEnabled()) gameWon = false;
                    bubble.update(deltaTime, levelXBounderies, levelYBounderies);
                }
                if(gameWon){
                    setState(GameState.reset);
                    break;
                }
                levelPlayer.update(deltaTime, levelBubbles, levelBubblesSpawned);
                if(levelTimer <= 0)
                    setState(GameState.lose);
                break;
        }
    }

    public static void setState(GameState nextState){
        switch(nextState){
            case reset:
                lives.set(maxLives);
                setState(GameState.starting);
                break;
            case starting:
                levelBubblesSpawned.clear();
                levelExtraBubbles.getChildren().clear();
                countdown = 3;
                levelPlayer.reset();
                levelProgress.set(1);
                for(Bubble bubble: levelBubbles)
                    bubble.reset();
                System.out.println("Starting..");

                state = GameState.starting;
                break;
            case ongoing:
                System.out.println("Round started");
                levelTimer = levelTimerMax;

                state = GameState.ongoing;
                break;
            case lose:
                lives.set(lives.get() - 1);
                System.out.println("Lost round");
                if(lives.get() == 0){
                    setState(GameState.reset);
                    System.out.println("Game Over");
                } else setState(GameState.starting);
                break;
        }
    }

    public static void spawnBubble(double x, double y, String id){
        Bubble bubble = new Bubble(id);
        bubble.setLayoutX(x);
        bubble.setLayoutY(y);
        levelExtraBubbles.getChildren().add(bubble);
        levelBubblesSpawned.add(bubble);
        bubble.init();
    }

    public static void sendLevelInfo(Player player, Pane bubbles, Pane extraBubbles, double xBounderies, double yBounderies, double timer, BarView barView){
        levelPlayer = player;
        levelBubbles = new ArrayList<Bubble>();
        for(var bubble : bubbles.getChildren()){
            levelBubbles.add((Bubble) bubble);
        }
        levelExtraBubbles = extraBubbles;
        levelXBounderies = xBounderies;
        levelYBounderies = yBounderies;
        levelTimerMax = timer;

        levelProgress = new SimpleDoubleProperty(1);
        levelBarView = barView;
        barView.initBinds(lives, score, levelProgress);
        initManager();
        init();
    }
}
