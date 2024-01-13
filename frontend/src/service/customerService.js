import axios from "axios";
import { BASE_URL, PUBLIC_URL } from "../constant";
import { errorhandler, getHeader } from "../helper";
import { toast } from "react-toastify";

class CustomerService {
  async getCategory() {
    try {
      const response = await axios.get(PUBLIC_URL + "/category");
      console.log("category", response.data);
      return response.data;
    } catch (error) {
      console.log(error);
      errorhandler(error);
    }
  }

  async getBooks() {
    try {
      const response = await axios.get(PUBLIC_URL + "/book");
      console.log("books", response.data);
      return response.data;
    } catch (error) {
      console.log(error);
      errorhandler(error);
    }
  }

  async checkout(address, cartItemList) {
    try {
      const response = await axios.post(
        PUBLIC_URL + "/orders/checkout",
        {
          address: address,
          cartItemList: cartItemList,
        },
        getHeader()
      );
      console.log("checkout", response.data);

      toast.success("order placed successfully");
      return response.data;
    } catch (error) {
      console.log(error);
      errorhandler(error);
      throw error;
    }
  }

  async getCustomerOrders() {
    try {
      const response = await axios.get(
        PUBLIC_URL + "/orders/customer",
        getHeader()
      );
      console.log("getOrders", response);
      return response.data;
    } catch (error) {
      console.log(error.response.data.error);
      errorhandler(error);
      throw error;
    }
  }
}

export default new CustomerService();
