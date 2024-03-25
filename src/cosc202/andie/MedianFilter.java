 package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * 
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 
     * 5x5 filter, and so forth.
     * 
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
     * @see MeanFilter(int)
     * 
     */

    MedianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply median filter conversion to an image.
     * </p>
     * <p>
     * The conversion from red, green, and blue values to a median colour, uses a
     * weighted average of RGB
     * </p>
     *
     * @param input The image to be converted to a median colour.
     * 
     * @return The resulting median colour version of the image.
     * 
     */

    public BufferedImage apply(BufferedImage input) {

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
        System.out.println(medianArrayB.length);
        System.out.println(input.getHeight());
        System.out.println(input.getWidth());

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

    }

    public int medianValue(int[] unsortedArray, int n) {

        Arrays.sort(unsortedArray);
        if (n % 2 != 0) {
            return unsortedArray[n / 2];
        } else {
            return unsortedArray[n / 2 - 1] + unsortedArray[n / 2] / 2;
        }
    }
}