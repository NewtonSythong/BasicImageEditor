package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.*;
import java.util.ResourceBundle;

/**
 * <p>
 * ImageOperation to apply a Median filter 
 * </p>
 * 
 * <p>
 * A median filter takes an image  takes all of the pixel values in a local
 * neighbourhood and sorts them. The new pixel value is then the middle value (the median) from the
 * sorted list.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 *
 * @author Cam Clark, Jenny Kim
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * 
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */

    private int radius;

    MedianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * 
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter(int)
     * 
     */

    MedianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply Median filter conversion to an image.
     * </p>
     * <p>
     * The conversion from red, green, and blue values to a median colour, uses a
     * weighted average of RGBA.  The size of the array to sort to find the median colour 
     * </p>
     *
     * @param input The image to be converted to a median colour.
     * 
     * @return The resulting median colour (blurred) version of the image.
     * 
     */

    public BufferedImage apply(BufferedImage input) {
        try {
            if (input != null) {
                BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                        input.isAlphaPremultiplied(), null);
                int newA;
                int newR;
                int newG;
                int newB;
                int size = (2 * radius + 1) * (2 * radius + 1);
                int[] medianArrayA = new int[size];
                int[] medianArrayR = new int[size];
                int[] medianArrayG = new int[size];
                int[] medianArrayB = new int[size];

                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        int count = 0;

                        for (int offsetY = -radius; offsetY <= radius; offsetY++) {
                            for (int offsetX = -radius; offsetX <= radius; offsetX++) {

                                int pixelX = Math.min(Math.max(x + offsetX, 0), input.getWidth() - 1);
                                int pixelY = Math.min(Math.max(y + offsetY, 0), input.getHeight() - 1);
                                int argbV1 = input.getRGB(pixelX, pixelY);

                                int a = (argbV1 >> 24) & 0xFF;
                                int r = (argbV1 >> 16) & 0xFF;
                                int g = (argbV1 >> 8) & 0xFF;
                                int b = argbV1 & 0xFF;

                                if (count < size) {
                                    medianArrayA[count] = a;
                                    medianArrayR[count] = r;
                                    medianArrayG[count] = g;
                                    medianArrayB[count] = b;
                                    count++;
                                }
                            }

                        }
                        newA = medianValue(medianArrayA, medianArrayA.length);
                        newR = medianValue(medianArrayR, medianArrayR.length);
                        newG = medianValue(medianArrayG, medianArrayG.length);
                        newB = medianValue(medianArrayB, medianArrayB.length);
                        int argbV2 = (newA << 24) | (newR << 16) | (newG << 8) | newB;
                        output.setRGB(x, y, argbV2);

                    }

                }
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
/**
 * method which takes the unsorted array of values for RGBA and sorts them
 * Then returning the median value of each array.
 * @param unsortedArray the array to be sorted
 * @param n the size of the array 
 * @return The median value of the array.
 */
    public int medianValue(int[] unsortedArray, int n) {

        Arrays.sort(unsortedArray);
        if (n % 2 != 0) {
            return unsortedArray[n / 2];
        } else {
            return unsortedArray[n / 2 - 1] + unsortedArray[n / 2] / 2;
        }
    }
}