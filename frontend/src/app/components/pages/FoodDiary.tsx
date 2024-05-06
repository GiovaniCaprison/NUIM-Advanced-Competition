// FoodDiary.tsx
import React, {useEffect, useState} from 'react';
import { sendFoodDiaryEntryToBackend, fetchFoodDiaryEntries } from '../services/apiService';

interface FoodEntry {
    id: number;
    name: string;
    calories: number;
}




const FoodDiary: React.FC = () => {
    const [entries, setEntries] = useState<FoodEntry[]>([]);
    const [foodName, setFoodName] = useState('');
    const [calories, setCalories] = useState<number | ''>('');

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
    }, []);

    const removeFoodEntry = (id: number) => {
        setEntries(entries.filter((entry) => entry.id !== id));
    };

    return (
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
                        {entry.name} - {entry.calories} kcal
                        <button onClick={() => removeFoodEntry(entry.id)}>Remove</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FoodDiary;
