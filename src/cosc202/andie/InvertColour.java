package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to convert an image from colour to greyscale.
 * </p>
 * 
 * <p>
 * The images produced by this operation are still colour images,
 * Inverting an image involves iterating over the pixels in the image 
 * and updating the red, green, and blue values to 255 minus their original values
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 */
public class InvertColour implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * Create a new InvertColour operation.
     * </p>
     */
    InvertColour() {

    }

    /**
     * <p>
     * Apply inversion conversion to an image.
     * </p>
     * 
     * <p>
     * Inverting an image involves iterating over the pixels in the image and updating the red, green, and
     * blue values to 255 minus their original values
     * </p>
     * 
     * @param input The image to have its colour values inverted
     * @return The resulting inverted colour image
     */
    public BufferedImage apply(BufferedImage input) {
        try{
            if(input != null){
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                int argb = input.getRGB(x, y);
                
                int a = ((argb) & 0xFF000000) >> 24; // the opacity of the colour
                int r = ((255-argb) & 0x00FF0000) >> 16 ;
                int g = ((255-argb) & 0x0000FF00) >> 8;
                int b = ((255-argb) & 0x000000FF);
                argb = (a << 24) | (r << 16 ) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        
         return input;
    }else{
        throw new NullPointerException();
    }
  }catch ( NullPointerException e){
        JOptionPane.showMessageDialog(null, "Please select an image file before trying to invert colour");
return null;
  }

    }

}