package hi.vinnsla;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class Hljod {
    public static final String forsidatonlist = "C:\\Users\\andri\\IdeaProjects\\SapukuluBaratta\\src\\main\\resources\\hi\\Tonlist\\Forsida.mp3";
    //public final String leikjatonlist = "";
    private static File file;
    private static Media media;
    private static MediaPlayer mediaPlayer;

    public static void play(){
        file = new File(forsidatonlist);
        media = new Media(file.toURI().toString());
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
