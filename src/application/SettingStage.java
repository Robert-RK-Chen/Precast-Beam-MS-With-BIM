package application;

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
public class SettingStage extends Application {
    private static Stage settingStage = null;

    public static Stage getSettingStage() {
        if (settingStage == null) {
            settingStage = new Stage();
        }
        return settingStage;
    }

    @Override
    public void start(Stage stage) {
        URL settingUrl = getClass().getResource("../scene/SettingScene.fxml");
        FXMLLoader settingLoader = new FXMLLoader(settingUrl);

        try {
            Parent root = settingLoader.load();
            Scene settingScene = new Scene(root, 400, 600);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                stage.getIcons().add(icon);
            }
            stage.setTitle("“梁场通”设置面板");
            stage.setScene(settingScene);
            stage.setResizable(false);
            stage.show();
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

    public void showStage() { start(getSettingStage()); }
}
