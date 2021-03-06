package pl.edu.uj.kimage.plugin.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {

    private static final Color RED = new Color(255, 0, 0);
    private static final Color GREEN = new Color(0, 255, 0);
    private static final Color BLUE = new Color(0, 0, 255);
    private static final int IMAGE_MIN_WIDTH = 1;
    private static final int IMAGE_MIN_HEIGHT = 1;
    private static final int FIRST_PIXEL_Y = 0;
    private static final int FIRST_PIXEL_X = 0;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void sizeAccessorsReturnValidValues() {
        // given
        int imageWidth = 2;
        int imageHeight = 2;
        Color[] colors = new Color[]{RED, GREEN, BLUE, RED};

        // when
        Image image = new Image(imageWidth, imageHeight, colors);

        // then
        assertThat(image.getWidth()).isEqualTo(imageWidth);
        assertThat(image.getHeight()).isEqualTo(imageHeight);
    }

    @Test
    public void imageColorAccessorReturnValidValues() {
        // given
        Color[] colors = new Color[]{new Color(RED.getRed(), RED.getGreen(), RED.getBlue())};
        Image image = new Image(IMAGE_MIN_WIDTH, IMAGE_MIN_HEIGHT, colors);

        // when
        Color returnedColor = image.getColor(FIRST_PIXEL_X, FIRST_PIXEL_Y);

        // then
        assertThat(returnedColor.getRed()).isEqualTo(RED.getRed());
        assertThat(returnedColor.getGreen()).isEqualTo(RED.getGreen());
        assertThat(returnedColor.getBlue()).isEqualTo(RED.getBlue());
    }

    @Test
    public void imageColorAccessorReturnClampedValues() {
        // given
        int redChannel = 0;
        int greenChannel = -25;
        int blueChannel = 280;
        Color[] colors = new Color[]{new Color(redChannel, greenChannel, blueChannel)};
        Image image = new Image(IMAGE_MIN_WIDTH, IMAGE_MIN_HEIGHT, colors);

        // when
        Color returnedColor = image.getColor(FIRST_PIXEL_X, FIRST_PIXEL_Y);

        // then
        assertThat(returnedColor.getRed()).isEqualTo(BLUE.getRed());
        assertThat(returnedColor.getGreen()).isEqualTo(BLUE.getGreen());
        assertThat(returnedColor.getBlue()).isEqualTo(BLUE.getBlue());
    }
}