package cosc202.andie;

import java.awt.image.*;
import javax.swing.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian blur to an image
 * </p>
 * 
 * <p>
 * The images produced by this operation are still colour images,
 * Gaussian blurring involves performing a weighted average of 
 * surrounding pixels based on the Gaussian distribution
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jenny Kim
 * @version 1.0
 */
public class GaussianBlur implements ImageOperation{

    /**
     * The size of the filter to apply
     */
    private int radius;

    /**
     * Construct a Gaussian blur with a default radius of 1
     */
    GaussianBlur() {
        this(1);
    }

    /**
     * Construct a Gaussian blur with a specified radius
     * 
     * @param radius
     */
    GaussianBlur(int radius) {
        this.radius = radius;
    }


    /**
     * Apply Gaussian blur to an image.
     *
     * @param input The image to be blurred using the 2-dimensional Gaussian
     *              equation.
     * 
     * @return The resulting Gaussian blur version of the image.
     * 
     */
    public BufferedImage apply(BufferedImage input) {

        int size = 2 * radius + 1;
        float sigma = size / 3;

        float[] array = new float[size * size];
        float kernelSum = 0;
        int index = 0;
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                float distance = x * x + y * y;
                array[index] = (float) (Math.exp(-distance / (2 * sigma * sigma)) / (2 * Math.PI * sigma * sigma));
                kernelSum += array[index];
                index++;
            }
        }

        // Normalise the kernal values
        for (int i = 0; i < array.length; i++) {
            array[i] /= kernelSum;
        }

        Kernel kernel = new Kernel(size, size, array);


        ConvolveOp convOp = new ConvolveOp(kernel);

        try {
            if (input != null) {
                BufferedImage output = new BufferedImage(
                        input.getColorModel(),
                        input.copyData(null),
                        input.isAlphaPremultiplied(), null);
                convOp.filter(input, output);
                return output;
            } else {
                throw new NullPointerException();

            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Please select an image before filter");
            return null;
        }
    }
}
