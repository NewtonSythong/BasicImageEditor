package cosc202.andie;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

/**
 * This class represents an operation that rotates an image.
 * It implements the ImageOperation interface and is serializable.
 */
public class ImageRotation implements ImageOperation, java.io.Serializable {
    
    // Flag to determine if the rotation is clockwise or anti-clockwise
    private boolean clockwise;
    
    /**
     * Constructor for the ImageRotation class.
     * @param clockwise A boolean that determines if the rotation is clockwise or anti-clockwise.
     */
    ImageRotation(boolean clockwise) {
        this.clockwise = clockwise;
    }
    
    /**
     * This method applies the rotation operation to an image.
     * @param input The input image.
     * @return The output image after the rotation operation.
     */
    public BufferedImage apply(BufferedImage input) {
        try{
            if(input != null){  
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
    }else{
        throw new NullPointerException();
    }
      }catch ( NullPointerException e){
        JOptionPane.showMessageDialog(null, "Please select a image file before trying to rotate");
return null;
  }
    }
}