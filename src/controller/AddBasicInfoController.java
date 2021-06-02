package controller;

import hibernate.entities.BeamInfoEntity;
import hibernate.model.BeamInfoModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Robert Chen
 */
public class AddBasicInfoController
{
    @FXML
    private TextField beamIdTf;
    @FXML
    private ComboBox<String> beamKindCb;
    @FXML
    private TextField steelType1Tf;
    @FXML
    private TextField steelType2Tf;
    @FXML
    private TextField steelType3Tf;
    @FXML
    private TextField lengthTf;
    @FXML
    private TextField widthTf;
    @FXML
    private TextField heightTf;
    @FXML
    private TextField radiusTf;
    @FXML
    private Button addBeamButton;

    public void addBeam()
    {
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = new BeamInfoEntity();

        String beamId = beamIdTf.getText();
        String beamKind = beamKindCb.getValue();
        String steelType1 = steelType1Tf.getText();
        String steelType2 = steelType2Tf.getText();
        String steelType3 = steelType3Tf.getText();

        /* error here !
         * 当有值为空置时，String 转 Double 报错，虽然赋值 0
         * 尚不清楚原因
         * */
        
//        double length = Double.parseDouble(lengthTf.getText() == null ? "0" : lengthTf.getText());
//        double width = Double.parseDouble(widthTf.getText() == null ? "0" : widthTf.getText());
//        double height = Double.parseDouble(heightTf.getText() == null ? "0" : heightTf.getText());
//        double radius = Double.parseDouble(radiusTf.getText() == null ? "0" : radiusTf.getText());

        double length = 12;
        double width = 12;
        double height = 12;
        double radius = 12;

        beamInfoEntity.setBeamId(beamId);
        beamInfoEntity.setBeamKind(beamKind);
        beamInfoEntity.setSteelType1(steelType1);
        beamInfoEntity.setSteelType2(steelType2);
        beamInfoEntity.setSteelType3(steelType3);
        beamInfoEntity.setLength(length);
        beamInfoEntity.setWidth(width);
        beamInfoEntity.setHeight(height);
        beamInfoEntity.setRadius(radius);
        beamInfoEntity.setBeamState("预处理");
        beamInfoModel.insert(beamInfoEntity);

        Alert addSuccess = new Alert(Alert.AlertType.CONFIRMATION);
        addSuccess.setTitle("来自 添加预处理的预制梁 的消息");
        addSuccess.setHeaderText("预制梁添加成功！");
        addSuccess.showAndWait();

        Stage stage = (Stage) addBeamButton.getScene().getWindow();
        stage.close();
    }

    public void getBeamKind()
    {
        String beamKind = beamKindCb.getValue();
        if ("正方体".equals(beamKind))
        {
            widthTf.setDisable(false);
            lengthTf.setDisable(false);
            radiusTf.setText("");
            radiusTf.setDisable(true);
        }
        else
        {
            radiusTf.setDisable(false);
            widthTf.setText("");
            widthTf.setDisable(true);
            lengthTf.setText("");
            lengthTf.setDisable(true);
        }
    }
}
