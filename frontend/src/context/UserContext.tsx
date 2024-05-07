// src/context/UserContext.tsx
import React from 'react';
import { useContext, createContext } from 'react';

export const UserContext = createContext('user');

export default function App() {
    return (
        <UserContext.Provider value={{  }}>
        </UserContext.Provider>
    );
}

function User(){

}