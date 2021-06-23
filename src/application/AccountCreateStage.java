package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class AccountCreateStage extends Application
{
    private static Stage accountCreateStage = null;

    public static Stage getAccountCreateStage()
    {
        if (accountCreateStage == null)
        {
            accountCreateStage = new Stage();
        }
        return accountCreateStage;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        URL accountCreateUrl = getClass().getResource("../scene/AccountCreateScene.fxml");
        FXMLLoader accountCreateLoader = new FXMLLoader();
        accountCreateLoader.setLocation(accountCreateUrl);

        // 加载图标资源
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        // 设置场景以及窗口的信息
        Parent root = accountCreateLoader.load();
        Scene accountCreateScene = new Scene(root, 500, 700);
        stage.setScene(accountCreateScene);
        stage.setTitle("创建”梁场通“账号");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage() throws Exception
    {
        start(getAccountCreateStage());
    }
}
