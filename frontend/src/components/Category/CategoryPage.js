import React from "react";
import { useParams } from "react-router-dom";
import publicService from "../../service/publicService";
import Cart from "./../Cart/Cart";

export default function CategoryPage() {
  const { name } = useParams();

  const [products, setProducts] = React.useState([]);
  React.useEffect(() => {
    console.log("CategoryPage", name);
    publicService.getBookByCategory(name).then((res) => {
      console.log(res);
      setProducts(res);
    });
  }, [name]);
  return (
    <div className="container-lg">
      <h1 className="text-center mt-5">{name}</h1>
      <div className="row">
        {products.map((item) => (
          <Cart
            key={item.id}
            image={item.imageUrl}
            alt={item.imageUrl}
            title={item.title}
            item={item}
          />
        ))}
      </div>
    </div>
  );
}
