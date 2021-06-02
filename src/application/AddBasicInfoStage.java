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
public class AddBasicInfoStage extends Application
{
    /**
     * 单例模式
     */
    private static Stage basicInfoStage = null;

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

        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("添加预处理的预制梁");
        Scene addBasicScene = new Scene(root, 500, 420);
        stage.setScene(addBasicScene);
        stage.setResizable(false);
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
}
