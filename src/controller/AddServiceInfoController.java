package controller;

import hibernate.entities.*;
import hibernate.model.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author Robert Chen
 */
public class AddServiceInfoController
{
    public Button saveInfoButton;
    public TextField beamIdTf;
    public TextField inspectorTf;
    public DatePicker startTimeDp;
    public DatePicker finishTimeDp;
    public DatePicker shipmentActualDp;
    public Label finishTimeLabel;

    public void initialize(TextField textField)
    {
        String state = "养护";
        beamIdTf.setText(textField.getText());
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());
        if (state.equals(beamInfoEntity.getBeamState()))
        {
            finishTimeLabel.setText("预期运出时间");
            shipmentActualDp.setDisable(false);
        }
        else
        {
            finishTimeLabel.setText("结束时间");
            shipmentActualDp.setDisable(true);
        }
    }

    public void saveInfo() throws Exception
    {
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());
        String beamState = beamInfoEntity.getBeamState();
        String inspector = inspectorTf.getText();
        LocalDate startTime = startTimeDp.getValue();
        LocalDate finishTime = finishTimeDp.getValue();
        LocalDate shipmentActualTime = shipmentActualDp.getValue();

        switch (beamState)
        {
            case "预处理" -> addTieInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime);
            case "扎钢筋" -> addPouringInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime);
            case "浇筑" -> addCuringInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime);
            case "养护" -> addStoreInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime,
                    shipmentActualTime);
            default -> {}
        }

        Alert addSuccess = new Alert(Alert.AlertType.INFORMATION);
        addSuccess.setTitle("来自 添加预制梁业务 的消息");
        addSuccess.setHeaderText("业务信息添加成功！");
        addSuccess.show();

        Stage stage = (Stage) saveInfoButton.getScene().getWindow();
        stage.close();
    }

    public void addTieInfo(BeamInfoModel beamInfoModel, BeamInfoEntity beamInfoEntity, String inspector,
                           LocalDate startTime, LocalDate finishTime)
    {
        TieInfoModel tieInfoModel = new TieInfoModel();
        TieInfoEntity tieInfoEntity = new TieInfoEntity();
        tieInfoEntity.setBeamId(beamIdTf.getText());
        tieInfoEntity.setWireInspector(inspector);
        tieInfoEntity.setWireStart(Timestamp.valueOf(startTime.atStartOfDay()));
        tieInfoEntity.setWireFinish(Timestamp.valueOf(finishTime.atStartOfDay()));
        tieInfoModel.insert(tieInfoEntity);
        beamInfoEntity.setBeamState("扎钢筋");
        beamInfoModel.update(beamInfoEntity);
    }

    public void addPouringInfo(BeamInfoModel beamInfoModel, BeamInfoEntity beamInfoEntity, String inspector,
                               LocalDate startTime, LocalDate finishTime)
    {
        PouringInfoModel pouringInfoModel = new PouringInfoModel();
        PouringInfoEntity pouringInfoEntity = new PouringInfoEntity();
        pouringInfoEntity.setBeamId(beamIdTf.getText());
        pouringInfoEntity.setPouringInspector(inspector);
        pouringInfoEntity.setPouringStart(Timestamp.valueOf(startTime.atStartOfDay()));
        pouringInfoEntity.setPouringFinish(Timestamp.valueOf(finishTime.atStartOfDay()));
        pouringInfoModel.insert(pouringInfoEntity);
        beamInfoEntity.setBeamState("浇筑");
        beamInfoModel.update(beamInfoEntity);
    }

    public void addCuringInfo(BeamInfoModel beamInfoModel, BeamInfoEntity beamInfoEntity, String inspector,
                              LocalDate startTime, LocalDate finishTime)
    {
        CuringInfoModel curingInfoModel = new CuringInfoModel();
        CuringInfoEntity curingInfoEntity = new CuringInfoEntity();
        curingInfoEntity.setBeamId(beamIdTf.getText());
        curingInfoEntity.setCuringInspector(inspector);
        curingInfoEntity.setCuringStart(Timestamp.valueOf(startTime.atStartOfDay()));
        curingInfoEntity.setCuringFinish(Timestamp.valueOf(finishTime.atStartOfDay()));
        curingInfoModel.insert(curingInfoEntity);
        beamInfoEntity.setBeamState("养护");
        beamInfoModel.update(beamInfoEntity);
    }

    public void addStoreInfo(BeamInfoModel beamInfoModel, BeamInfoEntity beamInfoEntity, String inspector,
                             LocalDate startTime, LocalDate finishTime, LocalDate shipmentActualTime)
    {
        BeamStoreModel beamStoreModel = new BeamStoreModel();
        BeamStoreEntity beamStoreEntity = new BeamStoreEntity();
        beamStoreEntity.setBeamId(beamIdTf.getText());
        beamStoreEntity.setStoreInspector(inspector);
        beamStoreEntity.setStoreStart(Timestamp.valueOf(startTime.atStartOfDay()));
        beamStoreEntity.setShipmentExpect(Timestamp.valueOf(finishTime.atStartOfDay()));
        beamStoreEntity.setShipmentActual(Timestamp.valueOf(shipmentActualTime.atStartOfDay()));
        beamStoreModel.insert(beamStoreEntity);
        beamInfoEntity.setBeamState("存储");
        beamInfoModel.update(beamInfoEntity);
    }
}
