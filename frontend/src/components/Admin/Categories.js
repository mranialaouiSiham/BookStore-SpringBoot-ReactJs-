import React from 'react'
import { categories } from '../../BookData'
import CatTable from './CatTable'

const Categories = () => {
    return (
        <>
            <div className="container mt-3" id="categories">
                <h1 className="text-center mb-5 border-bottom">Categories</h1>
                <button className="btn btn-outline-primary mb-2" data-bs-toggle="modal" data-bs-target="#catFormModal">New Category</button>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Image</th>
                                <th scope="col">Edit</th>
                                <th scope="col">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {categories.map((item) => (
                                <CatTable key={item.id} id={item.id} image={item.img} alt={item.img} title={item.title} />
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    )
}

export default Categories