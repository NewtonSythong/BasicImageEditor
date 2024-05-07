package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;
import java.util.ResourceBundle;

/**
 * <p>
 * ImageOperation to apply a softening filter to an image
 * </p>
 * 
 * <p>
 * A soft blur filter decreases the unevenness between the pixels by averaging nearby pixels
 * Creating a blurry effect
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Katrina Hogg
 * @version 1.0
 */
public class SoftBlur implements ImageOperation, java.io.Serializable{
    /**
     * Soft blur filter constructor
     */
SoftBlur(){
    //construction Code
}

public BufferedImage apply (BufferedImage input){
    // The values for the kernel as a 9-element array
    float[] array = { 0, 1 / 8.0f, 0,
            1 / 8.0f, 1 / 2.0f, 1 / 8.0f,
            0, 1 / 8.0f, 0 };
    // Make a 3x3 filter from the array
    Kernel kernel = new Kernel(3, 3, array);
    // Apply this as a convolution - same code as in MeanFilter
    ConvolveOp convOp = new ConvolveOp(kernel);
try{
    if(input != null){
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
convOp.filter(input, output);
// And we're done
return output;
    }else{
        throw new NullPointerException();
    }
    } catch (NullPointerException e){
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
        return null;
    }
}

}

