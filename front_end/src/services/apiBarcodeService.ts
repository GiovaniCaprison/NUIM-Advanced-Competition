const BASE_URL = 'http://localhost:8080';
export const sendBarcodeToBackend = async ({barcode}: { barcode: any }) => {
    try {
        const response = await fetch(`${BASE_URL}/api/barcode`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ barcode }),
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        else {
            return await response.json();
        }
    } catch (error) {
        console.error('Error sending barcode to backend:', error);
        throw error; // Rethrow to handle it in the calling component
    }
};
