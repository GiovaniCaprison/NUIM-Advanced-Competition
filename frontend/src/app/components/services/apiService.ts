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

export const sendFoodDiaryEntryToBackend = async ({ foodName, calories }: { foodName: string, calories: number }) => {
    try {
        const response = await fetch(`${BASE_URL}/api/fooddiary`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ foodName, calories }),
        });

        if (!response.ok) {
            throw new Error(`Network response was not ok. Status code: ${response.status}, status text: '${response.statusText}'`);
        }

        else {
            return await response.json();
        }
    } catch (error) {
        console.error('Error sending food diary entry to backend:', error);
        throw error; // Rethrow to handle it in the calling component
    }
};

export const fetchFoodDiaryEntries = async () => {
    try {
        const response = await fetch(`${BASE_URL}/api/fooddiary`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }

        });


        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        else {
            return await response.json();
        }
    } catch (error) {
        console.error('Error fetching food diary entries:', error);
        throw error; // Rethrow to handle it in the calling component
    }
};
export const sendBarcodeEntryToBackend = async (barcodeEntry: { response: any; barcode: string }) => {
    try {
        const response = await fetch(`${BASE_URL}/api/barcodeEntry`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(barcodeEntry),
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        else {
            return await response.json();
        }
    } catch (error) {
        console.error('Error sending barcode diary to backend:', error);
        throw error; // Rethrow to handle it in the calling component
    }
};
export const fetchBarcodeEntries = async () => {
    try {
        const response = await fetch(`${BASE_URL}/api/getBarcodeEntry`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }

        });


        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        else {
            return await response.json();
        }
    } catch (error) {
        console.error('Error fetching barcode diary entries:', error);
        throw error; // Rethrow to handle it in the calling component
    }
};

export const fetchUserDetails = async (email: string) => {
    try {
        const response = await fetch(`${BASE_URL}/api/user/${email}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        else {
            return await response.json();
        }
    } catch (error) {
        console.error('Error fetching user details:', error);
        throw error; // Rethrow to handle it in the calling component
    }
};
