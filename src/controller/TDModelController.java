package controller;

import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TDModelController
{
    public Slider modelSlider;
    public ImageView modelView;

    public void showModel()
    {
        int index = (int) (modelSlider.getValue() / 2.7777);
        Image model = new Image("file:src/resource/image/Bridge/BridgeModel" + index + ".png");
        modelView.setImage(model);
    }
}
