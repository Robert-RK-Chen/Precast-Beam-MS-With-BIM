package controller;

import application.AddServiceInfoStage;
import application.Show3dModelStage;
import com.google.zxing.WriterException;
import hibernate.entities.*;
import hibernate.model.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.QrCodeUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * @author Robert Chen
 */
public class BeamInfoController {
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
    public Label stateLabel;
    public TextField wireInspectorTf;
    public TextField wireStartTf;
    public TextField wireFinishTf;
    public TextField pouringInspectorTf;
    public TextField pouringStartTf;
    public TextField pouringFinishTf;
    public TextField curingInspectorTf;
    public TextField curingStartTf;
    public TextField curingFinishTf;
    public TextField storeInspectorTf;
    public TextField storeStartTf;
    public TextField shipmentExpectTf;
    public TextField shipmentActualTf;
    public Button nextStepButton;
    public Button qrCodeButton;
    public Button deleteBeamButton;
    private final BeamInfoModel beamInfoModel = new BeamInfoModel();
    private final TieInfoModel tieInfoModel = new TieInfoModel();
    private final PouringInfoModel pouringInfoModel = new PouringInfoModel();
    private final CuringInfoModel curingInfoModel = new CuringInfoModel();
    private final BeamStoreModel beamStoreModel = new BeamStoreModel();

    public void init(String id) {
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(id);
        TieInfoEntity tieInfoEntity = tieInfoModel.findById(id);
        PouringInfoEntity pouringInfoEntity = pouringInfoModel.findById(id);
        CuringInfoEntity curingInfoEntity = curingInfoModel.findById(id);
        BeamStoreEntity beamStoreEntity = beamStoreModel.findById(id);

        // ?????????????????????
        URL imageUrl = getClass().getResource("../resource/image/preview/" + beamInfoEntity.getBeamKind() + ".jpg");
        if (imageUrl != null) {
            beamImageView.setImage(new Image(imageUrl.toExternalForm()));
        }

        // ??????????????????????????????
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

        // ???????????????????????????????????????????????????????????????????????????
        if (tieInfoEntity != null) {
            wireInspectorTf.setText(tieInfoEntity.getWireInspector());
            wireStartTf.setText(tieInfoEntity.getWireStart().toString());
            wireFinishTf.setText(tieInfoEntity.getWireFinish().toString());
        }

        // ?????????????????????????????????????????????????????????????????????
        if (pouringInfoEntity != null) {
            pouringInspectorTf.setText(pouringInfoEntity.getPouringInspector());
            pouringStartTf.setText(pouringInfoEntity.getPouringStart().toString());
            pouringFinishTf.setText(pouringInfoEntity.getPouringFinish().toString());
        }

        // ?????????????????????????????????????????????????????????????????????
        if (curingInfoEntity != null) {
            curingInspectorTf.setText(curingInfoEntity.getCuringInspector());
            curingStartTf.setText(curingInfoEntity.getCuringStart().toString());
            curingFinishTf.setText(curingInfoEntity.getCuringFinish().toString());
        }

        // ?????????????????????????????????????????????????????????????????????
        if (beamStoreEntity != null) {
            storeInspectorTf.setText(beamStoreEntity.getStoreInspector());
            storeStartTf.setText(beamStoreEntity.getStoreStart().toString());
            shipmentExpectTf.setText(beamStoreEntity.getShipmentExpect().toString());
            shipmentActualTf.setText(beamStoreEntity.getShipmentActual().toString());
        }
    }

    public void nextStep() {
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());

        // ????????????????????????
        String beamState = beamInfoEntity.getBeamState();
        switch (beamState) {
            // ??????????????????????????????????????????????????????
            case "??????" -> {
                beamInfoEntity.setBeamState("?????????");
                beamInfoModel.update(beamInfoEntity);
                Alert transBeam = new Alert(Alert.AlertType.INFORMATION);
                transBeam.setTitle("?????? ????????????????????? ?????????");
                transBeam.setHeaderText("?????????????????????!");
                transBeam.show();
            }

            // ????????????????????????????????????????????????
            case "?????????" -> {
                Alert finishBeam = new Alert(Alert.AlertType.INFORMATION);
                finishBeam.setTitle("?????? ????????????????????? ?????????");
                finishBeam.setHeaderText("??????????????????????????????\n?????????????????????????????????");
                finishBeam.show();
            }

            // ?????????????????????????????????
            default -> {
                AddServiceInfoStage serviceInfoStage = new AddServiceInfoStage();
                serviceInfoStage.initializePreBeam(beamIdTf.getText());
                serviceInfoStage.showStage();
            }
        }
    }

    public void show3dModel() {
        new Show3dModelStage().showStage();
    }

    public void deleteBeam() {
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());

        Alert deleteBeam = new Alert(Alert.AlertType.CONFIRMATION);
        deleteBeam.setTitle("??????????????????????????????");
        deleteBeam.setHeaderText("????????????????????????????????????");
        Optional<ButtonType> result = deleteBeam.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            beamInfoModel.delete(beamInfoEntity);
            Alert deleteSuccess = new Alert(Alert.AlertType.INFORMATION);
            deleteSuccess.setTitle("??????????????????????????????");
            deleteSuccess.setHeaderText("????????????????????????");
            deleteSuccess.show();
            Stage stage = (Stage) beamIdTf.getScene().getWindow();
            stage.close();
        }
    }

    public void showQrCode() {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        ImageView qrCodeView = new ImageView();
        borderPane.setCenter(qrCodeView);

        try {
            Image qrCode = new Image("file:" + new QrCodeUtil().getQrCode(beamIdTf.getText()).toString());
            qrCodeView.setImage(qrCode);
            Scene scene = new Scene(borderPane, qrCode.getWidth(), qrCode.getHeight());
            stage.setTitle("????????????????????????????????????");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException | WriterException exception) {
            Alert resourceLoadFailed = new Alert(Alert.AlertType.ERROR);
            resourceLoadFailed.setTitle("??????????????????????????????");
            resourceLoadFailed.setHeaderText("?????????????????????????????????????????????");
            resourceLoadFailed.show();
        }
    }
}
