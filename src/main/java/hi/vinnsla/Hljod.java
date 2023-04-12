package hi.vinnsla;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Hljod {
    public static final String forsidatonlist = "target/classes/hi/Tonlist/Forsida.mp3";
    public static final String leikjatonlist = "";
    private static MediaPlayer mediaPlayer;

    public static void play(){
        File file = new File(forsidatonlist);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void mute(){
        if (mediaPlayer.getVolume()>0){
            mediaPlayer.setVolume(0);
        }
    }

    public static void unmute(){
        if(mediaPlayer.getVolume()==0){
            mediaPlayer.setVolume(1);
        }
    }
}
