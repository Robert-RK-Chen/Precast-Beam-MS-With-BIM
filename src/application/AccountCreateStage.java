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
public class AccountCreateStage extends Application {
    private static Stage accountCreateStage = null;

    public static Stage getAccountCreateStage() {
        if (accountCreateStage == null) {
            accountCreateStage = new Stage();
        }
        return accountCreateStage;
    }

    @Override
    public void start(Stage stage) {
        URL accountCreateUrl = getClass().getResource("../scene/AccountCreateScene.fxml");
        FXMLLoader accountCreateLoader = new FXMLLoader(accountCreateUrl);

        try {
            Parent root = accountCreateLoader.load();
            Scene accountCreateScene = new Scene(root, 500, 700);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                stage.getIcons().add(icon);
            }

            stage.setScene(accountCreateScene);
            stage.setTitle("创建”梁场通“账号");
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

    public void showStage() {
        start(getAccountCreateStage());
    }
}
