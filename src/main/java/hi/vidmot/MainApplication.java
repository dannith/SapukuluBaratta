package hi.vidmot;

import hi.vinnsla.GameManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
private Image setIcon(){
    return new Image("bubble.png");

}
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forsida-view.fxml"));
        stage.setTitle("Bubble Struggle");
        Scene scene  = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(setIcon());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
