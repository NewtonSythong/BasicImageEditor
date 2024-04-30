package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.*;

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
            JOptionPane.showMessageDialog(null, "Please select an image before filter");
            return null;

        }

    }
}
