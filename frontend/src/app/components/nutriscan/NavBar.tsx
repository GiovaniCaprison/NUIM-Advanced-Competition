// NavBar.tsx
import Link from 'next/link';
import { AppBar, Toolbar, Typography, Button, Container, IconButton } from '@mui/material';
import Brightness4Icon from '@mui/icons-material/Brightness4';
import Brightness7Icon from '@mui/icons-material/Brightness7';
import React from 'react';

// Define the props interface
interface NavBarProps {
    mode: 'light' | 'dark';
    toggleColorMode: () => void;
}

// Accept mode and toggleColorMode as props
const NavBar: React.FC<NavBarProps> = ({ mode, toggleColorMode }) => {
    return (
        <AppBar position="static">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        NutriScan
                    </Typography>
                    <Link href="/pages/profile" passHref>
                        <Button color="inherit">Profile</Button>
                    </Link>
                    <Link href="/pages/SetGoals" passHref>
                        <Button color="inherit">Set Goals</Button>
                    </Link>
                    <Link href="/pages/FoodDiary" passHref>
                        <Button color="inherit">Food Diary</Button>
                    </Link>
                    <Link href="/pages/NutriBarcode" passHref>
                        <Button color="inherit">NutriBarcode</Button>
                    </Link>
                    <Link href="/pages/UserSettings" passHref>
                        <Button color="inherit">User Settings</Button>
                    </Link>
                    {/* IconButton to toggle light/dark mode */}
                    <IconButton onClick={toggleColorMode} color="inherit">
                        {mode === 'dark' ? <Brightness7Icon /> : <Brightness4Icon />}
                    </IconButton>
                </Toolbar>
            </Container>
        </AppBar>
    );
};

export default NavBar;
