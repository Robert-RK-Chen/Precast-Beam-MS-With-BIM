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
    public Button tieBeam1;
    public Button tieBeam2;
    public Button tieBeam3;
    public Button tieBeam4;
    public Button tieBeam5;
    public Button tieBeam6;
    public Button tieBeam7;
    public Button tieBeam8;
    public Button tieBeam9;
    public Button tieBeam10;
    public Button pourBeam1;
    public Button pourBeam2;
    public Button pourBeam3;
    public Button pourBeam4;
    public Button pourBeam5;
    public Button pourBeam6;
    public Button pourBeam7;
    public Button pourBeam8;
    public Button pourBeam9;
    public Button pourBeam10;
    public Button cureBeam1;
    public Button cureBeam2;
    public Button cureBeam3;
    public Button cureBeam4;
    public Button cureBeam5;
    public Button cureBeam6;
    public Button cureBeam7;
    public Button cureBeam8;
    public Button cureBeam9;
    public Button cureBeam10;
    public Button storeBeam1;
    public Button storeBeam2;
    public Button storeBeam3;
    public Button storeBeam4;
    public Button storeBeam5;
    public Button storeBeam6;
    public Button storeBeam7;
    public Button storeBeam8;
    public Button storeBeam9;
    public Button storeBeam10;
    public Button readDatabaseButton;
    public TextField beamSearchTf;
    public Button queryButton;


    public void initialize()
    {
        Thread loadDataThread = new Thread(HibernateUtil::getSession);
        loadDataThread.start();
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
    public void getTieBeam1() throws Exception { getInfo(tieBeam1); }
    public void getTieBeam2() throws Exception { getInfo(tieBeam2); }
    public void getTieBeam3() throws Exception { getInfo(tieBeam3); }
    public void getTieBeam4() throws Exception { getInfo(tieBeam4); }
    public void getTieBeam5() throws Exception { getInfo(tieBeam5); }
    public void getTieBeam6() throws Exception { getInfo(tieBeam6); }
    public void getTieBeam7() throws Exception { getInfo(tieBeam7); }
    public void getTieBeam8() throws Exception { getInfo(tieBeam8); }
    public void getTieBeam9() throws Exception { getInfo(tieBeam9); }
    public void getTieBeam10() throws Exception { getInfo(pourBeam10); }
    public void getPourBeam1() throws Exception { getInfo(pourBeam1); }
    public void getPourBeam2() throws Exception { getInfo(pourBeam2); }
    public void getPourBeam3() throws Exception { getInfo(pourBeam3); }
    public void getPourBeam4() throws Exception { getInfo(pourBeam4); }
    public void getPourBeam5() throws Exception { getInfo(pourBeam5); }
    public void getPourBeam6() throws Exception { getInfo(pourBeam6); }
    public void getPourBeam7() throws Exception { getInfo(pourBeam7); }
    public void getPourBeam8() throws Exception { getInfo(pourBeam8); }
    public void getPourBeam9() throws Exception { getInfo(pourBeam9); }
    public void getPourBeam10() throws Exception { getInfo(pourBeam10); }
    public void getCureBeam1() throws Exception { getInfo(cureBeam1); }
    public void getCureBeam2() throws Exception { getInfo(cureBeam2); }
    public void getCureBeam3() throws Exception { getInfo(cureBeam3); }
    public void getCureBeam4() throws Exception { getInfo(cureBeam4); }
    public void getCureBeam5() throws Exception { getInfo(cureBeam5); }
    public void getCureBeam6() throws Exception { getInfo(cureBeam6); }
    public void getCureBeam7() throws Exception { getInfo(cureBeam7); }
    public void getCureBeam8() throws Exception { getInfo(cureBeam8); }
    public void getCureBeam9() throws Exception { getInfo(cureBeam9); }
    public void getCureBeam10() throws Exception { getInfo(cureBeam10); }
    public void getStoreBeam1() throws Exception { getInfo(storeBeam1); }
    public void getStoreBeam2() throws Exception { getInfo(storeBeam2); }
    public void getStoreBeam3() throws Exception { getInfo(storeBeam3); }
    public void getStoreBeam4() throws Exception { getInfo(storeBeam4); }
    public void getStoreBeam5() throws Exception { getInfo(storeBeam5); }
    public void getStoreBeam6() throws Exception { getInfo(storeBeam6); }
    public void getStoreBeam7() throws Exception { getInfo(storeBeam7); }
    public void getStoreBeam8() throws Exception { getInfo(storeBeam8); }
    public void getStoreBeam9() throws Exception { getInfo(storeBeam9); }
    public void getStoreBeam10() throws Exception { getInfo(storeBeam10); }


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
        if ("".equals(beamId))
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
