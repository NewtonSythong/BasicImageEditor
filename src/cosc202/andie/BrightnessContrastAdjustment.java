package cosc202.andie;
import java.awt.image.*;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to adjust the brightness values of an image
 * </p>
 * 
 * <p>
 * Short description of how the filter/adjustments work
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Katrina Hogg
 * @version 1.0
 */
public class BrightnessContrastAdjustment implements ImageOperation, java.io.Serializable{
    
    /**
     * <p>
     * Create a new BrightnessAdjustment operation.
     * </p>
     */
    BrightnessContrastAdjustment() {

    }

    /**
     * <p>
     * Short description
     * </p>
     * 
     * <p>
     * More detailed decription
     * </p>
     * 
     * @param input The image to have its brightness adjusted
     * @return The resulting greyscale image.
     */
    public BufferedImage apply(BufferedImage input) {
        try{
          if(input != null){
              for (int y = 0; y < input.getHeight(); ++y) {
                  for (int x = 0; x < input.getWidth(); ++x) {
                      int argb = input.getRGB(x, y);
                      int a = (argb & 0xFF000000) >> 24;
                      int r = (argb & 0x00FF0000) >> 16;
                      int g = (argb & 0x0000FF00) >> 8;
                      int b = (argb & 0x000000FF);
      
                      int grey = (int) Math.round(0.3*r + 0.6*g + 0.1*b);
      
                      argb = (a << 24) | (grey << 16) | (grey << 8) | grey;
                      input.setRGB(x, y, argb);
                  }
              }
              
              return input;
          }else{
              throw new NullPointerException();
          }
        }catch ( NullPointerException e){
              JOptionPane.showMessageDialog(null, "Please select an image file before trying to adjust the brightness and contrast");
      return null;
        }

    }
}
