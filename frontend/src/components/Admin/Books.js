// import { useState } from "react";
// import { popularProducts } from "../../BookData";
import BookTable from "./BookTable";
import useForm from "./../FormHook/useForm";
import adminService from "../../service/adminService";

const Books = ({ books, authors, categories, language,setBooks }) => {

  const validate = (values) => {
    let errors = {};
    if (!values.title) {
      errors.title = "title is required";
    }
    return errors;
  };

  const { values, errors, handleChange, handleSubmit } = useForm(
    submit,
    validate
  );
  function submit() {
    console.log("values", values);

    // alert("User Inserted...");
    adminService.addBook(
      values.title,
      values.desc,
      values.imageUrl,
      values.author,
      values.category,
      values.language,
      values.price
    );
  }

  // function onEdit(id){
  //   console.log("values", values);
  //   console.log("edit", id);
  // }
  function onDelete(id) {
    console.log("delete", id);
    adminService.deleteBook(id).then((res) => {
      console.log("delete", res);
      setBooks(books.filter((book) => book.id !== id));
    });
  }
  return (
    <>
      <div className="container mt-3" id="books">
        <h1 className="text-center mb-5 border-bottom">Books</h1>
        <div className="row">
          <div className="col-9">
            <button
              className="btn btn-outline-primary mb-2"
              data-bs-toggle="modal"
              data-bs-target="#bookFormModal"
            >
              Add New Book
            </button>
          </div>
          <div className="col-3">
            {/* <div> 
              <input
                className="form-control"
                type="search"
                placeholder="Search here"
              />
            </div>*/}
          </div>
        </div>
        <div class="table-responsive">
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Image</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Auther</th>
                <th scope="col">Price</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
              </tr>
            </thead>
            <tbody>
              {books.map((item) => (
                <BookTable
                  key={item.id}
                  id={item.id}
                  image={item.imageUrl}
                  alt={item.imageUrl}
                  title={item.title}
                  author={item?.author?.author}
                  desc={item.description}
                  price={item.price}
                  onDelete={() => onDelete(item.id)}
                />
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="modal" tabIndex="1" id="bookFormModal">
        <div className="modal-dialog modal-lg">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">Add New Book</h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <form onSubmit={handleSubmit} noValidate>
                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Title
                  </label>
                  <input
                    autoComplete="off"
                    className={`input ${
                      errors.title && "is-danger"
                    } form-control`}
                    type="text"
                    name="title"
                    onChange={handleChange}
                    value={values.title || ""}
                    required
                  />
                  {errors.title && (
                    <p className="help is-danger text-danger">{errors.title}</p>
                  )}
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Book Image
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    name="imageUrl"
                    id="imageUrl"
                    onChange={handleChange}
                    value={values.imageUrl || ""}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Auther
                  </label>
                  <select
                    name="author"
                    className="form-control"
                    onChange={handleChange}
                  >
                    <option value="">Select Author</option>
                    {authors.map((author) => (
                      <option key={author.id} value={author.id}>
                        {author.author}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Categories
                  </label>
                  <select
                    name="category"
                    className="form-control"
                    onChange={handleChange}
                  >
                    <option value="">Select Category</option>
                    {categories.map((category) => (
                      <option key={category.id} value={category.id}>
                        {category.name}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="mb-3">
                  <label
                    htmlFor="exampleInputEmail1"
                    className="form-label"
                    onChange={handleChange}
                  >
                    language
                  </label>
                  <select
                    name="language"
                    className="form-control"
                    onChange={handleChange}
                  >
                    <option value="">Select language</option>
                    {language.map((language) => (
                      <option key={language.id} value={language.id}>
                        {language.language}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Description
                  </label>
                  <textarea
                    className="form-control"
                    name="desc"
                    id="desc"
                    onChange={handleChange}
                    value={values.desc || ""}
                  ></textarea>
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Price
                  </label>
                  <input
                    autoComplete="off"
                    className={`input ${
                      errors.price && "is-danger"
                    } form-control`}
                    type="number"
                    name="price"
                    onChange={handleChange}
                    value={values.price || ""}
                    required
                  />
                  {errors.price && (
                    <p className="help is-danger text-danger">{errors.price}</p>
                  )}
                </div>
                <div className="modal-footer">
                  {/* <button
                    type="button"
                    className="btn btn-outline-secondary"
                    data-bs-dismiss="modal"
                  >
                    Close
                  </button> */}
                  <button type="submit" className="btn btn-outline-success">
                    Save
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Books;
