package controller;

import application.AccountCreateStage;
import application.MainStage;
import hibernate.abstractModel.HibernateUtil;
import hibernate.entities.UsersEntity;
import hibernate.model.UsersModel;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.HashUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Robert Chen
 */
public class LoginController {
    public TextField userNameTf;
    public Hyperlink forgetHyperlink;
    public PasswordField passwordTf;
    public Button loginButton;
    public Button fastLoginBtn;
    public Hyperlink createAccountHyperlink;
    public String authorityFilePath =
            System.getProperties().getProperty("user.home") + "\\AppData\\Local\\PreBeam " + "System\\login.authority";

    public void loadHibernate() {
        ExecutorService singleThreadPool =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(HibernateUtil::getSession);
        singleThreadPool.shutdown();
    }

    public void login() {
        UsersEntity user = new UsersModel().findById(userNameTf.getText());
        if (user == null || !user.getPassword().equals(passwordTf.getText())) {
            Alert loginFailed = new Alert(Alert.AlertType.INFORMATION);
            loginFailed.setTitle("登陆失败！");
            loginFailed.setHeaderText("请检查用户名或密码是否正确。");
            loginFailed.show();
        } else {
            user.setMd5Check(new HashUtil().encryptionHash(user.getUsername() + user.getPassword() + System.currentTimeMillis()));
            new UsersModel().update(user);
            new MainStage().showStage();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
    }

    public void fastLogin() {
        List<UsersEntity> users = new UsersModel().findAll();
        try {
            Path filePath = Paths.get(authorityFilePath);
            String authority = Files.readString(filePath);
            for (UsersEntity user : users) {
                if (user.getMd5Check().equals(authority)) {
                    new MainStage().showStage();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    break;
                }
            }
        } catch (Exception exception) {
            Alert loginFailed = new Alert(Alert.AlertType.INFORMATION);
            loginFailed.setTitle("快捷登录失败！");
            loginFailed.setHeaderText("加载登录凭据失败，请重新登陆！");
            loginFailed.show();
        } finally {
            Alert loginFailed = new Alert(Alert.AlertType.INFORMATION);
            loginFailed.setTitle("快捷登录失败！");
            loginFailed.setHeaderText("快捷登录凭据已过期，请重新登陆！");
            loginFailed.show();
        }
    }

    public void createAccount() { new AccountCreateStage().showStage(); }
}

