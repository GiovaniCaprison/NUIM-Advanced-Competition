// pages/NutriBarcode.tsx
import * as React from 'react';

import NavBar from '../nutriscan/NavBar';

const NutriBarcode: React.FC = () => {
    const [mode, setMode] = React.useState<'light' | 'dark'>('light');
    const toggleColorMode = () => {
        setMode((prev) => (prev === 'dark' ? 'light' : 'dark'));
    };

    return (
        <div>
            <NavBar mode={mode} toggleColorMode={toggleColorMode} />
            <main>
                <h1>NutriBarcode Page</h1>
                {/*Your NutriBarcode page content*/ }
            </main>
        </div>
    );
};

export default NutriBarcode;
