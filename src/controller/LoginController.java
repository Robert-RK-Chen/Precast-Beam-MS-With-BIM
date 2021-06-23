package controller;

import application.MainStage;
import hibernate.abstractModel.HibernateUtil;
import hibernate.entities.UsersEntity;
import hibernate.model.UsersModel;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.HashUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LoginController
{
    public static UsersEntity thisUser;
    public TextField userNameTf;
    public Hyperlink forgetHyperLink;
    public PasswordField passwordTf;
    public Button loginButton;
    public Button fastLoginBtn;

    // 初始化方法，多线程加载 Hibernate 连接以免影响软件启动速度
    public void loadHibernate()
    {
        Thread thread1 = new Thread(() -> Platform.runLater(HibernateUtil::getSession));
        thread1.start();
    }

    public void login() throws Exception
    {
        UsersEntity user = new UsersModel().findById(userNameTf.getText());
        if (user == null || !user.getPassword().equals(passwordTf.getText()))
        {
            Alert loginFailed = new Alert(Alert.AlertType.INFORMATION);
            loginFailed.setTitle("登陆失败！");
            loginFailed.setHeaderText("请检查用户名或密码是否正确。");
            loginFailed.show();
        }
        else
        {
            user.setMd5Check(new HashUtil().encryptionHash(user.getUsername() + user.getPassword()));
            new UsersModel().update(user);
            new MainStage().showStage();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
    }

    // 用于判断用户是否已经登陆且存在登陆凭据，有的话就直接进入软件页面
    public void fastLogin() throws Exception
    {
        boolean fastLoginFlag = false;
        String authorityFilePath = System.getProperties().getProperty("user.home") + "\\AppData\\Local\\PreBeam " +
                "System\\login.authority";
        List<UsersEntity> users = new UsersModel().findAll();
        if (new File(authorityFilePath).exists())
        {
            Path filePath = Paths.get(authorityFilePath);
            String authority = Files.readString(filePath);
            for (UsersEntity user : users)
                if (user.getMd5Check().equals(authority))
                {
                    fastLoginFlag = true;
                    break;
                }
        }

        if (fastLoginFlag)
        {
            new MainStage().showStage();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert loginFailed = new Alert(Alert.AlertType.INFORMATION);
            loginFailed.setTitle("快捷登录失败！");
            loginFailed.setHeaderText("用户登录凭据被更改，请重新登陆！");
            loginFailed.show();
        }
    }
}

