'use client'
import * as React from 'react';
import { PaletteMode } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Divider from '@mui/material/Divider';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import AppAppBar from './components/landing/AppAppBar';
import Hero from './components/landing/Hero';
import Highlights from './components/landing/Highlights';
import Pricing from './components/landing/Pricing';
import Features from './components/landing/Features';
import Testimonials from './components/landing/Testimonials';
import FAQ from './components/landing/FAQ';
import Footer from './components/common/Footer';
import getLPTheme from './getLPTheme';

export default function LandingPage() {
    const [mode, setMode] = React.useState<PaletteMode>('dark');
    const [showCustomTheme, setShowCustomTheme] = React.useState(true);
    const LPtheme = createTheme(getLPTheme(mode));
    const defaultTheme = createTheme({ palette: { mode } });

    const toggleColorMode = () => {
        setMode((prev) => (prev === 'dark' ? 'light' : 'dark'));
    };

    const toggleCustomTheme = () => {
        setShowCustomTheme((prev) => !prev);
    };

    return (
        <ThemeProvider theme={showCustomTheme ? LPtheme : defaultTheme}>
            <CssBaseline />
            <AppAppBar mode={mode} toggleColorMode={toggleColorMode} />
            <Hero />
            <Box sx={{ bgcolor: 'background.default' }}>
                <Features />
                <Divider />
                <Testimonials />
                <Divider />
                <Highlights />
                <Divider />
                <Pricing />
                <Divider />
                <FAQ />
                <Divider />
                <Footer />
            </Box>
        </ThemeProvider>
    );
}
