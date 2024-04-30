package cosc202.andie;

import java.io.Serializable;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a rectangle on the image.
 * </p> 
 * 
 * <p>
 * This class draws a rectangle on the image. 
 * It may either draw a filled rectangle or draw an outlined rectangle.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Tele Tamati
 */
public class DrawRect implements ImageOperation, Serializable {

    public BufferedImage apply(BufferedImage input){
        return input;
    }    
}
   

