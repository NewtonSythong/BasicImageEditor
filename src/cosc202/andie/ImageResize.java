package cosc202.andie;
import java.awt.*;
import java.awt.image.*;

import javax.swing.JOptionPane;

/**
 * Class to handle the resizing of an image as an ImageOperation.
 * This class implements the ImageOperation interface and is serializable
 * so that the operation can be saved and restored as part of the application state.
 */
public class ImageResize implements ImageOperation, java.io.Serializable {
    private double scaleFactor; // The scale factor for resizing

     /**
     * Constructor for the image resizing operation.
     * @param scaleFactor The scaling factor to resize the image by.
     */
    public ImageResize(double scaleFactor){
        this.scaleFactor = scaleFactor;
    }

    /**
     * Applies the resize operation to a given image.
     * This can either enlarge or shrink the image based on the scaleFactor provided.
     * @param input The BufferedImage to be resized.
     * @return The resized BufferedImage.
     */
    public BufferedImage apply(BufferedImage input) {
        try{
            if(input != null){
        //Calculating the new dimensions based on the scaleFactor
        int width = input.getWidth();
        int height = input.getHeight();

        int newHeight = (int) (height * scaleFactor / 100);
        int newWidth = (int) (width * scaleFactor / 100);
        
        // Choose the scaling algorithm based on whether we are scaling down or scaling up.
        Image img;
        // Use SCALE_AREA_AVERAGING for downsizing to maintain image quality.
        if (scaleFactor/100 <= 1) img = input.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
        // Use SCALE_SMOOTH for upsizing, which uses bilinear interpolation.
        else img = input.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Create a new BufferedImage to hold the resized image.
        input = new BufferedImage(newWidth, newHeight, input.getType());

        // Use a Graphics2D object to draw the resized image onto the new BufferedImage.
        Graphics2D g = input.createGraphics();

        g.drawImage(img, 0, 0, null);
        g.dispose();

        return input; //returns the resized image
        }else{
        throw new NullPointerException();
    }
  }catch ( NullPointerException e){
        JOptionPane.showMessageDialog(null, "Please select a image file before trying to resize");
return null;
  }
}
}