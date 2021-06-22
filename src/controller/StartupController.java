package controller;

import application.MainStage;
import hibernate.abstractModel.HibernateUtil;
import javafx.application.Platform;

public class StartupController
{
    // 初始化方法，多线程加载 Hibernate 连接以免影响软件启动速度
    public void loadHibernate()
    {
        Thread thread1 = new Thread(() -> Platform.runLater(HibernateUtil::getSession));
        Thread thread2 = new Thread(() -> Platform.runLater(() -> new MainStage().showStage()));
        thread1.start();
        thread2.start();
    }
}
