import { useRef, useEffect } from 'react';
import Webcam from 'react-webcam';
import { useBarcodeDecoder } from '../hooks/useBarcodeDecoder'; // Import the custom hook

const BarcodeScanner = () => {
    const webcamRef = useRef<Webcam>(null);
    const { decodeBarcode, barcode } = useBarcodeDecoder();

    useEffect(() => {
        const interval = setInterval(() => {
            const imageSrc = webcamRef.current?.getScreenshot();
            decodeBarcode(imageSrc);
        }, 1000); // Capture an image every second

        return () => clearInterval(interval);
    }, [decodeBarcode]);

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





