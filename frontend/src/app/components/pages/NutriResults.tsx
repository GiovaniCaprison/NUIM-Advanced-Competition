// pages/NutriResults.tsx
import React from 'react';
import { useRouter } from 'next/router';

const NutriResults: React.FC = () => {
    const router = useRouter();
    const data = router.query.data ? JSON.parse(router.query.data as string) : {};

    return (
        <div>
            <h1>Nutritional Information</h1>
            {Object.keys(data).length > 0 ? (
                <div>
                    {Object.entries(data).map(([key, value]) => (
                        <p key={key}>
                            <strong>{key}</strong>: {String(value)}
                        </p>
                    ))}
                </div>
            ) : (
                <p>No data found</p>
            )}
        </div>
    );
};

export default NutriResults;
