import "bootstrap/dist/css/bootstrap.min.css";

import NavBar from "./components/Navbar/NavBar";
import Home from "./components/Home/Home";
import Admin from "./components/Admin/Admin";
import RegisterForm from "./components/RegisterForm/RegisterForm";
import LoginForm from "./components/LoginForm/LoginForm";
import { Routes, Route } from "react-router-dom";
import { CartProvider } from "react-use-cart";
import Footer from "./components/Footer/Footer";
import Logout from "./components/Logout";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import CategoryPage from "./components/Category/CategoryPage";
import UpdateBook from "./components/Admin/updateBook";
import OrdersPage from "./components/Admin/OrdersPage";
import CustomerOrdersPage from "./components/customerOrders/customerOrdersPage";
import { getUser, isAdmin } from "./helper";
import BookDetails from './components/bookDetails/bookDetails';
import CartItems from "./components/Cart/CartItems";


const App = () => {
  return (
    <>
      <CartProvider>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/category/:name" element={<CategoryPage />} />
          <Route path="/product/:id" element={<BookDetails />} />

          {isAdmin() ? (
            <>
              <Route path="/admin" element={<Admin />} />
              <Route path="/admin/book/update/:id" element={<UpdateBook />} />
              <Route path="/admin/orders" element={<OrdersPage />} />
            </>
          ) : null}

          <Route path="register" element={<RegisterForm />} />
          <Route path="login" element={<LoginForm />} />
          <Route path="logout" element={<Logout />} />

          {getUser() ? (
            <Route path="/customer/orders" element={<CustomerOrdersPage />} />
          ) : null}

          {/* if route not found redirect homepage */}
          <Route path="*" element={<Home />} />
        </Routes>
        <Footer />
        <ToastContainer position="bottom-center" />
        <CartItems />
      </CartProvider>
    </>
  );
};

export default App;
