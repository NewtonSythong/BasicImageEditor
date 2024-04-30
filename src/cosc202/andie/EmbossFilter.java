package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.*;

public class EmbossFilter implements ImageOperation {

    int direction;

    public EmbossFilter() {
        this.direction = 1;
    }

    public EmbossFilter(int direction) {
        this.direction = direction;
    }

    public BufferedImage apply(BufferedImage input) {

            try {
                if (input != null) {

                float[][] kernels = {
                        {
                                0, 0, 0,
                                1, 0, -1,
                                0, 0, 0
                        },
                        {
                                1, 0, 0,
                                0, 0, 0,
                                0, 0, -1
                        },
                        {
                                0, 1, 0,
                                0, 0, 0,
                                0, -1, 0
                        },
                        {
                                0, 0, 1,
                                0, 0, 0,
                                -1, 0, 0
                        },
                        {
                                0, 0, 0,
                                -1, 0, 1,
                                0, 0, 0
                        },
                        {
                                -1, 0, 0,
                                0, 0, 0,
                                0, 0, 1
                        },
                        {
                                0, -1, 0,
                                0, 0, 0,
                                0, 1, 0
                        },
                        {
                                0, 0, -1,
                                0, 0, 0,
                                1, 0, 0
                        }
                };

                BufferedImage output = ConvolutionNew.apply(input, kernels[direction - 1], 1);
                return output;

            } else throw new NullPointerException();

            }catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Please select an image before filter");
                return null;
        }
    }

}
