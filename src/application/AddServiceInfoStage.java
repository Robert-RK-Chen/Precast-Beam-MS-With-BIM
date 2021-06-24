package application;

import controller.AddServiceInfoController;
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
public class AddServiceInfoStage extends Application {
    private String beamId;
    private static Stage serviceInfoStage = null;

    public static Stage getServiceInfoStage() {
        if (serviceInfoStage == null) {
            serviceInfoStage = new Stage();
        }
        return serviceInfoStage;
    }

    @Override
    public void start(Stage stage) {
        URL addServiceUrl = getClass().getResource("../scene/addServiceInfoScene.fxml");
        FXMLLoader addServiceLoader = new FXMLLoader(addServiceUrl);

        try {
            Parent root = addServiceLoader.load();
            Scene addServiceScene = new Scene(root, 500, 340);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                stage.getIcons().add(icon);
            }

            stage.setScene(addServiceScene);
            AddServiceInfoController addServiceInfoController = addServiceLoader.getController();
            addServiceInfoController.init(beamId);
            stage.setTitle("添加预制梁业务步骤");
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
        start(getServiceInfoStage());
    }

    public void initializePreBeam(String id) {
        beamId = id;
    }
}
