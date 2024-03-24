import React from "react";
import { Route, Routes } from "react-router-dom";
import LoginPage from "./components/Login";
import SignupPage from "./components/Signup";

const App: React.FC = () => {
  return (
    <Routes>
      <Route path="/signin" element={<LoginPage />} />
      <Route path="/signup" element={<SignupPage />} />
    </Routes>
  );
};

export default App;
