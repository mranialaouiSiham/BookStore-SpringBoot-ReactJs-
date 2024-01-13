import useForm from "../FormHook/useForm";
import validate from './LoginFormValidationRules';
import { Link } from "react-router-dom";
import authService from "../../service/authService";

const Form = () => {
    const {
        values,
        errors,
        handleChange,
        handleSubmit,
    } = useForm(login, validate);

    function login() {
        console.log('No errors, submit callback called!');
        //Call Login Api Here
        console.log(values);
        authService.login(values.email, values.password)
            .then(res => {
                console.log(res);
            }
            )
            .catch(err => {
                console.log(err);
            }
            )
        
    }

    return (
        <>
            <div className="container my-5 mx-auto">
                <div className="row mx-auto">
                    <div className="col-9 mx-auto border rounded p-5">
                        <h3 className="text-center">Login Form</h3>
                        <form onSubmit={handleSubmit} noValidate>
                            <div className="mb-3">
                                <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
                                <input autoComplete="off" className= {`input ${errors.email && 'is-danger'} form-control`} type="email" name="email" onChange={handleChange} value={values.email || ''} required />
                                        {errors.email && (
                                            <p className="help is-danger text-danger">{errors.email}</p>
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
                            <button type="submit" className="btn btn-primary">Login</button>
                        </form>

                        <p className="mt-3">To Create an Account Please  <Link className="text-primary" to="/register"> Click Here</Link></p>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Form;