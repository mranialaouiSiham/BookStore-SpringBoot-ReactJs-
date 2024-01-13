import React from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import adminService from "../../service/adminService";

const BookTable = ({ id, image, alt, title, author, desc, price, onEdit, onDelete }) => {
  console.log(id, image, alt, title, author, desc, price);
  return (
    <>
      <tr key={id}>
        <th scope="row">{id}</th>
        <td>
          <img src={image} alt={alt} height="100px" />
        </td>
        <td>{title}</td>
        <td>{desc}</td>
        <td>{author}</td>
        <td>{price}</td>
        <td>
          <Link
            className="btn btn-outline-primary"
            to={`/admin/book/update/${id}`}
          >
            Edit
          </Link>
        </td>
        <td>
          <Button className="btn btn-danger" onClick={onDelete}>
          Delete</Button>
        </td>
      </tr>
    </>
  );
};

export default BookTable;
