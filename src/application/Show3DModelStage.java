package application;

import controller.ThreeDModelController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void start(Stage stage) throws IOException
    {
        ThreeDModelController modelController = new ThreeDModelController();
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("桥梁 3D模型 展示界面");
        stage.setScene(modelController.initialize());
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws IOException
    {
        start(getShowModelStage());
    }
}
