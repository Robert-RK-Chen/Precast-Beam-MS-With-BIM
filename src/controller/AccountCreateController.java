package controller;

import hibernate.entities.UsersEntity;
import hibernate.model.UsersModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Robert Chen
 */
public class AccountCreateController {
    public TextField accountIdTf;
    public PasswordField password1Tf;
    public PasswordField password2Tf;
    public TextField authorizeTf;
    public Button accountCreateBtn;
    private final UsersModel usersModel = new UsersModel();

    public void createAnAccount() {
        String accountName = accountIdTf.getText();
        String password = password1Tf.getText();
        String passwordCheck = password2Tf.getText();

        if (accountName.isEmpty() || password.isEmpty() || passwordCheck.isEmpty() || !password.equals(passwordCheck)) {
            Alert createAccountFailed = new Alert(Alert.AlertType.INFORMATION);
            createAccountFailed.setTitle("来自创建账户的提醒！");
            createAccountFailed.setHeaderText("账户名或密码为空，或两次设置密码不同，\n请返回检查！");
            createAccountFailed.show();
        } else {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUsername(accountName);
            usersEntity.setPassword(password);
            usersModel.insert(usersEntity);

            Alert createAccountSuccess = new Alert(Alert.AlertType.INFORMATION);
            createAccountSuccess.setTitle("创建账户成功！");
            createAccountSuccess.setHeaderText("请牢记您的密码，\n现在可以使用梁场通了！");
            createAccountSuccess.show();

            ExecutorService singleThreadPool =
                    new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), new ThreadPoolExecutor.AbortPolicy());
            singleThreadPool.execute(() -> {
                Stage stage = (Stage) accountCreateBtn.getScene().getWindow();
                stage.close();
            });
            singleThreadPool.shutdown();
        }
    }
}
