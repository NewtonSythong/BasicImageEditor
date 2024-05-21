package cosc202.andie;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
/**
 * This class is used to implement the Sobel filter to an image.
 * The Sobel filter is applied to the image in a specidic direction.
 * 
 * @author Jenny Kim
 */
public class SobelFilter implements ImageOperation, Serializable {

    int direction;

    /**
     * Contructs a new SobelFilter object with the default direction of 1.
     */
    public SobelFilter() {
        this.direction = 1;
    }

    /**
     * Contructs a new SobelFilter object with the specified direction.
     * @param direction the direction of the Sobel filter
     */
    public SobelFilter(int direction) {
        this.direction = direction;
    }

    /**
     * Applies the Sobel filter to the input image.
     * @param input the input image.
     * @return the output image with the Sobel operation applied
     */
    public BufferedImage apply(BufferedImage input) {
        try {

            if (input != null) {
                float[][] kernels = {
                        {
                                -1 / 2.0f, 0, 1 / 2.0f,
                                -1, 0, 1,
                                -1 / 2.0f, 0, 1 / 2.0f
                        },
                        {
                                -1 / 2.0f, -1, -1 / 2.0f,
                                0, 0, 0,
                                1 / 2.0f, 1, 1 / 2.0f
                        }
                };

                BufferedImage output = ConvolutionNew.apply(input, kernels[direction-1], 1, 127);
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
