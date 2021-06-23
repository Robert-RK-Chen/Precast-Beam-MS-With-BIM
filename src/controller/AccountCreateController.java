package controller;

import hibernate.entities.UsersEntity;
import hibernate.model.UsersModel;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountCreateController
{
    public TextField accountIDTf;
    public PasswordField password1Tf;
    public PasswordField password2Tf;
    public TextField authorizeTf;
    public Button accountCreateBtn;

    public void createAnAccount()
    {
        String accountName = accountIDTf.getText();
        String password = password1Tf.getText();
        String passwordCheck = password2Tf.getText();
        String authorize = authorizeTf.getText();
        if (accountName.isEmpty() || password.isEmpty() || passwordCheck.isEmpty() || !password.equals(passwordCheck))
        {
            Alert createAccountFailed = new Alert(Alert.AlertType.INFORMATION);
            createAccountFailed.setTitle("来自创建账户的提醒！");
            createAccountFailed.setHeaderText("账户名或密码为空，或两次设置密码不同，\n请返回检查！");
            createAccountFailed.show();
        }
        else
        {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUsername(accountName);
            usersEntity.setPassword(password);
            usersEntity.setMd5Check("");
            new UsersModel().insert(usersEntity);

            Alert createAccountSuccess = new Alert(Alert.AlertType.INFORMATION);
            createAccountSuccess.setTitle("创建账户成功！");
            createAccountSuccess.setHeaderText("请牢记您的密码，\n现在可以使用梁场通了！");
            createAccountSuccess.show();

            Thread thread = new Thread(() -> Platform.runLater(() ->
            {
                Stage stage = (Stage) accountCreateBtn.getScene().getWindow();
                stage.close();
            }));
            thread.start();
        }
    }
}
