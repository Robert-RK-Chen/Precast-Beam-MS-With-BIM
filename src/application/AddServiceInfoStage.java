package application;

import controller.AddServiceInfoController;
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
public class AddServiceInfoStage extends Application
{
    // beam : 作为主界面与添加预制梁信息界面的中间信息
    // serviceInfoStage : 添加预制梁业务信息界面
    private String beamId;
    private static Stage serviceInfoStage = null;

    /**
     * @return Stage serviceInfoStage
     * 使用单例模式保证只会弹出一个界面
     */
    public static Stage getServiceInfoStage()
    {
        if (serviceInfoStage == null)
        {
            serviceInfoStage = new Stage();
        }
        return serviceInfoStage;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        // addServiceUrl : 添加业务信息界面的 FXML 资源
        // addServiceLoader : 加载器，用于动态控制窗口
        URL addServiceUrl = getClass().getResource("../scene/addServiceInfoScene.fxml");
        FXMLLoader addServiceLoader = new FXMLLoader();
        addServiceLoader.setLocation(addServiceUrl);

        // 加载图标资源
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        // 设置场景以及窗口的信息
        Parent root = addServiceLoader.load();
        Scene addServiceScene = new Scene(root, 500, 340);
        stage.setTitle("添加预制梁业务步骤");
        stage.setScene(addServiceScene);
        stage.setResizable(false);

        // addServiceController : AddServiceInfoController 类的实例，窗口的控制器
        AddServiceInfoController addServiceController = addServiceLoader.getController();
        addServiceController.init(beamId);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(getServiceInfoStage());
    }

    public void initializePreBeam(String id)
    {
        beamId = id;
    }
}
