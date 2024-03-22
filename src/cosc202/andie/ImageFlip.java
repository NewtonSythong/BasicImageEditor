package cosc202.andie;

import java.awt.image.BufferedImage;

public class ImageFlip implements ImageOperation, java.io.Serializable {
    
    private boolean horizontal;
    

    public ImageFlip(boolean horizontal) {
        this.horizontal = horizontal;
    }
    
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, input.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (horizontal) {
                    output.setRGB(width - i - 1, j, input.getRGB(i, j));
                } else {
                    output.setRGB(i, height - j - 1, input.getRGB(i, j));
                }
            }
        }
        return output;
    }


}