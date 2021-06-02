package controller;

import application.AddServiceInfoStage;
import hibernate.entities.*;
import hibernate.model.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

/**
 * @author Robert Chen
 */
public class BeamInfoController
{
    public ImageView beamImageView;
    public TextField beamIdTf;
    public TextField beamKindTf;
    public TextField steelType1Tf;
    public TextField steelType2Tf;
    public TextField steelType3Tf;
    public TextField lengthTf;
    public TextField widthTf;
    public TextField heightTf;
    public TextField radiusTf;
    public Button nextStepButton;
    public Label stateLabel;
    public TextField wireInspectorTf;
    public TextField wireStartTf;
    public TextField wireFinishTf;
    public TextField pouringInspectorTf;
    public TextField pouringStartTf;
    public TextField pouringFinishTf;
    public TextField curingStartTf;
    public TextField curingInspectorTf;
    public TextField curingFinishTf;
    public TextField storeInspectorTf;
    public TextField storeStartTf;
    public TextField shipmentExpectTf;
    public TextField shipmentActualTf;

    public void initialize(Button button)
    {
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(button.getText());
        TieInfoModel tieInfoModel = new TieInfoModel();
        TieInfoEntity tieInfoEntity = tieInfoModel.findById(button.getText());
        PouringInfoModel pouringInfoModel = new PouringInfoModel();
        PouringInfoEntity pouringInfoEntity = pouringInfoModel.findById(button.getText());
        CuringInfoModel curingInfoModel = new CuringInfoModel();
        CuringInfoEntity curingInfoEntity = curingInfoModel.findById(button.getText());
        BeamStoreModel beamStoreModel = new BeamStoreModel();
        BeamStoreEntity beamStoreEntity = beamStoreModel.findById(button.getText());

        URL imageUrl = getClass().getResource("../resource/image/preview/" + beamInfoEntity.getBeamKind() + ".jpg");
        beamImageView.setImage(new Image(imageUrl.toExternalForm()));

        beamIdTf.setText(beamInfoEntity.getBeamId());
        beamKindTf.setText(beamInfoEntity.getBeamKind());
        steelType1Tf.setText(beamInfoEntity.getSteelType1());
        steelType2Tf.setText(beamInfoEntity.getSteelType2());
        steelType3Tf.setText(beamInfoEntity.getSteelType3());
        lengthTf.setText(beamInfoEntity.getLength().toString());
        widthTf.setText(beamInfoEntity.getWidth().toString());
        heightTf.setText(beamInfoEntity.getHeight().toString());
        radiusTf.setText(beamInfoEntity.getRadius().toString());
        stateLabel.setText(beamInfoEntity.getBeamState());

        if (tieInfoEntity != null)
        {
            wireInspectorTf.setText(tieInfoEntity.getWireInspector());
            wireStartTf.setText(tieInfoEntity.getWireStart().toString());
            wireFinishTf.setText(tieInfoEntity.getWireFinish().toString());
        }

        if (pouringInfoEntity != null)
        {
            pouringInspectorTf.setText(pouringInfoEntity.getPouringInspector());
            pouringStartTf.setText(pouringInfoEntity.getPouringStart().toString());
            pouringFinishTf.setText(pouringInfoEntity.getPouringFinish().toString());
        }

        if (curingInfoEntity != null)
        {
            curingInspectorTf.setText(curingInfoEntity.getCuringInspector());
            curingStartTf.setText(curingInfoEntity.getCuringStart().toString());
            curingFinishTf.setText(curingInfoEntity.getCuringFinish().toString());
        }

        if (beamStoreEntity != null)
        {
            storeInspectorTf.setText(beamStoreEntity.getStoreInspector());
            storeStartTf.setText(beamStoreEntity.getStoreStart().toString());
            shipmentExpectTf.setText(beamStoreEntity.getShipmentExpect().toString());
            shipmentActualTf.setText(beamStoreEntity.getShipmentActual().toString());
        }
    }

    public void nextStep() throws Exception
    {
        AddServiceInfoStage serviceInfoStage = new AddServiceInfoStage();
        serviceInfoStage.getBeam(beamIdTf);
        serviceInfoStage.showStage();
    }
}
