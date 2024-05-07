package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.util.ResourceBundle;

public class SobelFilter implements ImageOperation {

    int direction;

    public SobelFilter() {
        this.direction = 1;
    }

    public SobelFilter(int direction) {
        this.direction = direction;
    }

    public BufferedImage apply(BufferedImage input) {
        try {

            if (input != null) {
                float[][] kernels = {
                        {
                                -(1 / 2f), 0, 1 / 2,
                                -1, 0, 1,
                                -(1 / 2), 0, 1 / 2
                        },
                        {
                                -(1 / 2), -1, -(1 / 2),
                                0, 0, 0,
                                1 / 2, 1, 1 / 2
                        }
                };

                BufferedImage output = ConvolutionNew.apply(input, kernels[direction], 1);
                return output;
            } else
                throw new NullPointerException();
        } catch (NullPointerException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
            JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
            return null;

        }

    }
}
