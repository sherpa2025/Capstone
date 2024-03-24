import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { loginAsync } from "../../features/auth/authSlice";

const LoginPage: React.FC = () => {
  const dispatch = useDispatch();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const response = await dispatch(loginAsync({ email, password }));
      const accessToken = response.payload;
      if (accessToken != null) {
        setEmail("");
        setPassword("");
        alert(accessToken);
      } else {
        console.log("Invalid email or password");
        alert("Invalid email or password");
      }
    } catch (error) {
      console.log("Login failed:", (error as Error).message || "Unknown error");
      alert("Login failed!");
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleLogin();
        }}
      >
        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
