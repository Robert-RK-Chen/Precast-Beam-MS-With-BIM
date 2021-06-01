package controller;

import application.BeamInfoStage;
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

    public void getPreBeam() throws Exception
    {
        getInfo(preBeam1);
    }

    public void getInfo(Button button) throws Exception
    {
        String beamId = button.getText();
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamId);
        if (beamInfoEntity == null)
        {
            application.BeamInfoStage beamInfoStage = new BeamInfoStage();
            beamInfoStage.showStage();
        }
        else
        {
            // TODO Something
        }
    }

}
