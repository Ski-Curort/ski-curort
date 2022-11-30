import { Button, useToast } from "@chakra-ui/react";
import { useCallback, useEffect } from "react";
import { Outlet, Link, useNavigate } from "react-router-dom";
import { UserApi } from "../../api/UserApi";
import { ACCESS_TOKEN } from "../../constants";
import useAppContext from "../../hooks/useAppContext";
import useAxios from "../../hooks/userAxios";
import "./Wrapper.css";

export const Wrapper = () => {
  const axios = useAxios();
  const context = useAppContext();
  const navigate = useNavigate();
  const toast = useToast();

  const fetchUser = useCallback(async () => {
    if (!localStorage.getItem(ACCESS_TOKEN) || context.currentUser) {
      return;
    }
    const user = await UserApi.getUser();
    context.userModifier(user.data);
  }, [context]);

  useEffect(() => {
    fetchUser();
  }, [fetchUser]);

  const onLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    context.userModifier(null);

    toast({
      title: "You've been successfully logged out",
      description: "For more security close all browser",
      status: "success",
      duration: 4000,
      isClosable: true,
    });

    navigate("/", { replace: true });
  };

  const onLogin = () => {
    navigate("/login");
  };

  return (
    <div className="container">
      <div className="menu-wraper">
        <Link to={"/"}>Ski Resort</Link>
        {!context.currentUser ? (
          <Button onClick={onLogin} colorScheme={"blue"}>
            Log in
          </Button>
        ) : (
          <div className="nav-logged">
            <span>Logged as:{" "} 
            <Link to="/profile"> {context.currentUser.displayName}</Link></span>
            <Button onClick={onLogout} colorScheme={"blue"}>
              Logout
            </Button>
          </div>
        )}
      </div>
      <Outlet />
    </div>
  );
};
