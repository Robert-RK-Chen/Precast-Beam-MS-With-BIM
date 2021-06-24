package controller;

import hibernate.entities.BeamInfoEntity;
import hibernate.model.BeamInfoModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Robert Chen
 */
public class AddBasicInfoController {
    // 来自 FXML 绑定的的控件
    public TextField beamIdTf;
    public ComboBox<String> beamKindCb;
    public TextField steelType1Tf;
    public TextField steelType2Tf;
    public TextField steelType3Tf;
    public TextField lengthTf;
    public TextField widthTf;
    public TextField heightTf;
    public TextField radiusTf;
    public Button addBeamButton;

    // 初始化方法，用于传递预制梁编号
    public void init(String id) {
        beamIdTf.setText(id);
    }

    // 添加预制梁方法
    public void addBeam() {
        // 创建 Hibernate 模型，Model 用于 CRUD 操作，Entity 作为结果的实体
        BeamInfoEntity beamInfoEntity = new BeamInfoEntity();
        Boolean valueLegal = Boolean.TRUE;

        // 从添加基本信息界面的控件中获取对应的值
        String beamId = beamIdTf.getText();
        String beamKind = beamKindCb.getValue();
        String steelType1 = steelType1Tf.getText();
        String steelType2 = steelType2Tf.getText();
        String steelType3 = steelType3Tf.getText();

        // 三元表达式保证 长方体/圆柱体 的参数不为空
        double length = Double.parseDouble("".equals(lengthTf.getText()) ? "0" : lengthTf.getText());
        double width = Double.parseDouble("".equals(widthTf.getText()) ? "0" : widthTf.getText());
        double height = Double.parseDouble("".equals(heightTf.getText()) ? "0" : heightTf.getText());
        double radius = Double.parseDouble("".equals(radiusTf.getText()) ? "0" : radiusTf.getText());

        // 判断输入条件的合法性
        if (beamKind == null) {
            valueLegal = Boolean.FALSE;
        } else {
            if ("".equals(steelType1) && "".equals(steelType2) && "".equals(steelType3)) {
                valueLegal = Boolean.FALSE;
            }
            if (height <= 0) {
                valueLegal = Boolean.FALSE;
            }
            if ("长方体".equals(beamKind) && (length <= 0 || width <= 0)) {
                valueLegal = Boolean.FALSE;
            }
            if ("圆柱体".equals(beamKind) && radius <= 0) {
                valueLegal = Boolean.FALSE;
            }
        }

        // 向预制梁实体添加信息
        if (valueLegal.equals(Boolean.FALSE)) {
            Alert addFailed = new Alert(Alert.AlertType.INFORMATION);
            addFailed.setTitle("来自 添加预处理的预制梁 的消息");
            addFailed.setHeaderText("""
                    预制梁添加失败，请检查：
                    1. 是否选择了与质量类型；
                    2. 预制梁钢筋类型是否全空；
                    3. 预制梁的参数是否小于等于 0。
                    """);
            addFailed.show();
        } else {
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
            new BeamInfoModel().insert(beamInfoEntity);

            // 当用户新增预制梁成功后提醒用户创建与质量成功
            Alert addSuccess = new Alert(Alert.AlertType.INFORMATION);
            addSuccess.setTitle("来自 添加预处理的预制梁 的消息");
            addSuccess.setHeaderText("预制梁添加成功！");
            addSuccess.show();

            // 关闭预制梁信息添加面板
            ExecutorService singleThreadPool =
                    new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), new ThreadPoolExecutor.AbortPolicy());
            singleThreadPool.execute(() -> {
                Stage stage = (Stage) addBeamButton.getScene().getWindow();
                stage.close();
            });
            singleThreadPool.shutdown();
        }
    }

    // 获取 ComboBox 中预制梁的类型，来保证填入参数的正确性
    public void getBeamKind() {
        String beamKind = beamKindCb.getValue();
        String defaultBeamKind = "长方体";

        if (defaultBeamKind.equals(beamKind)) {
            widthTf.setDisable(false);
            lengthTf.setDisable(false);
            radiusTf.setText("");
            radiusTf.setDisable(true);
        } else {
            radiusTf.setDisable(false);
            widthTf.setText("");
            widthTf.setDisable(true);
            lengthTf.setText("");
            lengthTf.setDisable(true);
        }
    }
}
