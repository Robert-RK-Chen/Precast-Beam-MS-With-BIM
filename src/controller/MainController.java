package controller;

import application.AddBasicInfoStage;
import application.BeamInfoStage;
import hibernate.abstractModel.HibernateUtil;
import hibernate.entities.BeamInfoEntity;
import hibernate.model.BeamInfoModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Robert Chen
 */
public class MainController
{
    public Button preBeam1;
    public Button preBeam2;
    public Button preBeam3;
    public Button preBeam4;
    public Button preBeam5;
    public Button preBeam6;
    public Button preBeam7;
    public Button preBeam8;
    public Button preBeam9;
    public Button preBeam10;
    public Button readDatabaseButton;
    public TextField beamSearchTf;
    public Button queryButton;

    public void initialize()
    {
        Thread thread = new Thread(HibernateUtil::getSession);
        thread.start();
    }

    public void getPreBeam1() throws Exception { getInfo(preBeam1); }
    public void getPreBeam2() throws Exception { getInfo(preBeam2); }
    public void getPreBeam3() throws Exception { getInfo(preBeam3); }
    public void getPreBeam4() throws Exception { getInfo(preBeam4); }
    public void getPreBeam5() throws Exception { getInfo(preBeam5); }
    public void getPreBeam6() throws Exception { getInfo(preBeam6); }
    public void getPreBeam7() throws Exception { getInfo(preBeam7); }
    public void getPreBeam8() throws Exception { getInfo(preBeam8); }
    public void getPreBeam9() throws Exception { getInfo(preBeam9); }
    public void getPreBeam10() throws Exception { getInfo(preBeam10); }

    public void getInfo(Button button) throws Exception
    {
        String beamId = button.getText();
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamId);
        if (beamInfoEntity == null)
        {
            AddBasicInfoStage addBasicInfoStage = new AddBasicInfoStage();
            addBasicInfoStage.getPreBeam(button.getText());
            addBasicInfoStage.showStage();
        }
        else
        {
            BeamInfoStage beamInfoStage = new BeamInfoStage();
            beamInfoStage.getPreBeam(button.getText());
            beamInfoStage.showStage();
        }
    }

    public void readDatabase()
    {
        Alert beamIsNullAlert = new Alert(Alert.AlertType.INFORMATION);
        beamIsNullAlert.setTitle("来自 加载数据库 的消息");
        beamIsNullAlert.setHeaderText("数据库加载完成！");
        beamIsNullAlert.show();
    }

    public void queryBeam() throws Exception
    {
        String beamId = beamSearchTf.getText();
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        if("".equals(beamId))
        {
            beamSearchTf.setPromptText("🔍 请输入查询关键字！");
        }
        else if (beamInfoModel.findById(beamId) == null)
        {
            Alert beamIsNull = new Alert(Alert.AlertType.INFORMATION);
            beamIsNull.setTitle("来自 搜索预制梁 的消息");
            beamIsNull.setHeaderText("数据库中暂无此预制梁的信息");
            beamIsNull.show();
        }
        else
        {
            BeamInfoStage beamInfoStage = new BeamInfoStage();
            beamInfoStage.getPreBeam(beamId);
            beamInfoStage.showStage();
        }
    }
}
