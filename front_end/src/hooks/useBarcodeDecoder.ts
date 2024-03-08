import { useState, useCallback } from 'react';
import { BrowserMultiFormatReader } from '@zxing/library';
import { sendBarcodeToBackend } from '../services/apiBarcodeService'; // Import the service

export const useBarcodeDecoder = () => {
    const [barcode, setBarcode] = useState<string>('');
    const [lastSentBarcode, setLastSentBarcode] = useState<string>('');

    const decodeBarcode = useCallback((imageSrc: string | undefined) => {
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
                        })
                        .catch((error) => {
                            console.error('Failed to send barcode', error);
                        });
                }
            }).catch(() => {
                // Handle errors or not found cases
            });
        }
    }, [lastSentBarcode]);

    return { decodeBarcode, barcode };
};

