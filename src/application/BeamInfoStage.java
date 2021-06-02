package application;

import controller.BeamInfoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class BeamInfoStage extends Application
{
    private Button preBeam = null;
    private static Stage infoStage = null;

    public static Stage showBeamInfo()
    {
        if (infoStage == null)
        {
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
        Scene infoScene = new Scene(root, 1200, 800);
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());

        stage.getIcons().add(icon);
        stage.setTitle("预制梁信息面板");
        stage.setScene(infoScene);
        stage.setResizable(false);

        BeamInfoController infoController = infoLoader.getController();
        infoController.initialize(preBeam);
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

    public void getPreBeam(Button button)
    {
        preBeam = button;
    }
}
