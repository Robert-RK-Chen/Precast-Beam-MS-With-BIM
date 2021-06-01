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
     * 单例模式
     */
    private static Stage beamInfoStage = null;

    public static Stage getBeamInfoStage()
    {
        if(beamInfoStage == null)
        {
            beamInfoStage = new Stage();
        }
        return beamInfoStage;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        URL beamInfoSceneUrl = getClass().getResource("../scene/BeamInfoScene.fxml");
        FXMLLoader beamInfoSceneLoader = new FXMLLoader();
        beamInfoSceneLoader.setLocation(beamInfoSceneUrl);

        Parent root = beamInfoSceneLoader.load();
        Scene beamInfoScene = new Scene(root, 1200, 800);

        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());

        stage.getIcons().add(icon);
        stage.setTitle("预制梁信息面板");
        stage.setScene(beamInfoScene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(getBeamInfoStage());
    }
}
