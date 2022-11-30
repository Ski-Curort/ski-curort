import axios from "axios";
import { authorizedApi } from "../hooks/userAxios";
import { User } from "../models/User";

export class UserApi {
  static getUser = async () =>
    await authorizedApi.get<User>("http://localhost:8080/profile");
}
