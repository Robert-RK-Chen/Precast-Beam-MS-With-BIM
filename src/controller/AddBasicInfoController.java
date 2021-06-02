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

    public void initialize(Button button)
    {
        beamIdTf.setText(button.getText());
        beamIdTf.setDisable(true);
    }

    public void addBeam()
    {
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = new BeamInfoEntity();

        String beamId = beamIdTf.getText();
        String beamKind = beamKindCb.getValue();
        String steelType1 = steelType1Tf.getText();
        String steelType2 = steelType2Tf.getText();
        String steelType3 = steelType3Tf.getText();
        double length = Double.parseDouble("".equals(lengthTf.getText()) ? "0" : lengthTf.getText());
        double width = Double.parseDouble("".equals(widthTf.getText()) ? "0" : widthTf.getText());
        double height = Double.parseDouble("".equals(heightTf.getText()) ? "0" : heightTf.getText());
        double radius = Double.parseDouble("".equals(radiusTf.getText()) ? "0" : radiusTf.getText());

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

        Alert addSuccess = new Alert(Alert.AlertType.INFORMATION);
        addSuccess.setTitle("来自 添加预处理的预制梁 的消息");
        addSuccess.setHeaderText("预制梁添加成功！");
        addSuccess.show();

        Stage stage = (Stage) addBeamButton.getScene().getWindow();
        stage.close();
    }

    public void getBeamKind()
    {
        String beamKind = beamKindCb.getValue();
        String defaultBeamKind = "正方体";

        if (defaultBeamKind.equals(beamKind))
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
