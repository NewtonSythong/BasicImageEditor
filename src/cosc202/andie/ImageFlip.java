package cosc202.andie;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

/**
 * This class represents an operation that flips an image.
 * It implements the ImageOperation interface and is serializable.
 */
public class ImageFlip implements ImageOperation, java.io.Serializable {
    
    // Flag to determine if the flip is horizontal or vertical
    private boolean horizontal;
    
    /**
     * Constructor for the ImageFlip class.
     * @param horizontal A boolean that determines if the flip is horizontal or vertical.
     */
    public ImageFlip(boolean horizontal) {
        this.horizontal = horizontal;
    }
    
    /**
     * This method applies the flip operation to an image.
     * @param input The input image.
     * @return The output image after the flip operation.
     */
    public BufferedImage apply(BufferedImage input) {
    try{
        if(input !=null){
    
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
            }else{
        throw new NullPointerException();
    }
  }catch ( NullPointerException e){
        JOptionPane.showMessageDialog(null, "Please select an image file before trying to flip image");
return null;
  }
    }
}

