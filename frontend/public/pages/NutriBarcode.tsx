import React, { useRef, useEffect } from 'react';

const Camera: React.FC = () => {
    const videoRef = useRef<HTMLVideoElement | null>(null);
    const canvasRef = useRef<HTMLCanvasElement | null>(null);

    useEffect(() => {
        if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
            navigator.mediaDevices.getUserMedia({ video: true }).then((stream) => {
                if (videoRef.current) {
                    videoRef.current.srcObject = stream;
                }
            });
        }
    }, []);

    const handleSnap = () => {
        if (canvasRef.current && videoRef.current) {
            const context = canvasRef.current.getContext('2d');
            if (context) {
                context.drawImage(videoRef.current, 0, 0, 640, 480);
            }
        }
    };

    return (
        <div>
            <video ref={videoRef} width="640" height="480" autoPlay></video>
            <button onClick={handleSnap}>Snap Photo</button>
            <canvas ref={canvasRef} width="640" height="480"></canvas>
        </div>
    );
};

export default Camera;