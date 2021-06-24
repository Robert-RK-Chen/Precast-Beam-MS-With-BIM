package application;

import controller.BeamInfoController;
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
public class BeamInfoStage extends Application {
    private String preBeamId = null;
    private static Stage basicInfoStage = null;

    public static Stage getBasicInfoStage() {
        if (basicInfoStage == null) {
            basicInfoStage = new Stage();
        }
        return basicInfoStage;
    }

    @Override
    public void start(Stage stage) {
        URL infoUrl = getClass().getResource("../scene/BeamInfoScene.fxml");
        FXMLLoader infoLoader = new FXMLLoader(infoUrl);

        try {
            Parent root = infoLoader.load();
            Scene infoScene = new Scene(root, 1200, 800);
            URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
            if (iconUrl != null) {
                Image icon = new Image(iconUrl.toExternalForm());
                stage.getIcons().add(icon);
            }

            stage.setScene(infoScene);
            stage.setTitle("预制梁信息面板");
            stage.setResizable(false);
            BeamInfoController infoController = infoLoader.getController();
            infoController.init(preBeamId);
            stage.show();
        } catch (IOException ioException) {
            Alert sceneLoadFailed = new Alert(Alert.AlertType.ERROR);
            sceneLoadFailed.setTitle("界面资源加载失败！");
            sceneLoadFailed.setHeaderText("请联系技术支持以获得更多帮助！");
            sceneLoadFailed.show();
        }
    }

    public static void main(String[] args) { launch(args); }

    public void showStage() { start(getBasicInfoStage()); }

    public void initializePreBeam(String id) { preBeamId = id; }

    public void closeStage() { basicInfoStage.close(); }
}