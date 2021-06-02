package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class BeamInfoStage extends Application
{
    /**
     * 单例模式保证只弹出唯一的窗口
     */
    private static Stage infoStage = null;

    public static Stage showBeamInfo() {
        if(infoStage == null) {
            infoStage = new Stage();
        }
        return infoStage;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        URL infoUrl = getClass().getResource("../scene/BeamInfoScene.fxml");
        FXMLLoader infoLoader = new FXMLLoader();
        infoLoader.setLocation(infoUrl);
        Parent root = infoLoader.load();

        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("预制梁信息面板");
        Scene infoScene = new Scene(root, 1200, 800);
        stage.setScene(infoScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(showBeamInfo());
    }
}
