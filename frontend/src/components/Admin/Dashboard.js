import React from 'react'

const Dashboard = ({totalAuthor,totalCategory,totalBooks}) => {
    return (
        <>
            <div className="container mt-3" id="dashboard">
                <h1 className="text-center mb-5 border-bottom">Dashboard</h1>
                <div className="row d-flex justify-content-between">
                    <div className="col-lg-3 col-md-12 col-sm-12 m-3 border rounded d-flex justify-content-center align-items-center flex-column users">
                        <h5>Author</h5>
                        <h2>{totalAuthor}</h2>
                    </div>
                    <div className="col-lg-3 col-md-12 col-sm-12 m-3 border rounded d-flex justify-content-center align-items-center flex-column categories">
                        <h5>Categories</h5>
                        <h2>{totalCategory}</h2>
                    </div>
                    <div className="col-lg-3 col-md-12 col-sm-12 m-3 border rounded d-flex justify-content-center align-items-center flex-column books">
                        <h5>Books</h5>
                        <h2>{totalBooks}</h2>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Dashboard