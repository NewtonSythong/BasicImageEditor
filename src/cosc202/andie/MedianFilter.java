// package cosc202.andie;

// import java.awt.image.BufferedImage;
// import java.util.Arrays;

// public class MedianFilter {
//     /**
//      * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
//      */
//     private int radius;

//     MedianFilter(int radius) {
// this.radius = radius;
//     }


//     /**
//      * <p>
//      * Construct a Mean filter with the default size.
//      * </p
//      * >
//      * <p>
//      * By default, a Mean filter has radius 1.
//      * </p>
//      * 
//      * @see MeanFilter(int)
//      */
//     MedianFilter() {
//         this(1);
//     }
//     /**
//      * <p>
//      * Apply median filter conversion to an image.
//      * </p>
//      * 
//      * <p>
//      * The conversion from red, green, and blue values to a median colour, uses a
//      * weighted average of RGB
//      * </p>
//      * 
//      * @param input The image to be converted to greyscale
//      * @return The resulting greyscale image.
//      */

//     public BufferedImage apply(BufferedImage input) {
//         int size = (2*radius+1) * (2*radius+1);
//         for (int y = 0; y < input.getHeight(); ++y) {
//             for (int x = 0; x < input.getWidth(); ++x) {
//                 int argb = input.getRGB(x, y);
//                 int a = (argb & 0x00FF0000) >> 24;
//                 int r = (argb & 0x00FF0000) >> 16;
//                 int g = (argb & 0x0000FF00) >> 8;
//                 int b = (argb & 0x000000FF);
//                 int medianArray[] = { a, r, g, b };
                
//                 input.setRGB(x, y, medianValue(medianArray));
               
//             }
           
//         }
        
//         BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null))
//         return output;
//     }

//     public int medianValue(int[] unsortedArray) {
//         Arrays.sort(unsortedArray);
//         int midpoint = unsortedArray[unsortedArray.length / 2];
//         return midpoint;
//     }

// }
