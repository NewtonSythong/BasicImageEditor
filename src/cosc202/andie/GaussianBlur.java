package cosc202.andie;

import java.awt.image.*;

public class GaussianBlur implements ImageOperation, java.io.Serializable {

    private int radius;

    GaussianBlur() {
        this(1);
    }

    GaussianBlur(int radius) {
        this.radius = radius;
    }

    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as a 9-element array
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
