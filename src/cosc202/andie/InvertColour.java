package cosc202.andie;

import java.awt.image.*;

/**
 * Class
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
     * Write descriptor of logic behind image colour inversion
     * </p>
     * 
     * @param input The image to have its colour values inverted
     * @return The resulting inverted colour image
     */
    public BufferedImage apply(BufferedImage input) {
  
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
    }



}