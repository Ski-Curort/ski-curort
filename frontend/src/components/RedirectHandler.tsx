import { useRef } from "react";
import { Navigate } from "react-router-dom";
import { ACCESS_TOKEN } from "../constants";
import useUrlQuery from "../hooks/useUrlQuery";

export const RedirectHandler = () => {
  const query = useRef<URLSearchParams>(useUrlQuery());
  const token = query.current.get("token");

  if(token){
    localStorage.setItem(ACCESS_TOKEN, token);
    return <Navigate to={"/profile"} />
  }

  return <Navigate to={"/login"}/>;
};
