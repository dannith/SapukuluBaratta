package hi.vinnsla;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Hljod {
    public static final String forsidatonlist = "target/classes/hi/Tonlist/Forsida.mp3";
    public static final String pop = "target/classes/hi/Tonlist/popBubble.mp3";
    private static MediaPlayer mediaPlayer1;
    private static MediaPlayer mediapop;

    public static void main() {
        File file1 = new File(forsidatonlist);
        Media media1 = new Media(file1.toURI().toString());
        mediaPlayer1 = new MediaPlayer(media1);
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer1.play();
    }

    public static void mainMute() {
        if (mediaPlayer1.getVolume() > 0) {
            mediaPlayer1.setVolume(0);
        }
    }

    public static void mainunMute() {
        if (mediaPlayer1.getVolume() == 0) {
            mediaPlayer1.setVolume(1);
        }
    }

    public static void pop() {
        File file2 = new File(pop);
        Media media2 = new Media(file2.toURI().toString());
        mediapop = new MediaPlayer(media2);
        mediapop.setCycleCount(1);
        mediapop.play();
    }

    public static void popmute() {
        if (mediapop.getVolume() > 0) {
            mediapop.setVolume(0);
        }
    }

    public static void popunmute() {
        if (mediapop.getVolume() == 0) {
            mediapop.setVolume(1);
        }
    }
}
