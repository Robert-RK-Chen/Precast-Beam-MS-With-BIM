package controller;

import application.AddServiceInfoStage;
import application.Show3DModelStage;
import hibernate.entities.*;
import hibernate.model.*;
import javafx.scene.control.Alert;
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

    public void initialize(String id)
    {
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(id);
        TieInfoModel tieInfoModel = new TieInfoModel();
        TieInfoEntity tieInfoEntity = tieInfoModel.findById(id);
        PouringInfoModel pouringInfoModel = new PouringInfoModel();
        PouringInfoEntity pouringInfoEntity = pouringInfoModel.findById(id);
        CuringInfoModel curingInfoModel = new CuringInfoModel();
        CuringInfoEntity curingInfoEntity = curingInfoModel.findById(id);
        BeamStoreModel beamStoreModel = new BeamStoreModel();
        BeamStoreEntity beamStoreEntity = beamStoreModel.findById(id);

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
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());

        String beamState = beamInfoEntity.getBeamState();
        switch (beamState)
        {
            case "存储" -> {
                beamInfoEntity.setBeamState("已运出");
                beamInfoModel.update(beamInfoEntity);
                Alert transBeam = new Alert(Alert.AlertType.INFORMATION);
                transBeam.setTitle("来自 添加预制梁业务 的消息");
                transBeam.setHeaderText("预制梁已经运出!");
                transBeam.show();
            }
            case "已运出" -> {
                Alert finishBeam = new Alert(Alert.AlertType.INFORMATION);
                finishBeam.setTitle("来自 添加预制梁业务 的消息");
                finishBeam.setHeaderText("这块预制梁已经运出，\n完成了所有的业务方法！");
                finishBeam.show();
            }
            default -> {
                AddServiceInfoStage serviceInfoStage = new AddServiceInfoStage();
                serviceInfoStage.getBeam(beamIdTf);
                serviceInfoStage.showStage();
            }
        }
    }

    /**
     * 完成 3D 模型展示接口
     */
    public void show3DModel()
    {
        Show3DModelStage modelStage = new Show3DModelStage();
        modelStage.showStage();
    }
}
