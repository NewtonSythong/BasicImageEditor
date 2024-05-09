package cosc202.andie;

import javax.swing.JOptionPane;
import java.awt.image.*;
import java.util.ResourceBundle;

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
 * 
 * @author Katrina Hogg
 * @version 1.0
 */
public class CycleColour implements ImageOperation, java.io.Serializable{

    /**
     * The user requested order of the colour channels
     */
     private String config;
    
     /**
      * <p>
      * Construct colour cycle with user input configuration
      * </p
      * >
     */
     CycleColour(String config) {
         this.config = config;
     }
 
     /**
      * 
      * <p>
      * Construct colour cycle with default configuration
      * </p
      * >
      * <p>
      * By default, the colour channel order is RGB
      * </p>
      * 
      * @see CycleColour(String)
      * 
      */
      CycleColour() {
         this("RGB");
     }

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

                if(config.equals("RBG")){
                    //swaps green and blue placement
                    argb = (a << 24) | (r << 16 ) | (b << 8) | g;
                } else if (config.equals("GRB")) {
                    //swaps red and green placement
                    argb = (a << 24) | (g << 16 ) | (r << 8) | b;
                } else if (config.equals("GBR")) {
                    //gives you lab book GBR example
                    argb = (a << 24) | (b << 16 ) | (r << 8) | g;
                } else if (config.equals("BGR")) {
                    //swaps red and blue placement
                    argb = (a << 24) | (b << 16 ) | (g << 8) | r;
                } else if (config.equals("BRG")) {
                    //gives you lab book BRG exmaple
                    argb = (a << 24) | (g << 16 ) | (b << 8) | r;
                }

                input.setRGB(x, y, argb);

            }
        }
        
        return input;



    }else{
        throw new NullPointerException();
    }
}catch(NullPointerException e){
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
    JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
    return null;
}
    }
}