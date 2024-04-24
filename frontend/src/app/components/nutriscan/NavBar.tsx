// NavBar.tsx
import Link from 'next/link';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';

const NavBar = () => {
    return (
        <AppBar position="static">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        NutriScan
                    </Typography>
                    <Button color="inherit" component={Link} href="/profile" noLinkStyle>
                        Profile
                    </Button>
                    <Button color="inherit" component={Link} href="/SetGoals" noLinkStyle>
                        Set Goals
                    </Button>
                    <Button color="inherit" component={Link} href="/FoodDiary" noLinkStyle>
                        Food Diary
                    </Button>
                    <Button color="inherit" component={Link} href="/NutriBarcode" noLinkStyle>
                        NutriBarcode
                    </Button>
                    <Button color="inherit" component={Link} href="/UserSettings" noLinkStyle>
                        User Settings
                    </Button>
                </Toolbar>
            </Container>
        </AppBar>
    );
};

export default NavBar;




