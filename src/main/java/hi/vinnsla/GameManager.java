package hi.vinnsla;

import hi.vidmot.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class GameManager {
    private static double deltaTime;
    private static long lastTime;
    private static double elapsedTime = 0;
    private static int elapsedSeconds = 0;
    private static double countdown;
    final private static int maxLives = 8;
    public static IntegerProperty lives= new SimpleIntegerProperty(0);
    public static IntegerProperty score = new SimpleIntegerProperty(0);
    private static int maxScore = 0;
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
    private static Label levelInfoLabel;
    private static LevelBase levelController;
    private static boolean isFirstLevel;
    private static Timeline timeline;
    public static GameState state;


    public static void initManager(){
        deltaTime = 0;
        lastTime = System.nanoTime();
        initGameLoop();
    }

    private static void initGameLoop() {
        if(timeline == null) {
            KeyFrame k = new KeyFrame(Duration.millis(20),
                    e -> {
                        gameLoop();
                    });

            timeline = new Timeline(k);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public static void init(){
        levelBubblesSpawned = new ArrayList<>();
        for(Bubble bubble : levelBubbles)
            bubble.init();
        if(isFirstLevel)
            setState(GameState.reset);
        else
            setState(GameState.starting);
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

                    setState(GameState.win);
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
                maxScore=0;
                score.set(maxScore);
                lives.set(maxLives);
                setState(GameState.starting);
                break;
            case starting:
                score.set(maxScore);
                levelBubblesSpawned.clear();
                levelExtraBubbles.getChildren().clear();
                countdown = 3;
                levelPlayer.reset();
                levelProgress.set(1);
                for(Bubble bubble: levelBubbles)
                    bubble.reset();
                levelInfoLabel.setVisible(true);
                System.out.println("Starting..");

                state = GameState.starting;
                break;
            case ongoing:
                levelInfoLabel.setVisible(false);
                System.out.println("Round started");
                levelTimer = levelTimerMax;

                state = GameState.ongoing;
                break;
            case lose:
                lives.set(lives.get() - 1);
                System.out.println("Lost round");
                if(lives.get() == 0){
                    // búa til alarmbox sem vistar í highscore


                    System.out.println("Game Over");
                    try {
                        levelController.loadNextLevel("forsida-view.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else setState(GameState.starting);
                break;
            case win:
                maxScore += score.get();
                timeline.stop();
                timeline = null;
                try {
                    levelController.loadNextLevel();
                    levelPlayer.initKeys();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public static void increaseScore(int scoreIncrease){
        score.set(score.get()+scoreIncrease);
    }

    public static int getScore() {
        return score.get();
    }

    public static IntegerProperty scoreProperty() {
        return score;
    }

    public static void spawnBubble(double x, double y, String id){
        Bubble bubble = new Bubble(id);
        bubble.setLayoutX(x);
        bubble.setLayoutY(y);
        levelExtraBubbles.getChildren().add(bubble);
        levelBubblesSpawned.add(bubble);
        bubble.init();
    }

    public static void sendLevelInfo(Player player, Pane bubbles, Pane extraBubbles, double xBounderies, double yBounderies,
                                     double timer, BarView barView, LevelBase base, Label infoLabel, boolean firstLevel){
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

        levelInfoLabel = infoLabel;

        levelController = base;
        isFirstLevel = firstLevel;

        initManager();
        init();
    }
}
