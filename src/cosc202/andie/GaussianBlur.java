package cosc202.andie;

import java.awt.image.*;

/* Blurs when radius is set to 3 or higher 
 * 
*/
public class GaussianBlur implements ImageOperation, java.io.Serializable {

    private int radius;

    // construction Code
    GaussianBlur() {
        this(1);
    }

    // parameters
    GaussianBlur(int radius) {
        this.radius = radius;
    }

    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as a 9-element array
        int size = 2 * radius + 1;
        float sigma = size / 3;

        float[] array = new float[size * size];

        float denom1 = (2 * (float) Math.PI * sigma * sigma);
        float denom2 = 2 * sigma * sigma;
        int i = 0;
        float centre = size / 2;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                float xOffset = x - centre;
                float yOffset = y - centre;
                float distance = (xOffset * xOffset + yOffset * yOffset);
                array[i] = (float) (Math.exp(-distance / denom2) / denom1);
                i++;
            }
        }


        // Normalize the kernel values
        float sum = 0;
        for (float value : array) {
            sum += value;
        }
        for (int j = 0; j < array.length; j++) {
            array[j] /= sum;
        }

        Kernel kernel = new Kernel(size, size, array);

        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(
                input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        // And we're done
        return output;
    }
}
