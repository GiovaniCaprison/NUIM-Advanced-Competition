import * as React from 'react'; // Import React, necessary for JSX if you're using React 16 or earlier.
import BarcodeScanner from './components/BarcodeScanner';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                {BarcodeScanner() }
            </header>
        </div>
    );
}

export default App;


