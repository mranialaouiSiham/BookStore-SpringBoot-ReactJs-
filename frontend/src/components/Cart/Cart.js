import React from "react";
import { useCart } from "react-use-cart";
import {Link} from "react-router-dom";
const Cart = ({ id, image, title, item }) => {
  const { addItem } = useCart();
  // const { id, image, title, item } = (props);
  return (
    <>
      <div className="col-lg-4 col-md-12 col-sm-12" key={id}>
        <div className="card mt-5">
          <div className="card-body">
            <img
              src={image}
              alt={image}
              style={{ width: "100%", height: "200px" }}
            />
          </div>
          <h6 class="card-subtitle ms-1">
            <p className="ms-2">Price: MAD{item.price}</p>
            <p className="ms-2">Language: {item?.language?.language}</p>
            <p className="ms-2">category: {item?.category?.name}</p>
          </h6>
          <div className="card-footer d-flex align-items-center flex-column">
            <h6>{title}</h6>

            <div className="buttons w-100 d-flex justify-content-between">
              <button
                onClick={() => addItem(item)}
                data-bs-toggle="modal"
                data-bs-target="#cartModal"
                className="btn btn-outline-primary"
              >
                <i className="fa-solid fa-cart-plus"></i> Add Cart
              </button>
              <Link to={"/product/"+item.id} className="btn btn-outline-danger">
                 View Book
              </Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Cart;
