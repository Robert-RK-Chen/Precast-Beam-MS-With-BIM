package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class MainStage extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        URL mainSceneUrl = getClass().getResource("../scene/MainScene.fxml");
        FXMLLoader mainSceneLoader = new FXMLLoader();
        mainSceneLoader.setLocation(mainSceneUrl);

        Parent root = mainSceneLoader.load();
        Scene mainScene = new Scene(root, 1200, 800);

        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("预制梁场生产管理系统");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
