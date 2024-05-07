package cosc202.andie;

import java.awt.image.*;
import javax.swing.JOptionPane;
import java.util.ResourceBundle;

/**
 * <p>
 * ImageOperation to apply a sharpen filter.
 * </p>
 * 
 * <p>
 * A sharpen filter enhances the color contrast around edges in the image
 * by enhancing the differences between neighbouring pixel values
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Katrina Hogg
 * @version 1.0
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {

    /**
     * Sharpen filter constructor
     */
    SharpenFilter() {
    }

    public BufferedImage apply(BufferedImage input) {
        float[] array = { 0, -1 / 2.0f, 0,
                -1 / 2.0f, 3, -1 / 2.0f,
                0, -1 / 2.0f, 0 };

        Kernel kernel = new Kernel(3, 3, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        try {
            if (input != null) {

                EdgeHandling eh = new EdgeHandling();

                BufferedImage paddedInput = eh.filterImage(input, 3);

                BufferedImage paddedOutput = new BufferedImage(
                        paddedInput.getColorModel(),
                        paddedInput.copyData(null),
                        paddedInput.isAlphaPremultiplied(), null);

                convOp.filter(paddedInput, paddedOutput);

                BufferedImage output = eh.cropImage(paddedOutput, 3);
                return output;

            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
            JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
            return null;
        }

    }
}
