import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

const userTestimonials = [
    {
        avatar: <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />,
        name: 'Remy Sharp',
        occupation: 'Fitness Coach',
        testimonial:
            "NutriScan has revolutionized my approach to nutrition and fitness. The ability to scan barcodes and track my food intake helps me stay on top of my dietary goals with ease. It's an indispensable tool for anyone serious about their health.",
    },
    {
        avatar: <Avatar alt="Travis Howard" src="/static/images/avatar/2.jpg" />,
        name: 'Travis Howard',
        occupation: 'Nutritionist',
        testimonial:
            "I'm thoroughly impressed with NutriScan's customer support. Anytime I've had a question or needed assistance, their team was there to help. It's fantastic to see such dedication to user satisfaction.",
    },
    {
        avatar: <Avatar alt="Cindy Baker" src="/static/images/avatar/3.jpg" />,
        name: 'Cindy Baker',
        occupation: 'Dietitian',
        testimonial:
            'NutriScan has simplified nutritional tracking for my clients and me. Its user-friendly interface makes it easy to log meals and snacks, significantly enhancing our daily health management practices.',
    },
    {
        avatar: <Avatar alt="Julia Stewart" src="/static/images/avatar/4.jpg" />,
        name: 'Julia Stewart',
        occupation: 'Health Enthusiast',
        testimonial:
            "The attention to detail in NutriScan's app design is outstanding. From barcode scanning to goal tracking, every feature is carefully thought out to provide a seamless user experience.",
    },
    {
        avatar: <Avatar alt="John Smith" src="/static/images/avatar/5.jpg" />,
        name: 'John Smith',
        occupation: 'Gym Owner',
        testimonial:
            "NutriScan stands out with its innovative features, like the barcode scanning and goal-setting functionalities. It's a game-changer for anyone looking to improve their dietary habits and track their progress.",
    },
    {
        avatar: <Avatar alt="Daniel Wolf" src="/static/images/avatar/6.jpg" />,
        name: 'Daniel Wolf',
        occupation: 'Personal Trainer',
        testimonial:
            "I was amazed by the quality and versatility of NutriScan. It's not just an app; it's a comprehensive tool that supports my clients' nutrition and fitness journeys. Definitely a worthy investment for health and wellness professionals.",
    },
];

const logoStyle = {
    width: '64px',
    opacity: 0.3,
};

export default function Testimonials() {

    return (
        <Container
            id="testimonials"
            sx={{
                pt: { xs: 4, sm: 12 },
                pb: { xs: 8, sm: 16 },
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
                <Typography component="h2" variant="h4" color="text.primary">
                    Testimonials
                </Typography>
                <Typography variant="body1" color="text.secondary">
                    See what customers love about NutriScan. Discover how we excel in
                    efficiency, durability, and satisfaction. Join us for quality, innovation,
                    and reliable support.
                </Typography>
            </Box>
            <Grid container spacing={2}>
                {userTestimonials.map((testimonial, index) => (
                    <Grid item xs={12} sm={6} md={4} key={index} sx={{ display: 'flex' }}>
                        <Card
                            sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'space-between',
                                flexGrow: 1,
                                p: 1,
                            }}
                        >
                            <CardContent>
                                <Typography variant="body2" color="text.secondary">
                                    {testimonial.testimonial}
                                </Typography>
                            </CardContent>
                            <Box
                                sx={{
                                    display: 'flex',
                                    flexDirection: 'row',
                                    justifyContent: 'space-between',
                                    pr: 2,
                                }}
                            >
                                <CardHeader
                                    avatar={testimonial.avatar}
                                    title={testimonial.name}
                                    subheader={testimonial.occupation}
                                />
                            </Box>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
}