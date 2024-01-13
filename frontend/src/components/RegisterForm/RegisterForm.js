import useForm from "../FormHook/useForm";
import validate from "./FormValidationRules";
import { Link } from "react-router-dom";
import authService from "../../service/authService";

const Form = () => {
  const { values, errors, handleChange, handleSubmit } = useForm(
    login,
    validate
  );

  function login() {
    console.log("No errors, submit callback called!");
    //Call Register Api Here
    authService.register(values.email, values.password);
  }

  return (
    <>
      <div className="container my-5 mx-auto">
        <div className="row mx-auto">
          <div className="col-9 mx-auto border rounded p-5">
            <h3 className="text-center">Registration Form</h3>
            <form onSubmit={handleSubmit} noValidate>
            
              <div className="mb-3">
                <label htmlFor="exampleInputEmail1" className="form-label">
                  Email address
                </label>
                <input
                  autoComplete="off"
                  className={`input ${
                    errors.email && "is-danger"
                  } form-control`}
                  type="email"
                  name="email"
                  onChange={handleChange}
                  value={values.email || ""}
                  required
                />
                {errors.email && (
                  <p className="help is-danger text-danger">{errors.email}</p>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="exampleInputPassword1" className="form-label">
                  Password
                </label>
                <div className="control">
                  <input
                    className={`input ${
                      errors.password && "is-danger"
                    } form-control`}
                    type="password"
                    name="password"
                    onChange={handleChange}
                    value={values.password || ""}
                    required
                  />
                </div>
                {errors.password && (
                  <p className="help is-danger text-danger">
                    {errors.password}
                  </p>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="exampleInputPassword1" className="form-label">
                  Confirm Password
                </label>
                <div className="control">
                  <input
                    className={`input ${
                      errors.cpassword && "is-danger"
                    } form-control`}
                    type="password"
                    name="cpassword"
                    onChange={handleChange}
                    value={values.cpassword || ""}
                    required
                  />
                </div>
                {errors.cpassword && (
                  <p className="help is-danger text-danger">
                    {errors.cpassword}
                  </p>
                )}
              </div>
              <button type="submit" className="btn btn-primary">
                Register
              </button>
            </form>

            <p className="mt-3">
              Already registered?{" "}
              <Link className="text-primary" to="/login">
                {" "}
                Click Here
              </Link>{" "}
              to Login
            </p>
          </div>
        </div>
      </div>
    </>
  );
};

export default Form;
