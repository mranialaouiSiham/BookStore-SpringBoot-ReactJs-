import React from "react";
import publicService from "../../service/publicService";
import useForm from "./../FormHook/useForm";
import { useParams } from "react-router-dom";
import adminService from "../../service/adminService";

export default function UpdateBook() {
  const { id } = useParams();
  const [categories, setCategories] = React.useState([]);
  const [author, setauthor] = React.useState([]);
  const [books, setBooks] = React.useState([]);
  const [language, setLanguage] = React.useState([]);

  React.useEffect(() => {
    publicService.getBooks().then((res) => {
      setBooks(res);
    });
    publicService.getCategory().then((res) => {
      setCategories(res);
    });
    publicService.getAuthors().then((res) => {
      console.log("authors", res);
      setauthor(res);
    });

    publicService.getLanguage().then((res) => {
      console.log("language", res);
      setLanguage(res);
    });

    publicService.getBook(id).then((res) => {
      console.log("book", res);
      setValues({
        title: res.title,
        desc: res.description,
        imageUrl: res.imageUrl,
        author: res.author?.id,
        language: res.language?.id,
        category: res.category?.id,
        price: res.price,
      });
    });
  }, []);
  const validate = (values) => {
    let errors = {};
    if (!values.title) {
      errors.title = "title is required";
    }
    return errors;
  };
  const { values, errors, handleChange, handleSubmit, setValues } = useForm(
    submit,
    validate
  );

  function submit() {
      adminService.updateBook(
        id,
        values.title,
        values.desc,
        values.imageUrl,
        values.author,
        values.category,
        values.language,
        values.price
        );
        
  }
  return (
    <div className="container-md">
      <p>update book</p>
      <form onSubmit={handleSubmit} noValidate>
        <div className="mb-3">
          <label htmlFor="exampleInputEmail1" className="form-label">
            Title
          </label>
          <input
            autoComplete="off"
            className={`input ${errors.title && "is-danger"} form-control`}
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
            value={values.author || ""}
          >
            <option value="">Select Author</option>
            {author.map((author) => (
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
            value={values.category || ""}
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
            value={values.language || ""}
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
            className={`input ${errors.price && "is-danger"} form-control`}
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
          <button
            type="button"
            className="btn btn-outline-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="submit" className="btn btn-outline-success">
            Save
          </button>
        </div>
      </form>
    </div>
  );
}
