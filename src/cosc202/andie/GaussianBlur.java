package cosc202.andie;

import java.awt.image.*;
import javax.swing.*;

/** This class applies a Gaussian blur to an image
 * 
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
        float kernelSum = 0f;
        int index = 0;
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                
                array[index] = gaussian(x, y, sigma);
                kernelSum+=array[index];
                index++;
            }
        }

        float[] newArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = normalisedGaussian(array[i], kernelSum);
        }



        Kernel kernel = new Kernel(size, size, newArray);

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
    public static float gaussian(int x, int y, float sigma) {
        
        return (float) (Math.exp(-((x*x + y*y) / (2.0f * sigma * sigma))) * (1.0f / (2.0f * Math.PI * sigma * sigma)));
    }

    public static float normalisedGaussian(float value, float kernelSum) {
        return value / kernelSum;
    }
}
