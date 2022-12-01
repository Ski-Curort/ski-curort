import axios from "axios";
import { authorizedApi } from "../hooks/userAxios";
import { User } from "../models/user";

export class UserApi {
  static getUser = async () =>
    await authorizedApi.get<User>(`${process.env.REACT_APP_API_BASE_URL}/profile`);
}
