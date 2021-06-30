package controller;

import com.alibaba.fastjson.JSONObject;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Robert Chen
 */
public class SettingController {
    public Label userIdLabel;
    public Hyperlink exitLoginHyperlink;
    public Label developerLabel;
    public Hyperlink checkUpdateHyperlink;
    public Label versionLabel;

    public void exitLogin() {
        System.exit(0);
    }

    public static JSONObject execCurl(String[] curl) {
        ProcessBuilder process = new ProcessBuilder(curl);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return JSONObject.parseObject(builder.toString());
        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return null;
    }

    public void checkUpdate() {
        boolean newVersionFlag = false;
        // 生成一串 SSH密钥，替换掉第三个参数
        String[] curl =
                {"curl", "-u", "//your github ssh", "https://api.github.com/repos/Robert-RK-Chen/Precast-Beam-MS-With" + "-BIM/releases/latest"};
        try {
            JSONObject versionInfo = execCurl(curl);
            String latestVer = versionInfo.getString("name");
            String[] latestVersion = latestVer.split("-")[0].split("\\.");
            String[] currentVersion = versionLabel.getText().split("-")[0].split("\\.");
            for (int i = 0 ; i < 3 ; i++) {
                if (Integer.parseInt(latestVersion[i]) > Integer.parseInt(currentVersion[i])) {
                    newVersionFlag = true;
                    break;
                }
            }
            if (newVersionFlag) {
                Alert newVersion = new Alert(Alert.AlertType.INFORMATION);
                newVersion.setTitle("检查更新");
                newVersion.setHeaderText("检测到版本更新：" + versionInfo.getString("body") + "\n\n点击确认前往下载最新版本软件！");
                newVersion.show();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://github.com/Robert-RK-Chen/Precast-Beam-MS-With-BIM/releases");
            } else {
                Alert noUpdate = new Alert(Alert.AlertType.INFORMATION);
                noUpdate.setTitle("检查更新");
                noUpdate.setHeaderText("您使用的软件是最新版本的！");
                noUpdate.show();
            }
        } catch (Exception exception) {
            Alert noUpdate = new Alert(Alert.AlertType.INFORMATION);
            noUpdate.setTitle("检查更新错误");
            noUpdate.setHeaderText("检查更新失败，请检查您的网络！");
            noUpdate.show();
        }
    }

    public void getEasternEgg(MouseEvent mouseEvent) {
        int clickedCount = 7;
        if (mouseEvent.getClickCount() >= clickedCount) {
            Alert easternEgg = new Alert(Alert.AlertType.INFORMATION);
            easternEgg.setTitle("恭喜你发现了隐藏彩蛋！！");
            easternEgg.setHeaderText("如你所见，这个软件是由这些开发者编写的！\n用时1个月增加了大大小小的功能！\n为他们鼓掌喝彩！！");
            easternEgg.setContentText("感谢赵纲正同学提供的 MD5 校验代码！");
            easternEgg.show();
        }
    }
}
