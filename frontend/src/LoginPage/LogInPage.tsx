import { GOOGLE_AUTH_URL } from "../constants";

export const LogInPage = () => {
  const onLogin = () => {
    window.location.href = GOOGLE_AUTH_URL;
  };

  return (
    <div>
      {process.env.REACT_APP_KEY}
      <button onClick={onLogin}>Login with GOOGLE</button>
    </div>
  );
};
