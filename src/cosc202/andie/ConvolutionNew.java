package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class ConvolutionNew {

    public static BufferedImage apply(BufferedImage input, float[] kernel, int radius) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, input.getType());

        int offset = 127;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                float r = 0.0f;
                float g = 0.0f;
                float b = 0.0f;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {

                        float kernelValue = kernel[(dx + radius) + (((radius * 2) + 1) * (dy + radius))];
                        int xVal = dx;
                        int yVal = dy;

                        if (x + xVal < 0 || x + xVal >= width - 1)
                            xVal = 0;
                        if (y + yVal < 0 || y + yVal >= height - 1)
                            yVal = 0;

                        Color currentColor = new Color(input.getRGB(x + xVal, y + yVal));
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