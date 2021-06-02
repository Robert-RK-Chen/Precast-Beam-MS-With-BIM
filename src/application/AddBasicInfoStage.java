package application;

import controller.AddBasicInfoController;
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
public class AddBasicInfoStage extends Application
{

    /**
     * String preBeamId : 作为主界面与添加预制梁信息界面中间信息传递控件
     * Stage basicInfoStage : 添加预制梁基本信息界面
     */
    private String preBeamId = null;
    private static Stage basicInfoStage = null;

    /**
     * @return Stage basicInfoStage
     * 使用单例模式保证只会弹出一个界面
     */
    public static Stage addBasicInfo()
    {
        if (basicInfoStage == null)
        {
            basicInfoStage = new Stage();
        }
        return basicInfoStage;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        URL addBasicUrl = getClass().getResource("../scene/addBasicInfoScene.fxml");
        FXMLLoader addBasicLoader = new FXMLLoader();
        addBasicLoader.setLocation(addBasicUrl);
        Parent root = addBasicLoader.load();
        Scene addBasicScene = new Scene(root, 500, 420);
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());

        stage.getIcons().add(icon);
        stage.setTitle("添加预处理的预制梁");
        stage.setScene(addBasicScene);
        stage.setResizable(false);

        AddBasicInfoController addBasicController = addBasicLoader.getController();
        addBasicController.initialize(preBeamId);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(addBasicInfo());
    }

    public void getPreBeam(String id) { preBeamId = id; }
}
