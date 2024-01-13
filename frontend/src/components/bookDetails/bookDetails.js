import React from "react";
import { useParams } from "react-router-dom";
import publicService from "../../service/publicService";
import Cart from "../Cart/Cart";
import { useCart } from "react-use-cart";
import { toast } from "react-toastify";

export default function BookDetails() {
  const { id } = useParams();
  const [book, setBook] = React.useState(null);
  const { addItem } = useCart();
  React.useEffect(() => {
    console.log("BookDetails", id);
    publicService.getBook(id).then((res) => {
      console.log(res);
      setBook(res);
    });
  }, [id]);

  return (
    <div className="container-lg">
      {book && (
        <>
          <h1 className="text-center mt-5">{book.title}</h1>
          <img
            src={book.imageUrl}
            alt={book.imageUrl}
            className="img-fluid img-thumbnail "
            style={{
              maxWidth: "200px",
            }}
          />
          <p>{book.description}</p>
          <p>₹{book.price}</p>
          <p className="text-danger">author: {book?.author?.author}</p>
          <p className="text-danger">language: {book?.language?.language}</p>
          <p className="text-danger">category {book?.category?.name}</p>
          <button
            className="btn btn-primary"
            onClick={() => {
              addItem(book);
              toast.success("Book added to cart");
            }}
          >
            Add to Cart
          </button>
        </>
      )}
    </div>
  );
}
