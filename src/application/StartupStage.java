package application;

import controller.StartupController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class StartupStage extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        URL infoUrl = getClass().getResource("../scene/StartupScene.fxml");
        FXMLLoader infoLoader = new FXMLLoader();
        infoLoader.setLocation(infoUrl);

        Parent root = infoLoader.load();
        Scene infoScene = new Scene(root, 600, 380);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(infoScene);
        stage.setResizable(false);
        stage.show();
        Thread loadHibernateThread = new Thread(() -> Platform.runLater(() -> new StartupController().loadHibernate()));
        loadHibernateThread.start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
