package kimage.plugins.color;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * @author Krzysztof
 */
public class SepiaII extends Plugin {

    @Override
    public void process(Image imgIn, Image imgOut) {
        final int height = imgIn.getHeight();
        final int widht = imgIn.getWidth();

        for (int x = 0; x < widht; x++) {
            for (int y = 0; y < height; y++) {
                final int red = imgIn.getRed(x, y);
                final int green = imgIn.getGreen(x, y);
                final int blue = imgIn.getBlue(x, y);

                imgOut.setRGB(x, y,
                        Math.min((int) (0.393 * red + 0.769 * green + 0.189 * blue), 255),
                        Math.min((int) (0.349 * red + 0.686 * green + 0.168 * blue), 255),
                        Math.min((int) (0.272 * red + 0.534 * green + 0.131 * blue), 255));
            }
        }
    }
}
