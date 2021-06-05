package application;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author MING
 * @author Liu BaoYang
 * @author Wang YiYan
 * @author Robert Chen
 */
public class Show3DModelStage extends Application {
    private static Stage showModelStage = null;
    public static Stage getShowModelStage() {
        if (showModelStage == null) { showModelStage = new Stage(); }
        return showModelStage;
    }

    // 创建包含 3D 模型的场景
    private Scene createScene() {
        // 设置 3D 模型的照相机、创建 3D 模型的信息组与漫反射灯，使 3D 模型的每个角度都能看到颜色
        Group model = load3dModel(getClass().getResource("../resource/beamModel/bridge1.obj"));
        PerspectiveCamera camera = new PerspectiveCamera(true);
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);

        // 调整照相机角度
        camera.setTranslateX(-25);
        camera.setTranslateY(0);
        camera.setTranslateZ(-93);

        // 调整模型角度、添加灯光效果
        model.getTransforms().add(new Rotate(10, Rotate.X_AXIS));
        model.getTransforms().add(new Rotate(60, Rotate.Y_AXIS));
        model.getTransforms().add(new Rotate(-90, Rotate.Z_AXIS));
        model.getChildren().add(ambientLight);

        Scene scene = new Scene(model, 1000, 660, true);
        scene.setCamera(camera);
        return scene;
    }

    // 导入 3D 模型数据
    private Group load3dModel(URL url) {
        Group modelRoot = new Group();
        ObjModelImporter importer = new ObjModelImporter();
        importer.read(url);
        for (MeshView view : importer.getImport()) { modelRoot.getChildren().add(view); }
        return modelRoot;
    }

    @Override
    public void start(Stage stage) {
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("桥梁 3D模型 展示界面");
        stage.setScene(createScene());
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
    public void showStage() { start(getShowModelStage()); }
}
