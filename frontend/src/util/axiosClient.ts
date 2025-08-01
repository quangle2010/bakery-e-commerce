import Cookies from "js-cookie";
import axios from "axios";

const axiosClient = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

axiosClient.interceptors.request.use(
  (config) => {
    const token = Cookies.get("token");
    if (token && config.headers) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosClient;
