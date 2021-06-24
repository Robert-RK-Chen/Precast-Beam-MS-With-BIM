package application;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Robert Chen
 */
public class LoginStage extends Application {
    @Override
    public void start(Stage stage) {
        URL loginUrl = getClass().getResource("../scene/LoginScene.fxml");
        FXMLLoader loginLoader = new FXMLLoader(loginUrl);

        try {
            Parent root = loginLoader.load();
            Scene loginScene = new Scene(root, 800, 500);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                stage.getIcons().add(icon);
            }

            stage.setTitle("“梁场通”预制梁生产管理系统登陆页面");
            stage.setScene(loginScene);
            stage.setResizable(false);
            stage.show();

            ExecutorService singleThreadPool =
                    new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), new ThreadPoolExecutor.AbortPolicy());
            singleThreadPool.execute(() -> new LoginController().loadHibernate());
            singleThreadPool.shutdown();
        } catch (IOException ioException) {
            Alert sceneLoadFailed = new Alert(Alert.AlertType.ERROR);
            sceneLoadFailed.setTitle("软件初始化出现问题！");
            sceneLoadFailed.setHeaderText("请联系技术支持以获得更多帮助！");
            sceneLoadFailed.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
