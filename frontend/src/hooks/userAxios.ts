import axios, { AxiosRequestConfig } from "axios";
import {ACCESS_TOKEN, GOOGLE_AUTH_URL} from "../constants";

export const authorizedApi = axios.create();

export const useAxios = () => {

  authorizedApi.interceptors.request.use((config: AxiosRequestConfig) => {

    if(localStorage.getItem(ACCESS_TOKEN)===null){
      return {
        ...config,
        baseURL: process.env.REACT_APP_API_BASE_URL,
      };
    }

    return {
      ...config,
      headers: {
        ...config.headers,
        Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`,
      },
      baseURL: process.env.REACT_APP_API_BASE_URL,
    };
  });

  authorizedApi.interceptors.response.use(
    (response) => {
      return response;
    },
    (error) => {
      if (error.response.status === 401) {
        window.location.href = GOOGLE_AUTH_URL;
      }
      return Promise.reject(error);
    }
  );
};

export default useAxios;
