package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;


public class EdgeHandling {

    BufferedImage input;

    public EdgeHandling(){}

    public BufferedImage addPadding(BufferedImage src, int radius) {
        int newWidth = src.getWidth() + 2 * radius;
        int newHeight = src.getHeight() + 2 * radius;
    
        BufferedImage paddedImage = new BufferedImage(newWidth, newHeight, src.getType());
    
        Graphics2D g = paddedImage.createGraphics();
        g.drawImage(src, radius, radius, null);
        g.dispose();
    
        return paddedImage;
    }

    public BufferedImage cropImage(BufferedImage src, int radius) {
        int newWidth = src.getWidth() - 2 * radius;
        int newHeight = src.getHeight() - 2 * radius;
    
        BufferedImage croppedImage = src.getSubimage(radius, radius, newWidth, newHeight);
    
        return croppedImage;
    }

    // crop out the original image width * height from the enlarged image
    public BufferedImage cropMiddle(BufferedImage src, int widthOriginal, int heightOriginal) {
        int startX = (src.getWidth() - widthOriginal) / 2;
        int startY = (src.getHeight() - heightOriginal) / 2;
        
        BufferedImage edgeImage = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        
        Graphics2D g = edgeImage.createGraphics();
        g.drawImage(src, 0, 0, null);
        g.clearRect(startX, startY, widthOriginal, heightOriginal);
        g.dispose();
        
        return edgeImage;
    }
    
    public BufferedImage addOriginalImage(BufferedImage edgeImage, BufferedImage original) {
        int startX = (edgeImage.getWidth() - original.getWidth()) / 2;
        int startY = (edgeImage.getHeight() - original.getHeight()) / 2;
        
        Graphics2D g = edgeImage.createGraphics();
        g.drawImage(original, startX, startY, null);
        g.dispose();
        
        return edgeImage;
    }
}
