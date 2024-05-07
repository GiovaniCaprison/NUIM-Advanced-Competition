import React from 'react';

interface UserContextProps {
    userEmail: string;
    setUserInfo: (email: string) => void;
    getUserInfo: () => string;
}

export const UserContext = React.createContext<UserContextProps>({
   userEmail: '',
   setUserInfo: (email: string) => { },
   getUserInfo: () => ''
});