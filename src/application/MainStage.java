package application;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @author Robert Chen
 */
public class MainStage extends Application {
    private static Stage mainStage = null;

    public static Stage getMainStage() {
        if (mainStage == null) {
            mainStage = new Stage();
        }
        return mainStage;
    }

    @Override
    public void start(Stage primaryStage) {
        URL mainUrl = getClass().getResource("../scene/MainScene.fxml");
        FXMLLoader mainLoader = new FXMLLoader(mainUrl);

        try {
            Parent root = mainLoader.load();
            Scene mainScene = new Scene(root, 1200, 800);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                primaryStage.getIcons().add(icon);
            }

            primaryStage.setScene(mainScene);
            primaryStage.setTitle("预制梁场生产管理系统");
            primaryStage.setResizable(false);
            MainController mainController = mainLoader.getController();
            mainController.setBeamHashTable();
            mainController.readDatabase();
            primaryStage.show();
        } catch (IOException ioException) {
            Alert sceneLoadFailed = new Alert(Alert.AlertType.ERROR);
            sceneLoadFailed.setTitle("界面资源加载失败！");
            sceneLoadFailed.setHeaderText("请联系技术支持以获得更多帮助！");
            sceneLoadFailed.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showStage() { start(getMainStage()); }
}
