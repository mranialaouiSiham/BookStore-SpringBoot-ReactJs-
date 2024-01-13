import React from 'react'
import { user } from '../../BookData'
import UserTable from './UserTable'


const Users = ({users}) => {
    console.log("users", users)
    return (
        <>
            <div className="container mt-3" id="users">
                <h1 className="text-center mb-5 border-bottom">User Management</h1>
                <button className="btn btn-outline-primary mb-2" data-bs-toggle="modal" data-bs-target="#userFormModal">Add New User</button>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Mobile No</th>
                                <th scope="col">Address</th>
                                <th scope="col">Role</th>
                                <th scope="col">Password</th>
                                <th scope="col">Edit</th>
                                <th scope="col">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {user.map((item) => (
                                <UserTable key={item.id} id={item.id} name={item.name} email={item.email} phone={item.phone} address={item.address} role={item.role} password={item.password} />
                            ))}

                        </tbody>
                    </table>
                </div>
            </div>
        </>
    )
}

export default Users