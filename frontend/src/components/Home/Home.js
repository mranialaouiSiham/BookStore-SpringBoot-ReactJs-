import React from 'react'
import Cart from '../Cart/Cart'
import { SliderItems, categories, popularProducts } from '../../BookData.js'
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';
import CartItems from '../Cart/CartItems';
import publicService from '../../service/publicService';

const Home = () => {

    const [products, setProducts] = React.useState([]);


    React.useEffect(() => {
        publicService.getBooks().then(res => {
            console.log(res);
            setProducts(res);
        });
    }, []);
    return (
        <>
            <AwesomeSlider animation="cubeAnimation" style={{ height: 500 }}>
                {SliderItems.map((item) => (
                    <div data-src={item.img} style={{ background: item.bg }} key={item.id}>
                        <h3>{item.title}</h3>
                        <p>{item.desc}</p>
                    </div>
                ))}
            </AwesomeSlider>
            <div className="container my-5">
                <h1 className="text-center mt-5">Categories</h1>
                <div className="row">
                    {categories.map((item) => (
                        <div className="col-lg-4 col-md-12 col-sm-12" key={item.id}>
                            <div className="card mt-5">
                                <div className="card-body">
                                    <img src={item.img} alt={item.img} style={{ width: '100%', height: '200px' }} />
                                </div>
                                <div className="card-footer d-flex align-items-center justify-content-between">
                                    {item.title}
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
                <h1 className="text-center mt-5">Popular Products</h1>
                <div className="row">
                    {products.map((item) => (
                        <Cart key={item.id} image={item.imageUrl} alt={item.imageUrl} title={item.title} item={item} />
                    ))}
                </div>
                {/* <CartItems /> */}
            </div>
        </>
    )
}

export default Home
