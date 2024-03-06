package Unused_SELF_MADE_Algorithm_For_imagePreProcessing;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for converting color images to grayscale and then binarizing them.
 * This serves as a base class that provides a framework for the conversion and binarization process,
 * allowing for the implementation of various binarization techniques by extending this class.
 * <p>
 * To implement a specific binarization technique, subclasses should override the `binarizeImage` method.
 * <p>
 * The class also includes a static block to configure the grayscale conversion operation,
 * prioritizing speed and disabling dithering for performance enhancement.
 * <p>
 * @see InterfaceContractForBinarization for the contract defining the binarization process.
 * @see OtsuBinarizerTechnique for an example of a binarization technique which by default will need a grayscale image.
 * @see AbstractBinarizerThreshold for an example of a subclass that applies a global threshold for binarization.
 * Author: Louis Grennell
 * Date: 2024-02-24
 */
public abstract class GreyscaleImageConverter implements InterfaceContractForBinarization {

    // Grayscale conversion operation configuration
    private static final ColorConvertOp COLOR_CONVERT_OP_TO_GRAYSCALE;
    static {
        Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
        renderingHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
        renderingHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        COLOR_CONVERT_OP_TO_GRAYSCALE = new ColorConvertOp(new RenderingHints(renderingHints));
    }

    // Indicates whether linear processing is applied during grayscale conversion
    private final boolean isLinearProcessingEnabled;

    /**
     * Constructor to specify the grayscale conversion mode.
     * <p>
     * We use Otsu's method for binarization, which does not require linear processing.
     * However, for other binarization methods, linear processing may be beneficial.
     * so we include a flag to enable or disable linear processing incase it is needed in the future.
     *
     * @param isLinearProcessingEnabled Flag to enable linear processing during grayscale conversion.
     *
     */
    public GreyscaleImageConverter(boolean isLinearProcessingEnabled) {
        this.isLinearProcessingEnabled = isLinearProcessingEnabled;
    }

    /**
     * Converts the provided image to grayscale based on the specified mode (linear or default),
     * then binarizes it using the subclass implementation of `binarizeImage`.
     *
     * @param sourceImage The BufferedImage to be converted and binarized.
     * @return A new BufferedImage that has been converted to grayscale and binarized.
     */
    public BufferedImage binarize(BufferedImage sourceImage) {
        BufferedImage grayscaleImage = convertToGrayscale(sourceImage, isLinearProcessingEnabled);
        BufferedImage binarizedImage = new BufferedImage(grayscaleImage.getWidth(), grayscaleImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        byte[] sourcePixels = ((DataBufferByte) grayscaleImage.getRaster().getDataBuffer()).getData();
        byte[] targetPixels = ((DataBufferByte) binarizedImage.getRaster().getDataBuffer()).getData();

        binarizeImage(sourcePixels, targetPixels, grayscaleImage.getWidth(), grayscaleImage.getHeight());
        return binarizedImage;
    }

    /**
     * Converts the given image to grayscale. If linear processing is enabled, uses the configured ColorConvertOp.
     * Otherwise, performs a manual conversion emphasizing speed and simplicity.
     *
     * @param image The image to convert to grayscale.
     * @param isLinear If true, linear processing is applied; otherwise, a default conversion is used.
     * @return A grayscale BufferedImage.
     */
    public static BufferedImage convertToGrayscale(BufferedImage image, boolean isLinear) {
        if (image.getType() == BufferedImage.TYPE_BYTE_GRAY) {
            return image; // Already grayscale
        }

        BufferedImage grayscaleImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        if (isLinear) {
            COLOR_CONVERT_OP_TO_GRAYSCALE.filter(image, grayscaleImage);
        } else {
            applyCustomGrayscaleConversion(image, grayscaleImage);
        }
        return grayscaleImage;
    }

    /**
     * Applies a custom algorithm for converting an image to grayscale.
     * This method manually calculates the grayscale value for each pixel, emphasizing performance and simplicity.
     *
     * @param sourceImage The source BufferedImage for conversion.
     * @param targetImage The target BufferedImage for the grayscale data.
     */
    private static void applyCustomGrayscaleConversion(BufferedImage sourceImage, BufferedImage targetImage) {
        byte[] targetPixels = ((DataBufferByte) targetImage.getRaster().getDataBuffer()).getData();
        // Weight factors for converting color to grayscale
        final int weightRed = 316;
        final int weightGreen = 624;
        final int weightBlue = 84;
        final int divisor = weightRed + weightGreen + weightBlue;

        for (int row = 0; row < sourceImage.getHeight(); row++) {
            for (int col = 0; col < sourceImage.getWidth(); col++) {
                int rgb = sourceImage.getRGB(col, row);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                targetPixels[row * sourceImage.getWidth() + col] = (byte) ((red * weightRed + green * weightGreen + blue * weightBlue) / divisor);
            }
        }
    }

    /**
     * Subclasses must implement this method to define their specific binarization algorithm.
     * This method directly manipulates raw pixel arrays for efficiency.
     *
     * @param sourcePixels The pixel data from the source image, already converted to grayscale.
     * @param targetPixels The pixel data for the target image, where the binarization result will be stored.
     * @param width The width of the images.
     * @param height The height of the images.
     */
    protected abstract void binarizeImage(byte[] sourcePixels, byte[] targetPixels, int width, int height);
}
