import React from "react";
import { Button } from "react-bootstrap";
import { Modal } from "react-bootstrap";
import adminService from "../../service/adminService";
import customerService from "../../service/customerService";

export default function CustomerOrdersPage() {
  const [orders, setOrders] = React.useState([]);
  const [show, setShow] = React.useState(false);
  const [selectedOrder, setSelectedOrder] = React.useState({});

  React.useEffect(() => {
    customerService.getCustomerOrders().then((response) => {
      setOrders(response);
    });
  }, []);

  function handleClose() {
    setShow(false);
  }

  return (
    <div>
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
                    <td>MAD {Number(order.totalPrice).toFixed(2)}</td>
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
                    <td> {Number(item.purchasePrice).toFixed(2)} MAD</td>
                    <td>{item.quantity}</td>
                    <td>
                      {Number(item.purchasePrice * item.quantity).toFixed(2)} MAD 
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
}
