import {useRef, useState, useEffect, useCallback, useContext} from 'react';
import Webcam from 'react-webcam';
import { BrowserMultiFormatReader } from '@zxing/library';
import {sendBarcodeEntryToBackend, sendBarcodeToBackend} from '../services/apiService'; // Import the service
import { alpha, Typography, Button, Box } from '@mui/material';
import { UserContext } from '@/context/UserContext';

const NutriBarcode = () => {
    const webcamRef = useRef<Webcam>(null);
    const [barcode, setBarcode] = useState<string>('');
    const [lastSentBarcode, setLastSentBarcode] = useState<string>('');
    const [isScanning, setIsScanning] = useState<boolean>(false);
    const context = useContext(UserContext); // Declare context here

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
                                // Use context here
                                sendBarcodeEntryToBackend({ barcode: decodedBarcode, response, userEmail: context.userEmail })
                                    .then(() => {
                                        console.log('Food diary sent successfully');
                                        console.log('poopoo' + context.userEmail);
                                    })
                                    .catch((error: any) => {
                                        console.error('Failed to send food diary', error);
                                        console.log("jiasd" + context.userEmail);
                                    });
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
    }, [lastSentBarcode, context]);

    useEffect(() => {
        if (isScanning) { // Add this line
            const interval = setInterval(() => {
                capture();
            }, 1000); // Capture an image every second
            return () => clearInterval(interval);
        } // Add this line
    }, [capture, isScanning]); // Add isScanning to the dependency array

    return (
        <Box
            id="hero"
            sx={(theme) => ({
                width: '100%',
                display: 'flex', // Center the content
                alignItems: 'center', // Center vertically
                justifyContent: 'center', // Center horizontally
                flexDirection: 'column', // Stack items vertically
                textAlign: 'center', // Center text horizontally
                padding: theme.spacing(4), // Add padding
                backgroundImage:
                    theme.palette.mode === 'light'
                        ? 'linear-gradient(180deg, #CEE5FD, #FFF)'
                        : `linear-gradient(#02294F, ${alpha('#090E10', 0.0)})`,
                backgroundSize: '100% 20%',
                backgroundRepeat: 'no-repeat',
                height: '100vh', // Fill the viewport height
            })}
        >
            <Typography variant="h2" gutterBottom>
                Scan your food!
            </Typography>
            <Typography variant="h5" gutterBottom>
                Point your camera at the barcode.
            </Typography>
            {isScanning && ( // Add this line
                <Webcam
                    audio={false}
                    ref={webcamRef}
                    screenshotFormat="image/jpeg"
                    videoConstraints={{
                        facingMode: "environment"
                    }}
                />
            )}
            {barcode && <p>Barcode Detected: {barcode}</p>}
            <Button variant="contained" color="primary" size="large" onClick={() => setIsScanning(!isScanning)}>
                {isScanning ? 'Stop Scanning' : 'Start Scanning'}
            </Button>
        </Box>
    );
};

export default NutriBarcode;