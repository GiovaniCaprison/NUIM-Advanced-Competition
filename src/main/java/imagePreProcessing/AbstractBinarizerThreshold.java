package imagePreProcessing;

/**
 * Abstract class that extends GrayscaleBinarizer to apply a global threshold for image binarization.
 * This class provides a template method for binarizing images using a global threshold determined by subclasses.
 * It outlines the basic flow of converting an image to grayscale, calculating a threshold, and applying this threshold to binarize the image.
 * <p>
 * Subclasses should implement the generateThreshold method to define how the binarization threshold is calculated.
 *
 * @see InterfaceContractForBinarization for the contract defining the binarization process.
 * @see GreyscaleImageConverter for the base grayscale conversion functionality.
 * @see OtsuBinarizerTechnique for an implementation example using Otsu's method.
 * Author: Louis Grennell
 * Date: 2024-02-24
 */
public abstract class AbstractBinarizerThreshold extends GreyscaleImageConverter {

    /**
     * Initializes a new instance of AbstractBinarizerThreshold with an option for linear processing in grayscale conversion.
     * @param enableLinearProcessing Indicates whether linear processing is enabled during grayscale conversion.
     */
    public AbstractBinarizerThreshold(boolean enableLinearProcessing) {
        super(enableLinearProcessing);
    }

    /**
     * Implements the binarization process by applying a global threshold to the grayscale image.
     * This method converts the original pixel values into either black or white based on the calculated threshold, producing a binary image.
     *
     * @param originalPixelImagery Array of original image pixels in grayscale.
     * @param binarizedPixelImagery Array where the binarized pixel data will be stored.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    @Override
    protected void binarizeImage(byte[] originalPixelImagery, byte[] binarizedPixelImagery, int width, int height) {
        int totalPixels = width * height;
        int threshold = generateThreshold(originalPixelImagery, width, height);

        for (int i = 0; i < totalPixels; i++) {
            binarizedPixelImagery[i] = (byte) ((originalPixelImagery[i] & 0xFF) <= threshold ? 0x00 : 0xFF);
        }
    }

    /**
     * Abstract method to be implemented by subclasses to calculate the global threshold for binarization.
     * The threshold determination algorithm should consider the entire image's pixel values.
     *
     * @param pixels Array of grayscale image pixels.
     * @param width The width of the image.
     * @param height The height of the image.
     * @return The calculated threshold value for binarization.
     */
    protected abstract int generateThreshold(byte[] pixels, int width, int height);
}


