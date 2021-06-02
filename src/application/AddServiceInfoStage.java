package application;

import controller.AddServiceInfoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class AddServiceInfoStage extends Application
{
    private TextField beam;
    private static Stage serviceInfoStage = null;

    /**
     * @return Stage serviceInfoStage
     * 使用单例模式保证只会弹出一个界面
     */
    public static Stage addServiceInfo()
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
        URL addServiceUrl = getClass().getResource("../scene/addServiceInfoScene.fxml");
        FXMLLoader addServiceLoader = new FXMLLoader();
        addServiceLoader.setLocation(addServiceUrl);
        Parent root = addServiceLoader.load();
        Scene addServiceScene = new Scene(root, 500, 340);
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());

        stage.getIcons().add(icon);
        stage.setTitle("添加预制梁业务步骤");
        stage.setScene(addServiceScene);
        stage.setResizable(false);

        AddServiceInfoController addServiceController = addServiceLoader.getController();
        addServiceController.initialize(beam);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(addServiceInfo());
    }

    public void getBeam(TextField textField)
    {
        beam = textField;
    }
}
