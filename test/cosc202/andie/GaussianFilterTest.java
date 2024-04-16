package cosc202.andie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class GaussianFilterTest {
    @Test
    public void Test() {}

    @Test 
    public void GaussianFilterTest1() {
        Assertions.assertEquals(1.432, GaussianBlur.gaussian(0, 0, 1.0f/3.0f), 0.001);
    }

    @Test 
    public void GaussianFilterTest2() {
        Assertions.assertEquals(0.016, GaussianBlur.gaussian(-1, 0, 1.0f/3.0f), 0.001);
    }

    @Test 
    public void GaussianFilterTest3() {
        Assertions.assertEquals(0.00, GaussianBlur.gaussian(-1, -1, 1.0f/3.0f), 0.001);
    }

    @Test
    public void normalisedGaussianTest1() {
        Assertions.assertEquals(0.957, GaussianBlur.normalisedGaussian(GaussianBlur.gaussian(0, 0, 1.0f/3.f), 1.496f), 0.001);
    }

    @Test
    public void normalisedGaussianTest2() {
        Assertions.assertEquals(0.011, GaussianBlur.normalisedGaussian(GaussianBlur.gaussian(-1, 0, 1.0f/3.f), 1.496f), 0.001);
    }

    @Test
    public void normalisedGaussianTest3() {
        Assertions.assertEquals(0.358, GaussianBlur.normalisedGaussian(GaussianBlur.gaussian(0, 0, 2.0f/3.f), 0.998f), 0.001);
    }
    
}
