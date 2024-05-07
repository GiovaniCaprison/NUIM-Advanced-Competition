'use client'
import * as React from 'react';
import Footer from "@/app/components/common/Footer";
import DashboardHero from "@/app/components/nutriscan/DashboardHero";
import NavBar from "@/app/components/nutriscan/NavBar";

import { ThemeProvider, createTheme } from '@mui/material/styles';
import { PaletteMode } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Divider from '@mui/material/Divider';
import getLPTheme from "@/app/getLPTheme";
import { UserContext } from '@/context/UserContext';
import {string} from "prop-types";

export default function DashboardPage() {
    const { userEmail, setUserInfo, getUserInfo } = React.useContext(UserContext);

    const [mode, setMode] = React.useState<PaletteMode>('dark');
    const [showCustomTheme] = React.useState(true);
    const LPtheme = createTheme(getLPTheme(mode));
    const defaultTheme = createTheme({ palette: { mode } });

    const toggleColorMode = () => {
        setMode((prev) => (prev === 'dark' ? 'light' : 'dark'));
    };


    return (
        <UserContext.Provider value = {{ userEmail, setUserInfo, getUserInfo }}>
            <ThemeProvider theme={showCustomTheme ? LPtheme : defaultTheme}>
                <CssBaseline />
                <NavBar mode={mode} toggleColorMode={toggleColorMode} />
                <DashboardHero userName={userEmail} />
                <Box sx={{ bgcolor: 'background.default' }}>
                    <Divider />
                    <Footer />
                </Box>
                <footer />
            </ThemeProvider>
        </UserContext.Provider>
    );
}