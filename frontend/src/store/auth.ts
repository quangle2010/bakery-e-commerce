// stores/authStore.ts
import Cookies from "js-cookie";
import { defineStore } from "pinia";
import axios from "axios";
import axiosClient from "../util/axiosClient";

interface User {
  id: number;
  email: string;
  fullName: string;
  phone: string;
  address: string;
  role: string;
  cart: number;
  addressId: number;
}

interface Auth {
  user: User | null;
  isInitialized: boolean;
  isAuthenticated: boolean;
}

export const useAuthStore = defineStore("auth", {
  state: (): Auth => ({
    user: null,
    isInitialized: false,
    isAuthenticated: false,
  }),

  actions: {
    async login(email: string, password: string) {
      try {
        const { data } = await axios.post("http://localhost:8080/login", {
          email,
          password,
        });
        if (data.status === true) {
          Cookies.set("token", data.data.token, {
            expires: 7,
            sameSite: "Lax",
          });
          await this.fetchUserInfo();
          this.isAuthenticated = true;
        }
      } catch (error: any) {
        this.logout();
        throw error;
      } finally {
        this.isInitialized = true;
      }
    },

    async fetchUserInfo() {
      try {
        const { data } = await axiosClient.get("/auth");
        if (data.status === true) {
          this.user = data.data;
          return data.data;
        }
      } catch (error) {
        this.logout();
        throw error;
      }
    },

    logout() {
      this.user = null;
      this.isAuthenticated = false;
      this.isInitialized = true;
      Cookies.remove("token");
    },

    async initialize() {
      const token = Cookies.get("token");
      if (token) {
        try {
          await this.fetchUserInfo();
          this.isAuthenticated = true;
        } catch {
          this.logout();
        }
      }
      this.isInitialized = true;
    },
    updateAddressId(addressId: number) {
      if (this.user) {
        this.user.addressId = addressId;
      }
    },
  },

  getters: {
    getUser: (state) => state.user,
    cartCount: (state) => state.user?.cart ?? 0,
    addressId: (state) => state.user?.addressId ?? null,
    isLoggedIn: (state) => state.isAuthenticated,
  },
});
