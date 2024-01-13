import React from 'react'

const CatTable = ({ id, image, alt, title }) => {

    return (
        <>
            <tr key={id}>
                <td scope="row">{id}</td>
                <td><img src={image} alt={alt} height="100px" /></td>
                <td>{title}</td>
                <td><button className="btn btn-outline-primary">Edit</button></td>
                <td><button className="btn btn-outline-danger">Delete</button></td>
            </tr>
        </>
    )
}

export default CatTable