package cosc202.andie;

import java.awt.image.*;

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

    //change
    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as a 9-element array
        float[] array = {0.000f, 0.011f, 0.000f,
                    0.011f, 0.957f, 0.011f,
                    0.000f, 0.011f, 0.000f
                };
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        // And we're done
        return output;
    }
}
