package controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Properties;

public class TDModelController
{
    public Slider modelSlider;
    public ImageView modelView;
    private static double rot;
    public Label rotationLabel;

    public void showModel()
    {
        Properties properties = new Properties();
        try
        {
            properties.load(TDModelController.class.getClassLoader().getResourceAsStream("model.properties"));
            rot = Double.parseDouble(properties.getProperty("rotation"));
        }
        catch (IOException ioException)
        {
            rot = 0;
            ioException.printStackTrace();
        }
        int index = (int) (modelSlider.getValue() / rot);
        Image model = new Image("file:src/resource/image/Bridge/BridgeModel" + index + ".png");
        modelView.setImage(model);
        rotationLabel.setText((int) (3.6 * modelSlider.getValue()) + "°");
    }
}
