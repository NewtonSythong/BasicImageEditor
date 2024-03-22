package cosc202.andie;

import java.awt.image.BufferedImage;

public class ImageRotation implements ImageOperation, java.io.Serializable {
    private boolean clockwise;

    ImageRotation(boolean clockwise) {
        this.clockwise = clockwise;
    }

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(height, width, input.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (clockwise) {
                    output.setRGB(height - j - 1, i, input.getRGB(i, j));
                } else {
                    output.setRGB(j, width - i - 1, input.getRGB(i, j));
                }
            }
        }
        return output;
    }
}
