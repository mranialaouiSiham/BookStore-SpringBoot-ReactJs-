import React from "react";
import { Button, Modal } from "react-bootstrap";
import adminService from "../../service/adminService";
import Navbar from "./Navbar/Navbar";

export default function OrdersPage() {
  const [orders, setOrders] = React.useState([]);
  const [show, setShow] = React.useState(false);
  const [selectedOrder, setSelectedOrder] = React.useState({});

  React.useEffect(() => {
    adminService.getOrders().then((response) => {
      setOrders(response);
    });
  }, []);

  function handleClose() {
    setShow(false);
  }

  function handeOrderUpdate(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const status = data.get("status");
    console.log(status);

    adminService
      .updateOrderStatus(selectedOrder.id, status)
      .then((response) => {
        setOrders(
          orders.map((order) => {
            if (order.id === selectedOrder.id) {
              order.orderStatus = status;
            }
            return order;
          })
        );
        setShow(false);
      });
  }
  return (
    <div>
      <Navbar />

      <div className="container-md">
        <h1>Orders</h1>
        <div className="row">
          <div className="col-md-12">
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>Order ID</th>
                  <th>Customer Email</th>
                  <th>Order Date</th>
                  <th>Total</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {orders.map((order) => (
                  <tr key={order.id}>
                    <td>{order.id}</td>
                    <td>{order.user.email}</td>
                    <td>{order.orderDate}</td>
                    <td>₹ {Number(order.totalPrice).toFixed(2)}</td>
                    <td>
                      <span className="badge bg-info">{order.orderStatus}</span>
                    </td>
                    <td>
                      <button
                        className="btn btn-outline-primary"
                        onClick={() => {
                          setShow(true);
                          setSelectedOrder(order);
                        }}
                      >
                        <i className="fa fa-eye"></i>
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Order details</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <h6>
              address: <span>{selectedOrder.address}</span>
            </h6>
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>Product Name</th>
                  <th>Product Price</th>
                  <th>Quantity</th>
                  <th>Total</th>
                </tr>
              </thead>
              <tbody>
                {selectedOrder?.orderItems?.map((item) => (
                  <tr key={item.id}>
                    <td>{item.title}</td>
                    <td>₹ {Number(item.purchasePrice).toFixed(2)}</td>
                    <td>{item.quantity}</td>
                    <td>
                      ₹ {Number(item.purchasePrice * item.quantity).toFixed(2)}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            <hr />
            <form onSubmit={handeOrderUpdate} id="order-update-form">
              <label> update order status</label>
              <select className="form-control" name="status">
                <option value={"PROCESSING"}>PROCESSING</option>
                <option value={"DELIVERED"}>DELIVERED</option>
              </select>
            </form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="primary" type="submit" form="order-update-form">
              Save Changes
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
}
