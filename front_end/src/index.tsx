import * as React from 'react';
import { createRoot } from 'react-dom/client'; // Use named import for createRoot
import App from './App';

// Ensure the element exists in your HTML file
const container = document.getElementById('root');

if (container) {
    const root = createRoot(container); // Initialize the root
    root.render(
        <React.StrictMode>
            <App />
        </React.StrictMode>
    );
}
