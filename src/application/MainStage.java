package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Robert Chen
 */
public class MainStage extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../scene/MainScene.fxml"));
        primaryStage.setTitle("预制梁场生产管理系统");
        primaryStage.setScene(new Scene(root, 1200,800));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
