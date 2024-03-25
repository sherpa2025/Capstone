import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { RootState } from "../../app/store"; // Assuming your store is named store
import axios from "axios";

// Define the base URL for your backend
const baseURL = "http://localhost:8080";

interface AuthState {
  isLoggedIn: boolean;
  token: string | null;
}

const initialState: AuthState = {
  isLoggedIn: false,
  token: null,
};

// Create an Axios instance with the base URL
const axiosInstance = axios.create({
  baseURL,
});

// Define your async thunks for login and signup
export const loginAsync = createAsyncThunk(
  "auth/login",
  async (
    credentials: { email: string; password: string },
    { rejectWithValue }
  ) => {
    try {
      const response = await axiosInstance.post(
        "/api/v1/auth/signin",
        credentials
      );
      const { token, refreshToken } = response.data;
      // Store tokens in local storage upon successful login
      localStorage.setItem("token", token);
      localStorage.setItem("refreshToken", refreshToken);
      return token;
    } catch (error) {
      return rejectWithValue(error.response.data.message || "Login failed");
    }
  }
);

export const signupAsync = createAsyncThunk(
  "auth/signup",
  async (userInfo: {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
  }) => {
    try {
      const response = await axiosInstance.post(
        "/api/v1/auth/signup",
        userInfo
      );
      return response.data.token;
    } catch (error) {
      throw error.response.data.message || "Signup failed";
    }
  }
);

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setLoggedIn: (state) => {
      state.isLoggedIn = true;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(loginAsync.fulfilled, (state) => {
      state.isLoggedIn = true;
    });
    builder.addCase(signupAsync.fulfilled, (state) => {
      state.isLoggedIn = true;
    });
  },
});

export const { setLoggedIn } = authSlice.actions;

// Selectors
export const selectIsLoggedIn = (state: RootState) => state.auth.isLoggedIn;
export const selectToken = (state: RootState) => state.auth.token;
// Export the reducer as a default export
export default authSlice.reducer;
