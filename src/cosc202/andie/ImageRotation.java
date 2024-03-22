package cosc202.andie;

import java.awt.image.BufferedImage;

public class ImageRotation implements ImageOperation, java.io.Serializable {
    ImageRotation() {
        // Contructor
    }

    public BufferedImage apply(BufferedImage input) {
        int getWidth = input.getWidth();
        int getHight = input.getHeight();
        BufferedImage output = new BufferedImage(getHight, getWidth, input.getType());
        for (int i = 0; i < getWidth; i++) {
            for (int j = 0; j < getHight; j++) {
                output.setRGB(j, getWidth - i - 1, input.getRGB(i, j));
            }
        }
    
        return output;

/*Hello Newton, You should probably finish off unit testing and create a new menu tab for rotating and flipping image */
        
    }


}
