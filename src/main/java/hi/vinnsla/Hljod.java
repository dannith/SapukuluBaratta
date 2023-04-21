package hi.vinnsla;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Hljod {
    private static final String forsidatonlist = "target/classes/hi/Tonlist/Forsida.mp3";
    private static final String pop = "target/classes/hi/Tonlist/bubble_pop.wav";
    private static final String getReady = "target/classes/hi/Tonlist/get_ready.wav";
    private static final String shoot = "target/classes/hi/Tonlist/bubble_shot.wav";
    private static final String lose = "target/classes/hi/Tonlist/death.wav";
    private static final String win = "target/classes/hi/Tonlist/level_won.mp3";
    private static final String l1 = "target/classes/hi/Tonlist/l1.mp3";
    private static final String l2 = "target/classes/hi/Tonlist/l2.wav";
    private static final String l3 = "target/classes/hi/Tonlist/l3.wav";
    private static final String l4 = "target/classes/hi/Tonlist/l4.mp3";
    private static final String l5 = "target/classes/hi/Tonlist/l5.wav";
    private static MediaPlayer mediaPlayer1;
    private static MediaPlayer mediapop;
    private static boolean muted = false;

    public static void main() {
        if(mediaPlayer1 != null) {
            mediaPlayer1.stop();
        }
            File file1 = new File(forsidatonlist);
            Media media1 = new Media(file1.toURI().toString());
            mediaPlayer1 = new MediaPlayer(media1);
            mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer1.play();
    }

    public static void toggleMain(){
        if(mediaPlayer1 != null && !muted) {
            mediaPlayer1.setMute(!mediaPlayer1.isMute());
        }
    }

    public static void mainMute() {
        muted = true;
        if(mediaPlayer1 != null) mediaPlayer1.setMute(true);
    }

    public static void mainunMute() {
        muted = false;
        if(mediaPlayer1 != null) mediaPlayer1.setMute(false);
    }

    public static void level1(){
        if(mediaPlayer1 != null && !muted) {
            mediaPlayer1.stop();
            File file1 = new File(l1);
            Media media1 = new Media(file1.toURI().toString());
            mediaPlayer1 = new MediaPlayer(media1);
            mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer1.play();
        }
    }
    public static void level2(){
            if(mediaPlayer1 != null && !muted) {
                mediaPlayer1.stop();
                File file1 = new File(l2);
                Media media1 = new Media(file1.toURI().toString());
                mediaPlayer1 = new MediaPlayer(media1);
                mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer1.play();
            }
    }
    public static void level3(){
                if(mediaPlayer1 != null && !muted) {
                    mediaPlayer1.stop();
                    File file1 = new File(l3);
                    Media media1 = new Media(file1.toURI().toString());
                    mediaPlayer1 = new MediaPlayer(media1);
                    mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
                    mediaPlayer1.play();
                }
    }
    public static void level4(){
                    if(mediaPlayer1 != null && !muted) {
                        mediaPlayer1.stop();
                        File file1 = new File(l4);
                        Media media1 = new Media(file1.toURI().toString());
                        mediaPlayer1 = new MediaPlayer(media1);
                        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
                        mediaPlayer1.play();
                    }
    }
    public static void level5(){
                        if(mediaPlayer1 != null && !muted) {
                            mediaPlayer1.stop();
                            File file1 = new File(l5);
                            Media media1 = new Media(file1.toURI().toString());
                            mediaPlayer1 = new MediaPlayer(media1);
                            mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
                            mediaPlayer1.play();
                        }
    }

    public static void pop() {
        File file2 = new File(pop);
        Media media2 = new Media(file2.toURI().toString());
        mediapop = new MediaPlayer(media2);
        mediapop.setCycleCount(1);
        mediapop.play();
    }

    public static void getReady(){
        File file2 = new File(getReady);
        Media media2 = new Media(file2.toURI().toString());
        mediapop = new MediaPlayer(media2);
        mediapop.setCycleCount(1);
        mediapop.play();
    }

    public static void shoot(){
        File file2 = new File(shoot);
        Media media2 = new Media(file2.toURI().toString());
        mediapop = new MediaPlayer(media2);
        mediapop.setCycleCount(1);
        mediapop.play();
    }

    public static void lose(){
        File file2 = new File(lose);
        Media media2 = new Media(file2.toURI().toString());
        mediapop = new MediaPlayer(media2);
        mediapop.setCycleCount(1);
        mediapop.play();
    }

    public static void win(){
        File file2 = new File(win);
        Media media2 = new Media(file2.toURI().toString());
        mediapop = new MediaPlayer(media2);
        mediapop.setCycleCount(1);
        mediapop.play();
    }
}
