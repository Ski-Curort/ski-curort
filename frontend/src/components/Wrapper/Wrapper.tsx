import { Box, useToast } from "@chakra-ui/react";
import { useCallback, useEffect } from "react";
import { Outlet, Link, useNavigate } from "react-router-dom";
import { UserApi } from "../../api/UserApi";
import { ACCESS_TOKEN, GOOGLE_AUTH_URL } from "../../constants";
import useAppContext from "../../hooks/useAppContext";
import useAxios from "../../hooks/userAxios";
import "./Wrapper.css";
import Logo from "../../files/logo.svg";
import Person from "../../files/Vector (2).png";

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
    window.location.href = GOOGLE_AUTH_URL;
  };

  return (
    <div className="container">
      <Box className={"navBar"}>
        <Link to={"/"}>
          <img className={"logo"} src={Logo} alt="logo"></img>
        </Link>

        <Box
          display={"flex"}
          flexDirection={"row"}
          alignItems={"center"}
          justifyContent={"center"}
        >
          {!context.currentUser ? (
            <Box
              display={"flex"}
              flexDirection={"row"}
              alignItems={"center"}
              justifyContent={"center"}
            >
              {/* <button className={"signButton"}>Singup</button> */}
              <button onClick={onLogin} className={"button"}>
                Login
              </button>
              <Box marginRight="43px"></Box>
            </Box>
          ) : (
            <Box
              display={"flex"}
              flexDirection={"row"}
              alignItems={"center"}
              justifyContent={"center"}
            >
              <Box display="flex" flexDirection={"row"}>
                <img alt={"Person Icon"} src={Person} width="16px" />
                <p className={"person"}>
                  {" "}
                  <Link to="/profile"> {context.currentUser.displayName}</Link>
                </p>
              </Box>
              <button onClick={onLogout} className={"button"}>
                Logout
              </button>
              <Box marginRight="43px"></Box>
            </Box>
          )}
        </Box>
      </Box>
      <Outlet />
    </div>
  );
};
