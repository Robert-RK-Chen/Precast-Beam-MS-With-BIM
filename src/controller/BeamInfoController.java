package controller;

import hibernate.entities.BeamInfoEntity;
import hibernate.model.BeamInfoModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @author Robert Chen
 */
public class BeamInfoController
{
    @FXML
    private ImageView beamImageView;
    @FXML
    private TextField beamIdTf;
    @FXML
    private TextField beamKindTf;
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

    public void initialize(Button button)
    {
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(button.getText());

        beamIdTf.setText(beamInfoEntity.getBeamId());
        beamKindTf.setText(beamInfoEntity.getBeamKind());
        steelType1Tf.setText(beamInfoEntity.getSteelType1());
        steelType2Tf.setText(beamInfoEntity.getSteelType2());
        steelType3Tf.setText(beamInfoEntity.getSteelType3());
        lengthTf.setText(beamInfoEntity.getLength().toString());
        widthTf.setText(beamInfoEntity.getWidth().toString());
        heightTf.setText(beamInfoEntity.getHeight().toString());
        radiusTf.setText(beamInfoEntity.getRadius().toString());
    }
}
