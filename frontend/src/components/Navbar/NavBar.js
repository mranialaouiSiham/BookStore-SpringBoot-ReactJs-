import React, { useEffect } from "react";
import { Button, Dropdown, DropdownButton } from "react-bootstrap";
import { Link } from "react-router-dom";
import { getUser, logout } from "../../helper";
import publicService from "../../service/publicService";
// import Logout from "./../Logout";

const NavBar = () => {
  const [category, setCategory] = React.useState([]);

  useEffect(() => {
    console.log("NavBar");
    publicService.getCategory().then((res) => {
      setCategory(res);
    });
  }, []);

  return (
    <>
      <nav className="navbar navbar-expand-lg bg-light">
        <div className="container">
          <Link className="navbar-brand text-primary" to="/">
            Book Store
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
              <DropdownButton
                className="btn btn-light"
               
                title="category"
                id="input-group-dropdown-1"
                size="sm"
              >
                {category.map((item, index) => {
                  return (
                    <Dropdown.Item key={index}>
                      <Link to={`/category/${item.name}`}>{item.name}</Link>
                    </Dropdown.Item>
                  );
                })}
              </DropdownButton>
              <li className="nav-item">
                <button
                  type="button"
                  className="nav-link text-primary border-0"
                  data-bs-toggle="modal"
                  data-bs-target="#cartModal"
                >
                  <i className="fa-solid fa-cart-shopping"></i> Cart
                </button>
              </li>

              {!getUser() ? (
                <>
                  <li className="nav-item">
                    <Link className="nav-link  text-primary" to="/login">
                      Login
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link  text-primary" to="/register">
                      Register
                    </Link>
                  </li>
                </>
              ) : (
                <>
                  <li className="nav-item">
                    <Link
                      className="nav-link  text-primary"
                      to={"/customer/orders"}
                    >
                      orders
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Button className="btn btn-light" onClick={logout}>
                      Logout
                    </Button>
                  </li>
                </>
              )}
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
};

export default NavBar;
