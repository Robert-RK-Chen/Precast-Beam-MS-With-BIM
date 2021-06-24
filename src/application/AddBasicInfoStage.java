package application;

import controller.AddBasicInfoController;
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
public class AddBasicInfoStage extends Application {
    private String preBeamId = null;
    private static Stage basicInfoAddStage = null;

    public static Stage getBasicInfoAddStage() {
        if (basicInfoAddStage == null) {
            basicInfoAddStage = new Stage();
        }
        return basicInfoAddStage;
    }

    @Override
    public void start(Stage stage) {
        URL basicInfoAddSceneUrl = getClass().getResource("../scene/addBasicInfoScene.fxml");
        FXMLLoader basicInfoAddLoader = new FXMLLoader(basicInfoAddSceneUrl);

        try {
            Parent root = basicInfoAddLoader.load();
            Scene addBasicScene = new Scene(root, 500, 420);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                stage.getIcons().add(icon);
            }

            stage.setScene(addBasicScene);
            AddBasicInfoController addBasicInfoController = basicInfoAddLoader.getController();
            addBasicInfoController.init(preBeamId);
            stage.setTitle("添加预处理的预制梁");
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
        start(getBasicInfoAddStage());
    }

    public void initializePreBeam(String id) {
        preBeamId = id;
    }
}
