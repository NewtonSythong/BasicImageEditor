package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This class implements a convolution operation on an image.
 * The convolution operation is applied to the image using a kernel.
 * 
 * @author Jenny Kim
 */
public class ConvolutionNew implements Serializable {

    /**
     * Applies the convolution operation to the input image with the specified
     * kernel and radius.
     * 
     * @param input  the input image
     * @param kernel the kernel to apply to the image
     * @param radius the radius of the kernel
     * @param offset the offset to add to the output image
     * @return the output image with the convolution operation applied
     */
    public static BufferedImage apply(BufferedImage input, float[] kernel, int radius, int offset) {

        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, input.getType());

        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {


                    float r = 0.0f;
                    float g = 0.0f;
                    float b = 0.0f;

                    for (int dy = -radius; dy <= radius; dy++) {
                        for (int dx = -radius; dx <= radius; dx++) {

                            float kernelValue = kernel[(dx + radius) + (((radius * 2) + 1) * (dy + radius))];
                            int xVal = Math.min(Math.max(x + dx, 0), width - 1);
                            int yVal = Math.min(Math.max(y + dy, 0), height - 1);

                            Color currentColor = new Color(input.getRGB(xVal, yVal));
                            r += currentColor.getRed() * kernelValue;
                            g += currentColor.getGreen() * kernelValue;
                            b += currentColor.getBlue() * kernelValue;
                        }
                    }

                    r += offset;
                    if (r < 0)
                        r = 0;
                    if (r > 255)
                        r = 255;

                    g += offset;
                    if (g < 0)
                        g = 0;
                    if (g > 255)
                        g = 255;

                    b += offset;
                    if (b < 0)
                        b = 0;
                    if (b > 255)
                        b = 255;

                    Color color = new Color(Math.round(r), Math.round(g), Math.round(b));
                    output.setRGB(x, y, color.getRGB());
                }
            }
        
        return output;
    }
}