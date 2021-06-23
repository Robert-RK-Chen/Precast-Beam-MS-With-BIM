package controller;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

import java.net.URL;

public class ObjModelController
{
    public Scene modelScene;
    public Group model;
    double[] mouseCoordinate = new double[2];

    // 创建包含 3D 模型的场景
    public Scene init()
    {
        // 设置 3D 模型的照相机、创建 3D 模型的信息组与漫反射灯，使 3D 模型的每个角度都能看到颜色
        model = load3dModel(getClass().getResource("../resource/beamModel/bridge1.obj"));
        PerspectiveCamera camera = new PerspectiveCamera(true);
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        model.getChildren().add(ambientLight);

        // 调整模型角度
        model.getTransforms().add(new Rotate(285, Rotate.X_AXIS));
        model.getTransforms().add(new Rotate(0, Rotate.Y_AXIS));
        model.getTransforms().add(new Rotate(-30, Rotate.Z_AXIS));

        // 调整照相机角度
        camera.setTranslateX(0);
        camera.setTranslateY(-10);
        camera.setTranslateZ(-80);

        modelScene = new Scene(model, 1000, 600, true);
        modelScene.setCamera(camera);
        return modelScene;
    }

    // 导入 3D 模型数据
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

    public void returnMouseCoordinate(MouseEvent mouseEvent)
    {
        mouseCoordinate[0] = mouseEvent.getX();
        mouseCoordinate[1] = mouseEvent.getY();
        System.out.println(mouseEvent.getX() + ", " + mouseEvent.getY());
    }

    public void rotateModel(MouseEvent mouseEvent)
    {
        double mouseOffsetX = mouseEvent.getX() - mouseCoordinate[0];
        double mouseOffsetY = mouseEvent.getY() - mouseCoordinate[1];
        System.out.println(mouseOffsetX + ", " + mouseOffsetY);
//        model.getTransforms().add(new Rotate(275 + mouseOffsetX, Rotate.X_AXIS));
    }
}
