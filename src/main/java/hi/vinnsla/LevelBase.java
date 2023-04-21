package hi.vinnsla;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class LevelBase {
    public abstract void loadNextLevel() throws IOException;
    public abstract void loadNextLevel(String levelToLoad) throws IOException;
}
