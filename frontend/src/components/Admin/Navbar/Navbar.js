import React from 'react'
import { Link } from 'react-router-dom'

const Navbar = () => {
    return (
        <>
            <nav className="navbar navbar-expand-lg bg-primary">
                <div className="container">
                    <button className="navbar-toggler bg-white" type="button" data-bs-toggle="collapse" data-bs-target="#adminnav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="adminnav">
                        <ul className="navbar-nav mx-auto mb-2 mb-lg-0">
                            <li className="nav-item mx-5 px-2 border bg-light rounded-5">
                                <Link className="nav-link text-primary" to={"/admin"}>Dashboard</Link>
                            </li>
                            {/* <li className="nav-item mx-5 px-2 border rounded-5 bg-light">
                                <a className="nav-link text-primary" href="#users">User Management</a>
                            </li>
                            <li className="nav-item mx-5 px-2 border rounded-5 bg-light">
                                <a className="nav-link  text-primary" href="#categories">Categories</a>
                            </li> */}
                            <li className="nav-item mx-5 px-2 border rounded-5 bg-light">
                                <a className="nav-link  text-primary" href="#books">Books</a>
                            </li>
                            <li className="nav-item mx-5 px-2 border bg-light rounded-5">
                                <Link className="nav-link text-primary" to={"/admin/orders"}>Orders</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </>
    )
}

export default Navbar