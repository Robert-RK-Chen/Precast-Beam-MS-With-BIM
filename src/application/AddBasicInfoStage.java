package application;

import controller.AddBasicInfoController;
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
public class AddBasicInfoStage extends Application
{
    // preBeamId : 作为主界面与添加预制梁信息界面的中间信息
    // basicInfoAddStage : 添加预制梁基本信息界面
    private String preBeamId = null;
    private static Stage basicInfoAddStage = null;

    /**
     * @return Stage basicInfoAddStage : 添加基本信息界面
     * 使用单例模式保证只会弹出一个界面
     */
    public static Stage getBasicInfoAddStage()
    {
        if (basicInfoAddStage == null)
        {
            basicInfoAddStage = new Stage();
        }
        return basicInfoAddStage;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        // basicInfoAddSceneUrl : 添加基本信息界面的 FXML 资源
        // basicInfoAddLoader : 加载器，用于动态控制窗口
        URL basicInfoAddSceneUrl = getClass().getResource("../scene/addBasicInfoScene.fxml");
        FXMLLoader basicInfoAddLoader = new FXMLLoader();
        basicInfoAddLoader.setLocation(basicInfoAddSceneUrl);

        // 加载图标资源
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        // 设置场景以及窗口的信息
        Parent root = basicInfoAddLoader.load();
        Scene addBasicScene = new Scene(root, 500, 420);
        stage.setScene(addBasicScene);
        stage.setTitle("添加预处理的预制梁");
        stage.setResizable(false);

        // addBasicInfoController : AddBasicInfoController 类的实例，窗口的控制器
        AddBasicInfoController addBasicInfoController = basicInfoAddLoader.getController();
        addBasicInfoController.init(preBeamId);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(getBasicInfoAddStage());
    }

    public void initializePreBeam(String id)
    {
        preBeamId = id;
    }
}
