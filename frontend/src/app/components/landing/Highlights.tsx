import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import AutoFixHighRoundedIcon from '@mui/icons-material/AutoFixHighRounded';
import ConstructionRoundedIcon from '@mui/icons-material/ConstructionRounded';
import QueryStatsRoundedIcon from '@mui/icons-material/QueryStatsRounded';
import SettingsSuggestRoundedIcon from '@mui/icons-material/SettingsSuggestRounded';
import SupportAgentRoundedIcon from '@mui/icons-material/SupportAgentRounded';
import ThumbUpAltRoundedIcon from '@mui/icons-material/ThumbUpAltRounded';

const items = [
    {
        icon: <SettingsSuggestRoundedIcon />,
        title: 'Adaptable performance',
        description:
            'NutriScan effortlessly adjusts to your needs, making it easier than ever to keep track of your food.',
    },
    {
        icon: <ConstructionRoundedIcon />,
        title: 'Built to last',
        description:
            'Experience unmatched durability when using NutriScan that goes above and beyond our competitors.',
    },
    {
        icon: <ThumbUpAltRoundedIcon />,
        title: 'Great user experience',
        description:
            'Know exactly what is inside your favorite foods, and track progress with an intuitive and easy-to-use interface.',
    },
    {
        icon: <AutoFixHighRoundedIcon />,
        title: 'Innovative functionality',
        description:
            'Stay ahead with features that set new standards, addressing your evolving needs better than the rest.',
    },
    {
        icon: <SupportAgentRoundedIcon />,
        title: 'Reliable support',
        description:
            'Count on our responsive customer support, offering assistance that goes beyond the purchase.',
    },
    {
        icon: <QueryStatsRoundedIcon />,
        title: 'Precision in every detail',
        description:
            'Enjoy a meticulously crafted product where small touches make a significant impact on your overall experience.',
    },
];

export default function Highlights() {
    return (
        <Box
            id="highlights"
            sx={{
                pt: { xs: 4, sm: 12 },
                pb: { xs: 8, sm: 16 },
                color: 'text.primary',
                bgcolor: 'transparent',
            }}
        >
            <Container
                sx={{
                    position: 'relative',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    gap: { xs: 3, sm: 6 },
                }}
            >
                <Box
                    sx={{
                        width: { sm: '100%', md: '60%' },
                        textAlign: { sm: 'left', md: 'center' },
                    }}
                >
                    <Typography component="h2" variant="h4">
                        Highlights
                    </Typography>
                    <Typography variant="body1" color='text.secondary'>
                        Explore why NutriScan stands out: adaptability, durability,
                        user-friendly design, and innovation. Enjoy reliable customer support and
                        precision in every detail.
                    </Typography>
                </Box>
                <Grid container spacing={2.5}>
                    {items.map((item, index) => (
                        <Grid item xs={12} sm={6} md={4} key={index}>
                            <Stack
                                direction="column"
                                color="inherit"
                                component={Card}
                                spacing={1}
                                useFlexGap
                                sx={{
                                    p: 3,
                                    height: '100%',
                                }}
                            >
                                <Box sx={{ opacity: '50%' }}>{item.icon}</Box>
                                <div>
                                    <Typography fontWeight="medium" gutterBottom>
                                        {item.title}
                                    </Typography>
                                    <Typography variant="body2" sx={{ color: 'grey.400' }}>
                                        {item.description}
                                    </Typography>
                                </div>
                            </Stack>
                        </Grid>
                    ))}
                </Grid>
            </Container>
        </Box>
    );
}