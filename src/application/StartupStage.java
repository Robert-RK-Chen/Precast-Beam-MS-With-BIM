package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class StartupStage extends Application {
    private static Stage startupStage = null;
    public static Stage getStartupStage() {
        if (startupStage == null) { startupStage = new Stage(); }
        return startupStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL infoUrl = getClass().getResource("../scene/StartupScene.fxml");
        FXMLLoader infoLoader = new FXMLLoader();
        infoLoader.setLocation(infoUrl);

        Parent root = infoLoader.load();
        Scene infoScene = new Scene(root, 600, 380);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(infoScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
    public void showStage() throws Exception { start(getStartupStage()); }
    public void closeStage() { startupStage.close(); }
}
