package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Properties;

public class SettingController
{
    public Label userIdLabel;
    public Hyperlink exitLoginHyperlink;
    public Label developerLabel;
    public Hyperlink checkUpdateHyperlink;
    public Label versionLabel;

    public void init()
    {
    }

    public void exitLogin()
    {

    }

    public void checkUpdate()
    {
    }

    public void getEasternEgg(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount() == 7)
        {
            Alert easternEgg = new Alert(Alert.AlertType.INFORMATION);
            easternEgg.setTitle("恭喜你发现了隐藏彩蛋！！");
            easternEgg.setHeaderText("如你所见，这个软件是由这些开发者编写的！\n用时1个月增加了大大小小的功能！\n为他们鼓掌！！");
            easternEgg.setContentText("鸣谢不知名的某位同学提供的 MD5 校验代码！");
        }
    }
}
