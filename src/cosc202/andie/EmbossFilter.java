package cosc202.andie;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;


/**
 * This class is used to apply an emboss filter to an image.
 * It applies an emboss effect to the image in a specific direction.
 * 
 * @author Jenny Kim
 * 
 */
public class EmbossFilter implements ImageOperation, Serializable {

    private  int direction;

    /**
     * Constructs an EmbossFilter with a default direction of 1.
     */
    public EmbossFilter() {
        this.direction = 1;
    }

    /**
     * Constructs an EmbossFilter with a given direction.
     */
    public EmbossFilter(int direction) {
        this.direction = direction;
    }

    /**
     * Applies the emboss filter operation to the input image.
     * @param input the image to which the filter is applied.
     * @return the image with the emboss filter applied.
     */
    public BufferedImage apply(BufferedImage input) {

            try {
                if (input != null) {

                float[][] kernels = {
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
                        },
                        {
                                0, 0, 0,
                                -1, 0, 1,
                                0, 0, 0
                        },
                        {
                                0, 0, 0,
                                0, 0, 0,
                                0, 0, 0
                        },
                        {
                                0, 0, 0,
                                1, 0, -1,
                                0, 0, 0
                        },
                        {
                                0, 0, 1,
                                0, 0, 0,
                                -1, 0, 0
                        },
                        {
                                0, 1, 0,
                                0, 0, 0,
                                0, -1, 0
                        },
                        {
                                1, 0, 0,
                                0, 0, 0,
                                0, 0, -1
                        }
                };

                BufferedImage output = ConvolutionNew.apply(input, kernels[direction - 1], 1, 127);
                return output;

            } else throw new NullPointerException();

            }catch (NullPointerException e) {
                ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
                JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
                return null;
        }
    }

}
