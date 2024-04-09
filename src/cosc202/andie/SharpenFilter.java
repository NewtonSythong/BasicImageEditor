package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to apply a sharpen filter.
 * </p>
 * 
 * <p>
 * A sharpen filter enhances the color contrast around edges in the image 
 * by enhancing the differences between neighbouring pixel values
 * </p>
 * 
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {

    /**
     * Sharpen filter constructor
     */
    SharpenFilter() {
        // construction Code
    }

    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as a 9-element array
        float[] array = { 0, -1 / 2.0f, 0,
                        -1 / 2.0f, 3, -1 / 2.0f,
                        0, -1 / 2.0f, 0 };

        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);
try{
    if(input !=  null){
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
    JOptionPane.showMessageDialog(null, "Please select image before filter");
return null;
}
 
    }
}
