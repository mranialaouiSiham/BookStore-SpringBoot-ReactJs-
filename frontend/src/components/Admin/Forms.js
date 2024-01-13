import React from 'react'
import publicService from '../../service/publicService';
import useForm from ".././FormHook/useForm";

const Forms = ({authors,categories,books, language}) => {
    // console.log("form authors",authors);
    const {
        values,
        errors,
        handleChange,
        handleSubmit,
    } = useForm(submit);
    function submit() {
        // console.log('No errors, submit callback called!');
        alert("User Inserted...");
        //Call Register Api Here
    }
    return (
        <>
            <div className="modal" tabIndex="1" id="userFormModal">
                <div className="modal-dialog modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">User Registration Form</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleSubmit} noValidate>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Name</label>
                                    <input autoComplete="off" className={`input ${errors.name && 'is-danger'} form-control`} type="text" name="name" onChange={handleChange} value={values.name || ''} required />
                                    {errors.name && (
                                        <p className="help is-danger text-danger">{errors.name}</p>
                                    )}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
                                    <input autoComplete="off" className={`input ${errors.email && 'is-danger'} form-control`} type="email" name="email" onChange={handleChange} value={values.email || ''} required />
                                    {errors.email && (
                                        <p className="help is-danger text-danger">{errors.email}</p>
                                    )}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Phone Number</label>
                                    <input autoComplete="off" className={`input ${errors.phone && 'is-danger'} form-control`} type="number" name="phone" onChange={handleChange} value={values.phone || ''} required />
                                    {errors.email && (
                                        <p className="help is-danger text-danger">{errors.phone}</p>
                                    )}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Address</label>
                                    <input autoComplete="off" className={`input ${errors.address && 'is-danger'} form-control`} type="text" name="address" onChange={handleChange} value={values.address || ''} required />
                                    {errors.email && (
                                        <p className="help is-danger text-danger">{errors.phone}</p>
                                    )}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
                                    <div className="control">
                                        <input className={`input ${errors.password && 'is-danger'} form-control`} type="password" name="password" onChange={handleChange} value={values.password || ''} required />
                                    </div>
                                    {errors.password && (
                                        <p className="help is-danger text-danger">{errors.password}</p>
                                    )}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputPassword1" className="form-label">Confirm Password</label>
                                    <div className="control">
                                        <input className={`input ${errors.cpassword && 'is-danger'} form-control`} type="password" name="cpassword" onChange={handleChange} value={values.cpassword || ''} required />
                                    </div>
                                    {errors.cpassword && (
                                        <p className="help is-danger text-danger">{errors.cpassword}</p>
                                    )}
                                </div>
                            </form>

                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-outline-success">Save</button>
                        </div>
                    </div>
                </div>
            </div>
            <div className="modal" tabIndex="1" id="catFormModal">
                <div className="modal-dialog modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Add New Category</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleSubmit} noValidate>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Category Name</label>
                                    <input autoComplete="off" className={`input ${errors.name && 'is-danger'} form-control`} type="text" name="name" onChange={handleChange} value={values.name || ''} required />
                                    {errors.name && (
                                        <p className="help is-danger text-danger">{errors.name}</p>
                                    )}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Category Image</label>
                                    <input type="file" className="form-control" name="image" id="image" />
                                </div>
                            </form>

                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-outline-success">Save</button>
                        </div>
                    </div>
                </div>
            </div>

            {/* <div className="modal" tabIndex="1" id="bookFormModal">
                <div className="modal-dialog modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Add New Book</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={handleSubmit} noValidate>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Title</label>
                                    <input autoComplete="off" className={`input ${errors.title && 'is-danger'} form-control`} type="text" name="title" onChange={handleChange} value={values.title || ''} required />
                                    {errors.title && (
                                        <p className="help is-danger text-danger">{errors.title}</p>
                                    )}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Book Image</label>
                                    <input type="url" className="form-control" name="imageUrl" id="imageUrl" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Auther</label>
                                    <select name='author' className='form-control'>
                                        <option value="">Select Author</option>
                                        {authors.map(author => (
                                            <option key={author.id} value={author.id}>{author.author}</option>
                                        ))}
                                    </select>
                                </div>

                                
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Categories</label>
                                    <select name='author' className='form-control'>
                                        <option value="">Select Category</option>
                                        {categories.map(category => (
                                            <option key={category.id} value={category.id}>{category.name}</option>
                                        ))}
                                    </select>
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">language</label>
                                    <select name='author' className='form-control'>
                                        <option value="">Select language</option>
                                        {language.map(language => (
                                            <option key={language.id} value={language.id}>{language.language}</option>
                                        ))}
                                    </select>
                                </div>


                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Description</label>
                                    <textarea className="form-control" name="desc" id="desc" onChange={handleChange} value={values.author || ''} ></textarea>
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInputEmail1" className="form-label">Price</label>
                                    <input autoComplete="off" className={`input ${errors.price && 'is-danger'} form-control`} type="number" name="price" onChange={handleChange} value={values.price || ''} required />
                                    {errors.price && (
                                        <p className="help is-danger text-danger">{errors.price}</p>
                                    )}
                                </div>
                            </form>

                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-outline-success">Save</button>
                        </div>
                    </div>
                </div>
            </div> */}
        </>
    )
}

export default Forms
