package cosc202.andie;

import javax.swing.JOptionPane;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to convert an image from an RGB colour cycle to an alternative.
 * </p>
 * 
 * <p>
 * The images produced by this operation are still colour images,
 * Colour cycling an image involves swapping the colour channels around
 * and updating the red, green, and blue value ordering
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 */
public class CycleColour implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * Create a new CycleColour operation.
     * </p>
     */
    CycleColour(){}

     /**
     * <p>
     * Apply colour channel cycling to an image.
     * </p>
     * 
     * <p>
     * Colour cycling an image involves swapping the colour channels around
     * and updating the red, green, and blue value ordering
     * </p>
     * 
     * @param input The image to have its colour channels cycled
     * @return The resulting colour cycled image
     */
    public BufferedImage apply(BufferedImage input){
        try{
            if(input != null){
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {//cycles through each pixel

                int argb = input.getRGB(x, y);
                
                int a = (argb & 0xFF000000) >> 24;

                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF); 

                argb = (a << 24) | (b << 16 ) | (r << 8) | g;
                input.setRGB(x, y, argb);
                //gives you lab book GBR on first run

                //argb = (a << 24) | (g << 16 ) | (b << 8) | r; 
                //input.setRGB(x, y, argb);
                //gives you lab book BRG on first run


            }
        }
        
        return input;
    }else{
        throw new NullPointerException();
    }
}catch(NullPointerException e){
    JOptionPane.showMessageDialog(null, "Please select an image file before trying to cycle colours");
    return null;
}
    }
}