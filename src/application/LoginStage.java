package application;

import controller.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class LoginStage extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        URL loginUrl = getClass().getResource("../scene/LoginScene.fxml");
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        FXMLLoader loginLoader = new FXMLLoader();
        loginLoader.setLocation(loginUrl);

        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        Parent root = loginLoader.load();
        Scene loginScene = new Scene(root, 800, 500);
        stage.setTitle("“梁场通”预制梁生产管理系统登陆页面");
        stage.setScene(loginScene);
        stage.setResizable(false);
        stage.show();
        Thread loadHibernateThread = new Thread(() -> Platform.runLater(() -> new LoginController().loadHibernate()));
        loadHibernateThread.start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
