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
public class BeamInfoStage extends Application
{
    // preBeamId : 作为主界面与添加预制梁信息界面的中间信息
    // basicInfoStage : 添加预制梁基本信息界面
    private String preBeamId = null;
    private static Stage basicInfoStage = null;

    /**
     * @return Stage basicInfoStage
     * 使用单例模式保证只会弹出一个界面
     */
    public static Stage getBasicInfoStage()
    {
        if (basicInfoStage == null)
        {
            basicInfoStage = new Stage();
        }
        return basicInfoStage;
    }

    @Override
    public void start(Stage stage)
    {
        // infoUrl : 预制梁信息界面的 FXML 资源
        // infoLoader : 加载器，用于动态控制窗口
        URL infoUrl = getClass().getResource("../scene/BeamInfoScene.fxml");
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        FXMLLoader infoLoader = new FXMLLoader();
        infoLoader.setLocation(infoUrl);

        // 加载图标资源
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        // 设置场景以及窗口的信息
        try
        {
            Parent root = infoLoader.load();
            Scene infoScene = new Scene(root, 1200, 800);
            stage.setTitle("预制梁信息面板");
            stage.setScene(infoScene);
            stage.setResizable(false);

            // infoController : BeamInfoController 类的实例，窗口的控制器
            BeamInfoController infoController = infoLoader.getController();
            infoController.init(preBeamId);
            stage.show();
        }
        catch (IOException ioException)
        {
            new Alert(Alert.AlertType.ERROR, "预制梁信息界面加载失败！").show();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage()
    {
        start(getBasicInfoStage());
    }

    public void initializePreBeam(String id)
    {
        preBeamId = id;
    }

    public void closeStage()
    {
        basicInfoStage.close();
    }
}
