package imagePreProcessing;

/**
 * Implements the Otsu binarization method extending the AbstractBinarizerThreshold class.
 * The Otsu method is an automatic thresholding technique that determines the threshold by minimizing
 * intra-class variance or, equivalently, by maximizing inter-class variance.
 * <p>
 * This class overrides the generateThreshold method to apply the Otsu algorithm on the given image pixels
 * and determine the optimal threshold for binarization.
 * <p>
 * @see AbstractBinarizerThreshold for the base class providing common thresholding functionality.
 * @see GreyscaleImageConverter for the base grayscale conversion functionality.
 * @see InterfaceContractForBinarization for the contract defining the binarization process.
 * Author: Louis Grennell
 * Date: 2024-02-24
 */
public class OtsuBinarizerTechnique extends AbstractBinarizerThreshold {

    /**
     * Constructs an OtsuBinarizer instance with the linear processing flag set to false as Otsu's method
     * does not require linear pre-processing of the image.
     */
    public OtsuBinarizerTechnique() {
        super(false);
    }

    /**
     * Calculates the optimal binarization threshold for an image using the Otsu's method.
     *
     * @param pixels The byte array of image pixels in grayscale.
     * @param width The width of the image.
     * @param height The height of the image.
     * @return The calculated threshold value.
     */
    @Override
    protected int generateThreshold(byte[] pixels, int width, int height) {
        int totalPixels = width * height;
        int[] histogram = createHistogram(pixels, totalPixels);
        return calculateOtsuThreshold(histogram);
    }

    /**
     * Determines the best threshold by maximizing the inter-class variance, which is equivalent to minimizing
     * the intra-class variance.
     *
     * @param histogram The histogram of grayscale pixel intensities.
     * @return The optimal threshold value as determined by Otsu's method.
     */
    private int calculateOtsuThreshold(int[] histogram) {
        double sum = 0, sumB = 0, varMax = 0;
        int threshold = 0;
        int totalSum = 0, backgroundWeight = 0;

        for (int i = 0; i < 256; i++) {
            sum += i * histogram[i];
            totalSum += histogram[i];
        }

        for (int t = 0; t < 255; t++) {
            backgroundWeight += histogram[t];
            if (backgroundWeight == 0) continue;
            int foregroundWeight = totalSum - backgroundWeight;
            if (foregroundWeight == 0) break;

            sumB += t * histogram[t];
            double meanBackground = sumB / backgroundWeight;
            double meanForeground = (sum - sumB) / foregroundWeight;

            double varBetween = (double) backgroundWeight * foregroundWeight * (meanBackground - meanForeground) * (meanBackground - meanForeground);
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = t;
            }
        }
        return threshold;
    }

    /**
     * Generates a histogram from the grayscale pixel data.
     *
     * @param pixels The byte array of grayscale image pixels.
     * @param length The number of pixels in the array.
     * @return An array representing the histogram of pixel intensities.
     */
    private static int[] createHistogram(byte[] pixels, int length) {
        int[] histogram = new int[256];
        for (int i = 0; i < length; i++) {
            int pixelValue = pixels[i] & 0xFF;
            histogram[pixelValue]++;
        }
        return histogram;
    }
}

