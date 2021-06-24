package controller;

import application.AddBasicInfoStage;
import application.BeamInfoStage;
import application.SettingStage;
import hibernate.entities.BeamInfoEntity;
import hibernate.model.BeamInfoModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Hashtable;
import java.util.List;

/**
 * @author Robert Chen
 */
public class MainController {
    public Button preBeam1;
    public Button preBeam2;
    public Button preBeam3;
    public Button preBeam4;
    public Button preBeam5;
    public Button preBeam6;
    public Button preBeam7;
    public Button preBeam8;
    public Button preBeam9;
    public Button preBeam10;
    public Button tieBeam1;
    public Button tieBeam2;
    public Button tieBeam3;
    public Button tieBeam4;
    public Button tieBeam5;
    public Button tieBeam6;
    public Button tieBeam7;
    public Button tieBeam8;
    public Button tieBeam9;
    public Button tieBeam10;
    public Button pourBeam1;
    public Button pourBeam2;
    public Button pourBeam3;
    public Button pourBeam4;
    public Button pourBeam5;
    public Button pourBeam6;
    public Button pourBeam7;
    public Button pourBeam8;
    public Button pourBeam9;
    public Button pourBeam10;
    public Button cureBeam1;
    public Button cureBeam2;
    public Button cureBeam3;
    public Button cureBeam4;
    public Button cureBeam5;
    public Button cureBeam6;
    public Button cureBeam7;
    public Button cureBeam8;
    public Button cureBeam9;
    public Button cureBeam10;
    public Button storeBeam1;
    public Button storeBeam2;
    public Button storeBeam3;
    public Button storeBeam4;
    public Button storeBeam5;
    public Button storeBeam6;
    public Button storeBeam7;
    public Button storeBeam8;
    public Button storeBeam9;
    public Button storeBeam10;
    public Button readDatabaseButton;
    public TextField beamSearchTf;
    public Button queryButton;
    public Hashtable<String, Button> beamHashTable = new Hashtable<>();
    public Button settingBtn;

    public void clickedPreBeam1() { getBeamInfo(preBeam1); }

    public void clickedPreBeam2() { getBeamInfo(preBeam2); }

    public void clickedPreBeam3() { getBeamInfo(preBeam3); }

    public void clickedPreBeam4() {
        getBeamInfo(preBeam4);
    }

    public void clickedPreBeam5() {
        getBeamInfo(preBeam5);
    }

    public void clickedPreBeam6() {
        getBeamInfo(preBeam6);
    }

    public void clickedPreBeam7() {
        getBeamInfo(preBeam7);
    }

    public void clickedPreBeam8() {
        getBeamInfo(preBeam8);
    }

    public void clickedPreBeam9() {
        getBeamInfo(preBeam9);
    }

    public void clickedPreBeam10() {
        getBeamInfo(preBeam10);
    }

    public void clickedTieBeam1() {
        getBeamInfo(tieBeam1);
    }

    public void clickedTieBeam2() {
        getBeamInfo(tieBeam2);
    }

    public void clickedTieBeam3() {
        getBeamInfo(tieBeam3);
    }

    public void clickedTieBeam4() { getBeamInfo(tieBeam4); }

    public void clickedTieBeam5() { getBeamInfo(tieBeam5); }

    public void clickedTieBeam6() { getBeamInfo(tieBeam6); }

    public void clickedTieBeam7() { getBeamInfo(tieBeam7); }

    public void clickedTieBeam8() { getBeamInfo(tieBeam8); }

    public void clickedTieBeam9() { getBeamInfo(tieBeam9); }

    public void clickedTieBeam10() { getBeamInfo(pourBeam10); }

    public void clickedPourBeam1() { getBeamInfo(pourBeam1); }

    public void clickedPourBeam2() { getBeamInfo(pourBeam2); }

    public void clickedPourBeam3() { getBeamInfo(pourBeam3); }

    public void clickedPourBeam4() {
        getBeamInfo(pourBeam4);
    }

    public void clickedPourBeam5() {
        getBeamInfo(pourBeam5);
    }

    public void clickedPourBeam6() {
        getBeamInfo(pourBeam6);
    }

    public void clickedPourBeam7() {
        getBeamInfo(pourBeam7);
    }

    public void clickedPourBeam8() {
        getBeamInfo(pourBeam8);
    }

    public void clickedPourBeam9() {
        getBeamInfo(pourBeam9);
    }

    public void clickedPourBeam10() { getBeamInfo(pourBeam10); }

    public void clickedCureBeam1() { getBeamInfo(cureBeam1); }

    public void clickedCureBeam2() {
        getBeamInfo(cureBeam2);
    }

    public void clickedCureBeam3() { getBeamInfo(cureBeam3); }

    public void clickedCureBeam4() { getBeamInfo(cureBeam4); }

    public void clickedCureBeam5() {
        getBeamInfo(cureBeam5);
    }

    public void clickedCureBeam6() { getBeamInfo(cureBeam6); }

    public void clickedCureBeam7() {
        getBeamInfo(cureBeam7);
    }

    public void clickedCureBeam8() {
        getBeamInfo(cureBeam8);
    }

    public void clickedCureBeam9() {
        getBeamInfo(cureBeam9);
    }

    public void clickedCureBeam10() { getBeamInfo(cureBeam10); }

    public void clickedStoreBeam1() {
        getBeamInfo(storeBeam1);
    }

    public void clickedStoreBeam2() { getBeamInfo(storeBeam2); }

    public void clickedStoreBeam3() {
        getBeamInfo(storeBeam3);
    }

    public void clickedStoreBeam4() {
        getBeamInfo(storeBeam4);
    }

    public void clickedStoreBeam5() {
        getBeamInfo(storeBeam5);
    }

    public void clickedStoreBeam6() {
        getBeamInfo(storeBeam6);
    }

    public void clickedStoreBeam7() {
        getBeamInfo(storeBeam7);
    }

    public void clickedStoreBeam8() {
        getBeamInfo(storeBeam8);
    }

    public void clickedStoreBeam9() {
        getBeamInfo(storeBeam9);
    }

    public void clickedStoreBeam10() {
        getBeamInfo(storeBeam10);
    }

    public void setBeamHashTable() {
        beamHashTable.put("预处理1", preBeam1);
        beamHashTable.put("预处理2", preBeam2);
        beamHashTable.put("预处理3", preBeam3);
        beamHashTable.put("预处理4", preBeam4);
        beamHashTable.put("预处理5", preBeam5);
        beamHashTable.put("预处理6", preBeam6);
        beamHashTable.put("预处理7", preBeam7);
        beamHashTable.put("预处理8", preBeam8);
        beamHashTable.put("预处理9", preBeam9);
        beamHashTable.put("预处理10", preBeam10);
        beamHashTable.put("扎钢筋1", tieBeam1);
        beamHashTable.put("扎钢筋2", tieBeam2);
        beamHashTable.put("扎钢筋3", tieBeam3);
        beamHashTable.put("扎钢筋4", tieBeam4);
        beamHashTable.put("扎钢筋5", tieBeam5);
        beamHashTable.put("扎钢筋6", tieBeam6);
        beamHashTable.put("扎钢筋7", tieBeam7);
        beamHashTable.put("扎钢筋8", tieBeam8);
        beamHashTable.put("扎钢筋9", tieBeam9);
        beamHashTable.put("扎钢筋10", tieBeam10);
        beamHashTable.put("浇筑1", pourBeam1);
        beamHashTable.put("浇筑2", pourBeam2);
        beamHashTable.put("浇筑3", pourBeam3);
        beamHashTable.put("浇筑4", pourBeam4);
        beamHashTable.put("浇筑5", pourBeam5);
        beamHashTable.put("浇筑6", pourBeam6);
        beamHashTable.put("浇筑7", pourBeam7);
        beamHashTable.put("浇筑8", pourBeam8);
        beamHashTable.put("浇筑9", pourBeam9);
        beamHashTable.put("浇筑10", pourBeam10);
        beamHashTable.put("养护1", cureBeam1);
        beamHashTable.put("养护2", cureBeam2);
        beamHashTable.put("养护3", cureBeam3);
        beamHashTable.put("养护4", cureBeam4);
        beamHashTable.put("养护5", cureBeam5);
        beamHashTable.put("养护6", cureBeam6);
        beamHashTable.put("养护7", cureBeam7);
        beamHashTable.put("养护8", cureBeam8);
        beamHashTable.put("养护9", cureBeam9);
        beamHashTable.put("养护10", cureBeam10);
        beamHashTable.put("存储1", storeBeam1);
        beamHashTable.put("存储2", storeBeam2);
        beamHashTable.put("存储3", storeBeam3);
        beamHashTable.put("存储4", storeBeam4);
        beamHashTable.put("存储5", storeBeam5);
        beamHashTable.put("存储6", storeBeam6);
        beamHashTable.put("存储7", storeBeam7);
        beamHashTable.put("存储8", storeBeam8);
        beamHashTable.put("存储9", storeBeam9);
        beamHashTable.put("存储10", storeBeam10);
        beamHashTable.put("已运出1", storeBeam1);
        beamHashTable.put("已运出2", storeBeam2);
        beamHashTable.put("已运出3", storeBeam3);
        beamHashTable.put("已运出4", storeBeam4);
        beamHashTable.put("已运出5", storeBeam5);
        beamHashTable.put("已运出6", storeBeam6);
        beamHashTable.put("已运出7", storeBeam7);
        beamHashTable.put("已运出8", storeBeam8);
        beamHashTable.put("已运出9", storeBeam9);
        beamHashTable.put("已运出10", storeBeam10);
    }

    public void getBeamInfo(Button button) {
        String beamId = button.getText();
        String beamName = button.getId();

        BeamInfoEntity beamInfoEntity = new BeamInfoModel().findById(beamId);
        String beamState = beamInfoEntity == null ? "" : beamInfoEntity.getBeamState();

        boolean showInfoExist = (beamName.startsWith("preBeam") && "预处理".equals(beamState)) ||
                        (beamName.startsWith("tieBeam") && "扎钢筋".equals(beamState)) ||
                        (beamName.startsWith("pourBeam") && "浇筑".equals(beamState)) ||
                        (beamName.startsWith("cureBeam") && "养护".equals(beamState)) ||
                        (beamName.startsWith("storeBeam") && ("存储".equals(beamState) ||
                        "已运出".equals(beamState)));

        if (beamInfoEntity == null) {
            // 发现预制梁的基本信息为空时，添加预制梁
            if (button.getId().startsWith("preBeam")) {
                AddBasicInfoStage addBasicInfoStage = new AddBasicInfoStage();
                addBasicInfoStage.initializePreBeam(beamId);
                addBasicInfoStage.showStage();
            }
        } else if (showInfoExist) {
            // 针对每个区域，当预制梁存在于该区域时，才展示预制梁的基本信息
            BeamInfoStage beamInfoStage = new BeamInfoStage();
            beamInfoStage.initializePreBeam(beamId);
            beamInfoStage.showStage();
        }
    }

    public void readDatabase() {
        BeamInfoModel beamInfoModel = new BeamInfoModel();
        List<BeamInfoEntity> beams = beamInfoModel.findAll();

        for (Button button : beamHashTable.values()) {
            String existBeamStyle = button.getStyle();
            button.setStyle(existBeamStyle.replace("#F0F0F0", "#FFFFFF"));
        }

        for (BeamInfoEntity beam : beams) {
            String beamState = beam.getBeamState();
            String beamId = beam.getBeamId();
            Button tempBeam = beamHashTable.get(beamState + beamId);
            String existBeamStyle = tempBeam.getStyle();
            tempBeam.setStyle(existBeamStyle.replace("#FFFFFF", "#F0F0F0"));
        }
    }

    public void queryBeam() {
        String beamId = beamSearchTf.getText();
        BeamInfoModel beamInfoModel = new BeamInfoModel();

        if ("".equals(beamId)) {
            // 查询关键字为空
            beamSearchTf.setPromptText("🔍 请输入查询关键字！");
        } else if (beamInfoModel.findById(beamId) == null) {
            // 没有预制梁的信息
            Alert beamIsNull = new Alert(Alert.AlertType.INFORMATION);
            beamIsNull.setTitle("来自 搜索预制梁 的消息");
            beamIsNull.setHeaderText("数据库中暂无此预制梁的信息");
            beamIsNull.show();
        } else {
            BeamInfoStage beamInfoStage = new BeamInfoStage();
            beamInfoStage.initializePreBeam(beamId);
            beamInfoStage.showStage();
        }
    }

    public void changeSetting() {
        SettingStage settingStage = new SettingStage();
        settingStage.showStage();
    }
}
