import axios from "axios";
import { BASE_URL, PUBLIC_URL } from "../constant";
import { errorhandler, getHeader } from "../helper";
import { toast } from "react-toastify";

class AdminService {
  async addBook(
    title,
    description,
    imageUrl,
    author,
    category,
    language,
    price
  ) {
    try {
      const response = axios.post(
        PUBLIC_URL + "/book/add",
        {
          title,
          description,
          imageUrl,
          author,
          category,
          language,
          price,
        },
        getHeader()
      );
      console.log("addBook", response);
      toast.success("Book added successfully");
      return response;
    } catch (error) {
      console.log(error);
      errorhandler(error);
    }
  }

  async updateBook(
    id,
    title,
    description,
    imageUrl,
    author,
    category,
    language,
    price
  ) {
    try {
      const response = axios.post(
        PUBLIC_URL + "/book/update/" + id,
        {
          title,
          description,
          imageUrl,
          author: author,
          category: category,
          language: language,
          price,
        },
        getHeader()
      );
      console.log("updateBook", response);
      toast.success("Book updated successfully");
      return response;
    } catch (error) {
      console.log(error);
      errorhandler(error);
    }
  }

  async getOrders() {
    try {
      const response = await axios.get(PUBLIC_URL + "/orders", getHeader());
      console.log("getOrders", response);
      return response.data;
    } catch (error) {
      console.log(error.response.data.error);
      errorhandler(error);
    }
  }


  async updateOrderStatus(id, status) {
    try {
      const response = await axios.post(
        PUBLIC_URL + "/orders/update/" + id+"?status="+status,
        {
        //   status,
        },
        getHeader()
      );
      console.log("updateOrderStatus", response);
      toast.success("Order updated successfully");
      return response;
    } catch (error) {
      console.log(error);
      errorhandler(error);
    }
  }

  async deleteBook(id) {
    try {
      const response = await axios.post(
        PUBLIC_URL + "/book/delete/" + id,
        {},
        getHeader()
      );
      console.log("deleteBook", response);
      toast.success("Book deleted successfully");
      return response;
    } catch (error) {
      console.log(error);
      errorhandler(error);

      return error;
    }
  }
}

export default new AdminService();
