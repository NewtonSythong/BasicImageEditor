package cosc202.andie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ImagePanelTest {
    @Test
    public void test(){
    }
    
    @Test
    public void getZoomInitialValue() {
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100.0, testPanel.getZoom());
    }

    @Test
    public void getZoomAfterSetZoom() {
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(0.0);
        Assertions.assertFalse(testPanel.getZoom() == 100.0);
        Assertions.assertTrue(testPanel.getZoom() >= 50.0);
    }
    
    

}
