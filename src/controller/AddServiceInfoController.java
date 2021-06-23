package controller;

import application.BeamInfoStage;
import hibernate.entities.*;
import hibernate.model.*;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author Robert Chen
 */
public class AddServiceInfoController
{
    // 来自 FXML 绑定的的控件
    public Button saveInfoButton;
    public TextField beamIdTf;
    public TextField inspectorTf;
    public DatePicker startTimeDp;
    public DatePicker finishTimeDp;
    public DatePicker shipmentActualDp;
    public Label finishTimeLabel;

    // 控制器初始化方法，如果预制梁的状态是养护，则在增加下一步业务时，实际运出时间的日期选择器可用
    public void init(String beamId)
    {
        beamIdTf.setText(beamId);
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());

        String state = "养护";
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

    // 使预制梁进入下一个步骤，增加该业务信息
    public void saveInfo()
    {
        // 获取 beamID 对应的预制梁实体
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());
        String beamState = beamInfoEntity.getBeamState();
        String inspector = inspectorTf.getText();
        LocalDate startTime = startTimeDp.getValue();
        LocalDate finishTime = finishTimeDp.getValue();
        LocalDate shipmentActualTime = shipmentActualDp.getValue();
        Boolean valueLegal = Boolean.TRUE;

        if (inspector.equals(""))
        {
            valueLegal = Boolean.FALSE;
        }
        if (startTime == null || finishTime == null)
        {
            valueLegal = Boolean.FALSE;
        }
        if (beamState.equals("养护") && shipmentActualTime == null)
        {
            valueLegal = Boolean.FALSE;
        }

        // 根据不同的预制梁状态执行不同的业务操作
        if (valueLegal == Boolean.FALSE)
        {
            Alert addFailed = new Alert(Alert.AlertType.INFORMATION);
            addFailed.setTitle("来自 添加预制梁业务 的消息");
            addFailed.setHeaderText("""
                    业务信息添加失败，请检查：
                    1. 质检员是否未录入；
                    2. 是否有时间为空。""");
            addFailed.show();
        }
        else
        {
            assert startTime != null;
            assert finishTime != null;
            assert shipmentActualTime != null;
            switch (beamState)
            {
                case "预处理" -> addTieInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime);
                case "扎钢筋" -> addPouringInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime);
                case "浇筑" -> addCuringInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime);
                case "养护" -> addStoreInfo(beamInfoModel, beamInfoEntity, inspector, startTime, finishTime,
                        shipmentActualTime);
                default -> {
                }
            }

            // 成功增加预制梁的业务信息后，提醒用户
            Alert addSuccess = new Alert(Alert.AlertType.INFORMATION);
            addSuccess.setTitle("来自 添加预制梁业务 的消息");
            addSuccess.setHeaderText("业务信息添加成功！");
            addSuccess.show();

            // 关闭业务信息添加面板
            Thread closeStage = new Thread(() -> Platform.runLater(() ->
            {
                new BeamInfoStage().closeStage();
                Stage stage = (Stage) saveInfoButton.getScene().getWindow();
                stage.close();
            }));
            closeStage.start();
        }
    }

    // 增加【扎钢筋】业务信息
    public void addTieInfo(BeamInfoModel beamInfoModel, BeamInfoEntity beamInfoEntity, String inspector,
                           LocalDate startTime, LocalDate finishTime)
    {
        TieInfoModel tieInfoModel = new TieInfoModel();
        TieInfoEntity tieInfoEntity = new TieInfoEntity();
        tieInfoEntity.setBeamId(beamIdTf.getText());
        tieInfoEntity.setWireInspector(inspector);
        tieInfoEntity.setWireStart(Timestamp.valueOf(startTime.atStartOfDay()));
        tieInfoEntity.setWireFinish(Timestamp.valueOf(finishTime.atStartOfDay()));
        tieInfoModel.update(tieInfoEntity);

        // 更新预制梁状态
        beamInfoEntity.setBeamState("扎钢筋");
        beamInfoModel.update(beamInfoEntity);
    }

    // 增加【浇筑】业务信息
    public void addPouringInfo(BeamInfoModel beamInfoModel, BeamInfoEntity beamInfoEntity, String inspector,
                               LocalDate startTime, LocalDate finishTime)
    {
        PouringInfoModel pouringInfoModel = new PouringInfoModel();
        PouringInfoEntity pouringInfoEntity = new PouringInfoEntity();
        pouringInfoEntity.setBeamId(beamIdTf.getText());
        pouringInfoEntity.setPouringInspector(inspector);
        pouringInfoEntity.setPouringStart(Timestamp.valueOf(startTime.atStartOfDay()));
        pouringInfoEntity.setPouringFinish(Timestamp.valueOf(finishTime.atStartOfDay()));
        pouringInfoModel.update(pouringInfoEntity);
        beamInfoEntity.setBeamState("浇筑");
        beamInfoModel.update(beamInfoEntity);
    }

    // 增加【养护】业务信息
    public void addCuringInfo(BeamInfoModel beamInfoModel, BeamInfoEntity beamInfoEntity, String inspector,
                              LocalDate startTime, LocalDate finishTime)
    {
        CuringInfoModel curingInfoModel = new CuringInfoModel();
        CuringInfoEntity curingInfoEntity = new CuringInfoEntity();
        curingInfoEntity.setBeamId(beamIdTf.getText());
        curingInfoEntity.setCuringInspector(inspector);
        curingInfoEntity.setCuringStart(Timestamp.valueOf(startTime.atStartOfDay()));
        curingInfoEntity.setCuringFinish(Timestamp.valueOf(finishTime.atStartOfDay()));
        curingInfoModel.update(curingInfoEntity);
        beamInfoEntity.setBeamState("养护");
        beamInfoModel.update(beamInfoEntity);
    }

    // 增加存储业务信息
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
        beamStoreModel.update(beamStoreEntity);
        beamInfoEntity.setBeamState("存储");
        beamInfoModel.update(beamInfoEntity);
    }
}
