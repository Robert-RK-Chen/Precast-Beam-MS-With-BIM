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

        // 预制梁的预览图
        URL imageUrl = getClass().getResource("../resource/image/preview/" + beamInfoEntity.getBeamKind() + ".jpg");
        if (imageUrl != null) {
            beamImageView.setImage(new Image(imageUrl.toExternalForm()));
        }

        // 加载预制梁的基本信息
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

        // 当预制梁的状态是【扎钢筋】时，加载扎钢筋的业务信息
        if (tieInfoEntity != null) {
            wireInspectorTf.setText(tieInfoEntity.getWireInspector());
            wireStartTf.setText(tieInfoEntity.getWireStart().toString());
            wireFinishTf.setText(tieInfoEntity.getWireFinish().toString());
        }

        // 当预制梁的状态是【浇筑】时，加载浇筑的业务信息
        if (pouringInfoEntity != null) {
            pouringInspectorTf.setText(pouringInfoEntity.getPouringInspector());
            pouringStartTf.setText(pouringInfoEntity.getPouringStart().toString());
            pouringFinishTf.setText(pouringInfoEntity.getPouringFinish().toString());
        }

        // 当预制梁的状态是【养护】时，加载养护的业务信息
        if (curingInfoEntity != null) {
            curingInspectorTf.setText(curingInfoEntity.getCuringInspector());
            curingStartTf.setText(curingInfoEntity.getCuringStart().toString());
            curingFinishTf.setText(curingInfoEntity.getCuringFinish().toString());
        }

        // 当预制梁的状态是【存储】时，加载存储的业务信息
        if (beamStoreEntity != null) {
            storeInspectorTf.setText(beamStoreEntity.getStoreInspector());
            storeStartTf.setText(beamStoreEntity.getStoreStart().toString());
            shipmentExpectTf.setText(beamStoreEntity.getShipmentExpect().toString());
            shipmentActualTf.setText(beamStoreEntity.getShipmentActual().toString());
        }
    }

    public void nextStep() {
        BeamInfoEntity beamInfoEntity = beamInfoModel.findById(beamIdTf.getText());

        // 获取预制梁的状态
        String beamState = beamInfoEntity.getBeamState();
        switch (beamState) {
            // 【存储】状态则直接改状态为【已运出】
            case "存储" -> {
                beamInfoEntity.setBeamState("已运出");
                beamInfoModel.update(beamInfoEntity);
                Alert transBeam = new Alert(Alert.AlertType.INFORMATION);
                transBeam.setTitle("来自 添加预制梁业务 的消息");
                transBeam.setHeaderText("预制梁已经运出!");
                transBeam.show();
            }

            // 已运出则提醒用户没有下一步操作了
            case "已运出" -> {
                Alert finishBeam = new Alert(Alert.AlertType.INFORMATION);
                finishBeam.setTitle("来自 添加预制梁业务 的消息");
                finishBeam.setHeaderText("这块预制梁已经运出，\n完成了所有的业务方法！");
                finishBeam.show();
            }

            // 加载添加业务方法的页面
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
        deleteBeam.setTitle("来自删除预制梁的警告");
        deleteBeam.setHeaderText("确定要删除这块预制梁吗？");
        Optional<ButtonType> result = deleteBeam.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            beamInfoModel.delete(beamInfoEntity);
            Alert deleteSuccess = new Alert(Alert.AlertType.INFORMATION);
            deleteSuccess.setTitle("来自删除预制梁的消息");
            deleteSuccess.setHeaderText("预制梁删除成功！");
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
            stage.setTitle("预制梁唯一标识符的二维码");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException | WriterException exception) {
            Alert resourceLoadFailed = new Alert(Alert.AlertType.ERROR);
            resourceLoadFailed.setTitle("二维码资源加载失败！");
            resourceLoadFailed.setHeaderText("请联系技术支持以获得更多帮助！");
            resourceLoadFailed.show();
        }
    }
}
