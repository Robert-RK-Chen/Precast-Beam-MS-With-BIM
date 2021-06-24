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
 * @author MING
 * @author Liu BaoYang
 * @author Wang YiYan
 * @author Robert Chen
 */
public class Show3dModelStage extends Application {
    private static Stage showModelStage = null;

    public static Stage getShowModelStage() {
        if (showModelStage == null) {
            showModelStage = new Stage();
        }
        return showModelStage;
    }

    @Override
    public void start(Stage primaryStage) {
        URL mainUrl = getClass().getResource("../scene/Show3DModelScene.fxml");
        FXMLLoader mainLoader = new FXMLLoader(mainUrl);

        try {
            Parent root = mainLoader.load();
            Scene mainScene = new Scene(root, 1000, 700);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                primaryStage.getIcons().add(icon);
            }
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("桥梁 3D 模型查看");
            primaryStage.setResizable(false);
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

    public void showStage() { start(getShowModelStage()); }
}
