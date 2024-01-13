import React from 'react'

const UserTable = ({ id, name, email, phone, address, role, password }) => {

    return (
        <>
            <tr key={id}>
                <td scope="row">{id}</td>
                <td>{name}</td>
                <td>{email}</td>
                <td>{phone}</td>
                <td>{address}</td>
                <td>{role}</td>
                <td>{password}</td>
                <td><button className="btn btn-outline-primary">Edit</button></td>
                <td><button className="btn btn-outline-danger">Delete</button></td>
            </tr>
        </>
    )
}

export default UserTable