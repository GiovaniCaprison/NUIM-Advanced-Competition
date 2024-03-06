package Unused_SELF_MADE_Algorithm_For_imagePreProcessing;

import java.awt.image.BufferedImage;

/**
 * Defines a contract for binarizing images. This interface is intended to be implemented by classes that
 * specialize in converting color or grayscale images to binary (black and white) images.
 * <p>
 * Binarization is a crucial preprocessing step in various image processing applications, such as optical
 * character recognition (OCR), document scanning, and image analysis. Implementing classes are expected to
 * provide their own binarization algorithms, tailored to specific needs or optimizations.
 * <p>
 * Implementers of this interface include:
 * - GrayscaleBinarizer: A base class for grayscale followed by binarization process.
 * - OtsuBinarizer: Implements the Otsu's method for binarization.
 * - AbstractBinarizerThreshold: A base class providing common thresholding functionality for subclasses.
 * <p>
 * @see GreyscaleImageConverter for the base grayscale conversion functionality.
 * @see OtsuBinarizerTechnique for an implementation example using Otsu's method.
 * @see AbstractBinarizerThreshold for an example of a subclass that applies a global threshold for binarization.
 * Author: Louis Grennell
 * Date: 2024-02-24
 */
public interface InterfaceContractForBinarization {

    /**
     * Converts the provided image to a binary format. The conversion process should result in an image where
     * pixels are either black or white, based on the implementing class's binarization algorithm.
     *
     * @param imageToBinarize The BufferedImage to be converted into binary format. This image can be in color
     * or grayscale before binarization.
     * @return A BufferedImage that has been converted to binary format, adhering to the binarization algorithm
     * implemented by the class.
     */
    BufferedImage binarize(BufferedImage imageToBinarize);
}

