import React, {useEffect, useState} from 'react';
import {sendFoodDiaryEntryToBackend, fetchFoodDiaryEntries, fetchBarcodeEntries} from '../services/apiService';
import { Collapse, List, ListItem, ListItemText, Box, Grid } from '@mui/material';
import { alpha, useTheme } from '@mui/material/styles';
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
    BrandName: string;
    protein: number;
    carbs: number;
    calories: number;
    response: {
        ProductName: string;
        'Vitamin B3 (Niacin) (mg per 1g)': number;
        'Vitamin B6 (mg per 1g)': number;
        // Add all other properties that you want to display
    };
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
    const [open, setOpen] = useState<number | null>(null);

    const handleClick = (index: number) => {
        setOpen(open === index ? null : index);
    };
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
    const fetchAndLogData = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/getBarcodeEntry'); // replace 'your-endpoint' with the actual endpoint
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log(data);
        } catch (error) {
            console.error('Error:', error);
        }
    };
    useEffect(() => {
            fetchFoodDiaryEntries()
                .then((entries) => {
                    console.log(entries);
                    setEntries(entries); // Set the food diary entries to the state
                })
                .catch((error) => console.error(error));
            fetchBarcodeEntries()
                .then((itemEntries) => {
                    console.log(itemEntries); // Log the barcode entries
                    setItemEntries(itemEntries); // Set the barcode entries to the state
                })
                .catch((error) => console.error(error));
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


                    <Grid container spacing={2} style={{ marginTop: '100px' }}>
                        <Grid item xs={6}>
                            <button onClick={fetchAndLogData}>Fetch and Log Data</button>
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
                            <h2>Food Diary Entries</h2>
                            <ul>
                                {entries.map((entry) => (
                                    <li key={entry.id}>
                                        {entry.foodName} - {entry.calories} kcal
                                        <button onClick={() => removeFoodEntry(entry.id)}>Remove</button>
                                    </li>
                                ))}
                            </ul>
                        </Grid>
                        <Grid item xs={6}>
                            <h2>Barcode Entries</h2>
                            <List>
                                {itemEntries.map((entry, index) => (
                                    <div key={index}>
                                        <ListItem button onClick={() => handleClick(index)}>
                                            <ListItemText primary={entry.response.ProductName} secondary={`${entry.calories} kcal`} />
                                        </ListItem>
                                        <Collapse in={open === index} timeout="auto" unmountOnExit>
                                            <List component="div" disablePadding>
                                                <ListItem>
                                                    <ListItemText primary={entry.response.BrandName} />
                                                </ListItem>
                                                <ListItem>
                                                    <ListItemText primary={`•Vitamin B3 (Niacin): ${entry.response['Vitamin B3 (Niacin) (mg per 1g)']}`} />
                                                </ListItem>
                                                <ListItem>
                                                    <ListItemText primary={`•Vitamin B6: ${entry.response['Vitamin B6 (mg per 1g)']}`} />
                                                </ListItem>
                                                {/* Add all other properties that you want to display */}
                                            </List>
                                        </Collapse>
                                    </div>
                                ))}
                            </List>
                        </Grid>
                    </Grid>



            </Box>

        </ThemeProvider>
    );
};
export default FoodDiary;