package application;

import controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SettingStage extends Application
{
    private static Stage settingStage = null;

    public static Stage getSettingStage()
    {
        if (settingStage == null)
        {
            settingStage = new Stage();
        }
        return settingStage;
    }

    @Override
    public void start(Stage stage)
    {
        URL settingUrl = getClass().getResource("../scene/SettingScene.fxml");
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        FXMLLoader settingLoader = new FXMLLoader(settingUrl);

        // 加载图标资源
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        // 设置场景以及窗口的信息
        try
        {
            Parent root = settingLoader.load();
            Scene infoScene = new Scene(root, 400, 600);
            stage.setTitle("“梁场通”设置面板");
            stage.setScene(infoScene);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage()
    {
        start(getSettingStage());
    }
}
