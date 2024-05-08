import {useRef, useState, useEffect, useCallback, useContext} from 'react';
import Webcam from 'react-webcam';
import { BrowserMultiFormatReader } from '@zxing/library';
import {sendBarcodeEntryToBackend, sendBarcodeToBackend} from '../services/apiService'; // Import the service
import { alpha, Typography, Button, Box, TextField } from '@mui/material';
import { UserContext } from '@/context/UserContext';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import IconButton from '@mui/material/IconButton';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';


const NutriBarcode = () => {
    const webcamRef = useRef<Webcam>(null);
    const [barcode, setBarcode] = useState<string>('');
    const [lastSentBarcode, setLastSentBarcode] = useState<string>('');
    const [isScanning, setIsScanning] = useState<boolean>(false);
    const [manualEntry, setManualEntry] = useState<boolean>(false);
    const context = useContext(UserContext); // Declare context here
    const theme = createTheme({
        palette: {
            mode: 'dark', // Change this to 'light' if you want a light theme
        },
    });

    const capture = useCallback(() => {
        if (webcamRef.current) {
            const imageSrc = webcamRef.current.getScreenshot();
            if (imageSrc) {
                const codeReader = new BrowserMultiFormatReader();
                codeReader.decodeFromImage(undefined, imageSrc).then((result) => {
                    const decodedBarcode = result.getText();
                    setBarcode(decodedBarcode);
                    if (decodedBarcode !== lastSentBarcode) {
                        sendBarcodeToBackend({ barcode: decodedBarcode })
                            .then((response) => {
                                console.log('Barcode sent successfully', response);
                                setLastSentBarcode(decodedBarcode); // Update last sent barcode
                                setBarcode(decodedBarcode); // Set the barcode
                                setTimeout(() => setBarcode(''), 10000); // Clear the barcode after 10 seconds

                                sendBarcodeEntryToBackend({ barcode: decodedBarcode, response: response})
                                    .then(() => {
                                        console.log('Barcode diary sent successfully');
                                        //console.log('poopoo' + context.userEmail);
                                    })
                                    .catch((error: any) => {
                                        console.error('Failed to send barcode diary', error);
                                        //console.log("jiasd" + context.userEmail);
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
    const handleManualSubmit = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/barcode', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    barcode: barcode
                }),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Received data:', data);
                setBarcode(barcode); // Set the barcode
                setTimeout(() => setBarcode(''), 10000); // Clear the barcode after 10 seconds
            } else {
                console.error('Failed to submit barcode');
            }
        } catch (error) {
            console.error('Error occurred during barcode submission:', error);
        }
    };
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline />
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
            <IconButton
                color="inherit"
                href="/NutriScan" // replace with your desired href
                aria-label="Go back"
                sx={{ position: 'absolute', top: 10, left: 10 }}
            >
            <ArrowBackIosIcon />
        </IconButton>
            <Typography variant="h2" gutterBottom>
                Scan your food!
            </Typography>
            <Typography variant="h5" gutterBottom>
                Point your camera at the barcode.
            </Typography>
            {isScanning && !manualEntry && (
                <Webcam
                    audio={false}
                    ref={webcamRef}
                    screenshotFormat="image/jpeg"
                    videoConstraints={{
                        facingMode: "environment"
                    }}
                />
            )}
            {manualEntry && !isScanning && (
                <Box
                    display="flex"
                    justifyContent="center"
                    alignItems="center"
                >
                    <TextField
                        margin="normal"
                        required
                        id="barcode"
                        label="Enter Barcode"
                        name="barcode"
                        autoFocus
                        value={barcode}
                        onChange={(e) => setBarcode(e.target.value)}
                        sx={{ width: '100%' }} // Adjust this value to change the width of the TextField
                    />
                </Box>
            )}
            {barcode && <p>Barcode Detected: {barcode}</p>}
            <Button variant="contained" color="primary" size="large" onClick={() => {
                setIsScanning(!isScanning);
                if (manualEntry) {
                    setManualEntry(false);
                }
            }}>
                {isScanning ? 'Stop Scanning' : 'Start Scanning'}
            </Button>
            <Box sx={{ margin: 1 }} /> {/* This will add a gap between the buttons */}
            <Button
                variant="contained"
                style={{backgroundColor: 'white', color: 'black'}}
                size="large"
                onClick={manualEntry ? handleManualSubmit : () => {
                    setManualEntry(!manualEntry);
                    if (isScanning) {
                        setIsScanning(false);
                    }
                }}
            >
                {manualEntry ? 'Submit' : 'Enter Manually'}
            </Button>
        </Box>
        </ThemeProvider>
    );
};

export default NutriBarcode;