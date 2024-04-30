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
     * The user requested percentage brightness and contrast change
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

                      //the equation is right, something about the scaling of the nmber input isnt right though i think
                      //like to make things positive seems like i have to make it 250
                      //otherwise if i do that on the negative it does it way too intensley, screen goes black

                      //having the value go in like this doesnt necessarily mean percent??, thatll be whats messing it up??

                      //turn the brightness and contrast values into the percentages?
                      //or does the contrast/100 take care of that
                      //the plus one makes it 125%?
                        
                      int newR = limit((int) Math.round((1+(contrast/100)) * (r-127.5) + 127.5 * (1+(brightness/100))));
                      int newG = limit((int) Math.round((1+(contrast/100)) * (g-127.5) + 127.5 * (1+(brightness/100))));
                      int newB = limit((int) Math.round((1+(contrast/100)) * (b-127.5) + 127.5 * (1+(brightness/100))));

                      argb = (a << 24) | (newR << 16) | (newG << 8) | newB;
                      input.setRGB(x, y, argb);

                      //each colour (red green blue) has a range of 225
                      //need to apply the equation to each colour value
                      //check each colour is within 0-255
                      //if below zero keep it zero?
                      ///if above 255 keep 255?

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

    /**
     * 
     */
    private static int limit(int num) {
        return Math.max(0, Math.min(255, num));
    }
}
