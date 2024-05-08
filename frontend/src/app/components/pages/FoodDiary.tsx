// FoodDiary.tsx
import React, {useEffect, useState} from 'react';
import {sendFoodDiaryEntryToBackend, fetchFoodDiaryEntries, fetchBarcodeEntries} from '../services/apiService';
import { alpha, Box,} from '@mui/material';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import IconButton from "@mui/material/IconButton";
import NutriBarcode from './NutriBarcode'; // Import NutriBarcode

interface FoodEntry {
    id: number;
    name: string;
    calories: number;
}

interface BarcodeEntry {
    productName: string;
    protein: number;
    carbs: number;
    calories: number;
}

const FoodDiary: React.FC = () => {
    const [entries, setEntries] = useState<FoodEntry[]>([]);
    const [itemEntries, setItemEntries] = useState<BarcodeEntry[]>([]);

    const [productName, setProductName] = useState('');
    const [protein, setProtein] = useState<number | ''>('');
    const [carbs, setCarbs] = useState<number | ''>('');
    const [itemCalories, setItemCalories] = useState<number | ''>('');

    const [foodName, setFoodName] = useState('');
    const [calories, setCalories] = useState<number | ''>('');

    const theme = createTheme({
        palette: {
            mode: 'dark', // Change this to 'light' if you want a light theme
        },
    });
    const addFoodEntry = () => {
        if (foodName && calories) {
            const newEntry = { id: Date.now(), name: foodName, calories: Number(calories) };
            setEntries([...entries, newEntry]);
            setFoodName('');
            setCalories('');
            sendFoodDiaryEntryToBackend({foodName, calories})
                .then(r =>  console.log(r))
                .catch(e => console.error(e));

        }
    };

    useEffect(() => {
        fetchFoodDiaryEntries()
            .then((entries) => {
                console.log(entries);
                setEntries(entries);
            })
            .catch((error) => console.error(error));
        fetchBarcodeEntries()
            .then((itemEntries) => {
                console.log(entries);
                setEntries(entries);
            })
    },
        []);



    const removeFoodEntry = (id: number) => {
        setEntries(entries.filter((entry) => entry.id !== id));
    };

    // @ts-ignore
    // @ts-ignore
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

                <div>
                    <h1>Food Diary</h1>
                    <div>
                        <input
                            type="text"
                            value={foodName}
                            onChange={(e) => setFoodName(e.target.value)}
                            placeholder="Food Name"
                        />
                        <input
                            type="number"
                            value={calories}
                            onChange={(e) => setCalories(Number(e.target.value))}
                            placeholder="Calories"
                        />
                        <button onClick={addFoodEntry}>Add Entry</button>
                    </div>
                    <ul>
                        {entries.map((entry) => (

                            <li key={entry.id}>
                                {entry.foodName} - {entry.calories} kcal
                                <button onClick={() => removeFoodEntry(entry.id)}>Remove</button>
                            </li>
                        ))}
                    </ul>
                    <ul>
                        {itemEntries.map((entry) => (

                            <li key={entry.id}>
                                {entry.itemName} - {entry.calories} kcal
                                <button onClick={() => removeFoodEntry(entry.id)}>Remove</button>
                            </li>
                        ))}
                    </ul>
                </div>

            </Box>
        </ThemeProvider>
    );
};

export default FoodDiary;
