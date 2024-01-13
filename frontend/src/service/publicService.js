import axios from "axios";
import { BASE_URL, PUBLIC_URL } from "../constant";
import { errorhandler } from "../helper";

class PublicService {
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

    async getBook(id) {
        try {
            const response = await axios.get(PUBLIC_URL + "/book/"+id);
            console.log("book", response.data);
            return response.data;
        } catch (error) {
            console.log(error);
            errorhandler(error);
        }
    }

    async getBookByCategory(categoryName) {
        try {
            const response = await axios.get(PUBLIC_URL + "/book/category/"+categoryName);
            console.log("book", response.data);
            return response.data;
        } catch (error) {
            console.log(error);
            errorhandler(error);
            return [];
        }
    }

    async getAuthors() {
        try {
            const response = await axios.get(PUBLIC_URL + "/author");
            console.log("authors", response.data);
            return response.data;
        } catch (error) {
            console.log(error);
            errorhandler(error);
        }
    }

    async getLanguage(){
        try {
            const response = await axios.get(PUBLIC_URL + "/language");
            console.log("language", response.data);
            return response.data;
        } catch (error) {
            console.log(error);
            errorhandler(error);
        }
    }
  

}

export default new PublicService();
