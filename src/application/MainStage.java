package application;

import controller.MainController;
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
public class MainStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // mainUrl : 主界面的 FXML 资源
        // mainLoader : 加载器，用于动态控制窗口
        URL mainUrl = getClass().getResource("../scene/MainScene.fxml");
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(mainUrl);

        // 加载图标资源
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        primaryStage.getIcons().add(icon);

        // 设置场景以及窗口的信息
        Parent root = mainLoader.load();
        Scene mainScene = new Scene(root, 1200, 800);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("预制梁场生产管理系统");
        primaryStage.setResizable(false);

        // mainController : MainController 类的实例，主窗口的控制器
        MainController mainController = mainLoader.getController();
        mainController.initialize();
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
