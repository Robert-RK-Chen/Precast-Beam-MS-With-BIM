package controller;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Robert Chen
 */
public class ThreeDemModelController
{
    public Slider modelSlider;
    public ImageView modelView;
    public Label rotationLabel;

    public void showModel()
    {
        Properties properties = new Properties();
        double rot;
        try
        {
            properties.load(ThreeDemModelController.class.getClassLoader().getResourceAsStream("software.properties"));
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
