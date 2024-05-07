// SignInPage.js

// Ensure 'use client' is at the top for client-side rendering
'use client';
import React, { useState } from 'react';
import SignIn from "@/app/components/auth/SignIn";

// Adjust the path to the actual location of your SignIn component file

import { UserContext } from '@/context/UserContext';

export default function SignInPage() {
    const [userEmail, setUserEmail] = React.useState('');

    const setUserInfo = (email: string) => {
        setUserEmail(email);
    };

    const getUserInfo = () => {
        return userEmail;
    };

    return (
        <UserContext.Provider value={{userEmail, setUserInfo, getUserInfo}}>
            <SignIn />
        </UserContext.Provider>
    );
}
