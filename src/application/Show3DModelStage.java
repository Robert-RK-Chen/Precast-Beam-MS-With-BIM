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
public class Show3DModelStage extends Application
{
    private static Stage showModelStage = null;

    public static Stage getShowModelStage()
    {
        if (showModelStage == null)
        {
            showModelStage = new Stage();
        }
        return showModelStage;
    }

    private Scene createScene()
    {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateX(-25);
        camera.setTranslateY(0);
        camera.setTranslateZ(-93);
        Group model = load3dModel(getClass().getResource("../resource/beamModel/bridge1.obj"));
        model.getTransforms().add(new Rotate(10, Rotate.X_AXIS));
        model.getTransforms().add(new Rotate(60, Rotate.Y_AXIS));
        model.getTransforms().add(new Rotate(-90, Rotate.Z_AXIS));
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        Group root = new Group(model);
        Scene scene = new Scene(root, 1000, 660, true);
        scene.setCamera(camera);
        root.getChildren().add(ambientLight);
        return scene;
    }

    private Group load3dModel(URL url)
    {
        Group modelRoot = new Group();
        ObjModelImporter importer = new ObjModelImporter();
        importer.read(url);
        for (MeshView view : importer.getImport())
        {
            modelRoot.getChildren().add(view);
        }
        return modelRoot;
    }

    @Override
    public void start(Stage stage)
    {
        URL iconUrl = getClass().getResource("../resource/image/icon/prebeams.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.setScene(createScene());
        stage.getIcons().add(icon);
        stage.setTitle("桥梁 3D模型 展示界面");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void showStage()
    {
        start(getShowModelStage());
    }
}
