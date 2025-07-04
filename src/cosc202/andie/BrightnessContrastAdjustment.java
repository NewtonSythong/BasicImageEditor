package cosc202.andie;

import java.awt.image.*;
import javax.swing.JOptionPane;
import java.util.ResourceBundle;

/**
 * <p>
 * ImageOperation to adjust the brightness and contrast values of an image
 * </p>
 * 
 * <p>
 * Images produced by this operation are colour images
 * The brightness and contrast of the image is decided by the user
 * The values used for adjustment are percentages
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
     * The user inputs percentage brightness and contrast change value
     */
    private int brightness;
    private int contrast;

    /**
     * <p>
     * Create a new BrightnessAdjustment operation.
     * </p>
     */
    BrightnessContrastAdjustment(int b, int c) {
        this.brightness = b;
        this.contrast = c;
    }


    /**
     * <p>
     * Apply brightness and contrast changes to image
     * </p>
     * 
     * <p>
     * An equation is used to individually adjust each red, green and blue
     * values by the user inputed brightness/contrast percentage change
     * </p>
     * 
     * @param input The image to have its brightness and or contrast adjusted
     * @return The resulting brightness/contrast adjusted image.
     */
    public BufferedImage apply(BufferedImage input) {
        try{
          if(input != null){
            BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
            input.isAlphaPremultiplied(), null);
              for (int y = 0; y < input.getHeight(); ++y) {
                  for (int x = 0; x < input.getWidth(); ++x) {
                      int argb = input.getRGB(x, y);
                      int a = (argb & 0xFF000000) >> 24;
                      int r = (argb & 0x00FF0000) >> 16;
                      int g = (argb & 0x0000FF00) >> 8;
                      int b = (argb & 0x000000FF);
                        
                      int newR = limit((int) Math.round((1.0+(contrast/100.0)) * (r-127.5) + (127.5 * (1.0+(brightness/100.0)))));
                      int newG = limit((int) Math.round((1.0+(contrast/100.0)) * (g-127.5) + (127.5 * (1.0+(brightness/100.0)))));
                      int newB = limit((int) Math.round((1.0+(contrast/100.0)) * (b-127.5) + (127.5 * (1.0+(brightness/100.0)))));

                      argb = (a << 24) | (newR << 16) | (newG << 8) | newB;
                      output.setRGB(x, y, argb);
                  }
              }
              
              return output;
          }else{
              throw new NullPointerException();
          }
        }catch ( NullPointerException e){
            ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
            JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
            return null;
        }

    }

    /**
     * <p>
     * Method used to ensure caluclated pixel value is not
     * below 0 and does not exceed 255
     * <p>
     */
    private static int limit(int num) {
        return Math.max(0, Math.min(255, num));
    }
}
