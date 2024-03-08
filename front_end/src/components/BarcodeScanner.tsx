import { useRef, useState, useEffect, useCallback } from 'react';
import Webcam from 'react-webcam';
import { BrowserMultiFormatReader } from '@zxing/library';
import { sendBarcodeToBackend } from '../services/apiService'; // Import the service

const BarcodeScanner = () => {
    const webcamRef = useRef<Webcam>(null);
    const [barcode, setBarcode] = useState<string>('');
    const [lastSentBarcode, setLastSentBarcode] = useState<string>('');

    const capture = useCallback(() => {
        if (webcamRef.current) {
            const imageSrc = webcamRef.current.getScreenshot();
            if (imageSrc) {
                const codeReader = new BrowserMultiFormatReader();
                codeReader.decodeFromImage(undefined, imageSrc).then((result) => {
                    const decodedBarcode = result.getText();
                    setBarcode(decodedBarcode);
                    if (decodedBarcode !== lastSentBarcode) {
                        sendBarcodeToBackend({barcode: decodedBarcode})
                            .then((response) => {
                                console.log('Barcode sent successfully', response);
                                setLastSentBarcode(decodedBarcode); // Update last sent barcode
                            })
                            .catch((error) => {
                                console.error('Failed to send barcode', error);
                            });
                    }
                }).catch(() => {
                    // Handle errors or not found cases
                });
            }
        }
    }, [lastSentBarcode]);

    useEffect(() => {
        const interval = setInterval(() => {
            capture();
        }, 1000); // Capture an image every second
        return () => clearInterval(interval);
    }, [capture]);

    return (
        <>
            <Webcam
                audio={false}
                ref={webcamRef}
                screenshotFormat="image/jpeg"
                videoConstraints={{
                    facingMode: "environment"
                }}
            />
            {barcode && <p>Barcode Detected: {barcode}</p>}
        </>
    );
};

export default BarcodeScanner;



