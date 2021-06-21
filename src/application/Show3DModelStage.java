package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author MING
 * @author Liu BaoYang
 * @author Wang YiYan
 * @author Robert Chen
 */
public class Show3DModelStage extends Application
{
    private static Stage showModelStage = null;

    public static Stage getShowModelStage()
    {
        if (showModelStage == null)
        {
            showModelStage = new Stage();
        }
        return showModelStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        URL mainUrl = getClass().getResource("../scene/Show3DModelScene.fxml");
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(mainUrl);

        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        primaryStage.getIcons().add(icon);

        Parent root = mainLoader.load();
        Scene mainScene = new Scene(root, 1000, 700);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("桥梁 3D 模型查看");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(getShowModelStage());
    }
}
