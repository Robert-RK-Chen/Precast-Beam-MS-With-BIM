package controller;

import application.AddBasicInfoStage;
import application.BeamInfoStage;
import hibernate.abstractModel.HibernateUtil;
import hibernate.entities.BeamInfoEntity;
import hibernate.model.BeamInfoModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Robert Chen
 */
public class MainController
{
    @FXML
    private Button preBeam1;
    @FXML
    private Button preBeam2;
    @FXML
    private Button preBeam3;
    @FXML
    private Button preBeam4;
    @FXML
    private Button preBeam5;
    @FXML
    private Button preBeam6;
    @FXML
    private Button preBeam7;
    @FXML
    private Button preBeam8;
    @FXML
    private Button preBeam9;
    @FXML
    private Button preBeam10;

    public void initialize()
    {
        HibernateUtil.getSession();
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
        if(beamInfoEntity==null)
        {
            application.AddBasicInfoStage addBasicInfoStage = new AddBasicInfoStage();
            addBasicInfoStage.showStage();
        }
        else
        {
            application.BeamInfoStage beamInfoStage = new BeamInfoStage();
            controller.BeamInfoController beamInfoController = new BeamInfoController();
            beamInfoController.initialize(button);
            beamInfoStage.showStage();
        }
    }
}
