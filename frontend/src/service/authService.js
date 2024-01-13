import axios from "axios";
import { toast } from "react-toastify";
import { AUTH_URL } from "../constant";
import { errorhandler, setUser } from "../helper";
class AuthService {
  async login(email, password) {
    try {
      const response = await axios.post(AUTH_URL + "/signin", {
        email: email,
        password: password,
      });
      if (response.data.accessToken) {
        console.log(response)
        console.log(response.data.accessToken);
        setUser(JSON.stringify(response.data));
        window.location.replace("/");
      }
      return response.data;
    } catch (error) {
      errorhandler(error);
      console.log(error);
    }
  }

  async register(email, password) {
    try {
      const response = await axios.post(AUTH_URL + "/signup", {
        email: email,
        password: password,
      });
      toast.success(response.data.message);
      return response.data;
    } catch (error) {
      console.log(error);
      errorhandler(error);
    }
  }
}

export default new AuthService();
