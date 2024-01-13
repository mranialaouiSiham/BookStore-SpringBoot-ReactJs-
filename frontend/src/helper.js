import { toast } from 'react-toastify';


const KEY = "user";
function setUser(token) {
  localStorage.setItem(KEY, token);
}

function getUser() {
  return localStorage.getItem(KEY);
}

function isAdmin(){
    if(getUser()){
       console.log(getUser());
        return JSON.parse(getUser()).roles[0]==="ROLE_ADMIN";
    }
    return false;
}
function removeUser() {
  localStorage.removeItem(KEY);
}

function getToken() {
  if (getUser()) {
    return JSON.parse(getUser()).accessToken;
  }
  return null;
}

function logout(){
    removeUser();
    window.location.replace("/login");
}
function getHeader() {
  return {
    headers: {
      Authorization: "Bearer " + getToken(),
    },
  };
}

function errorhandler(error,defaultMessage="Unknown error"){
    if(error.response?.data?.message){
        if(error.response?.data?.fieldName){
            toast.error(`${error.response.data.fieldName} ${error.response.data.message}`);
        }else{
        toast.error(error.response.data.message);
        }
    }else{
      if(error?.response?.data?.error){
        toast.error(error.response.data.error);
      }else{

        toast.error(defaultMessage);
      }
    }
}

export { setUser, getUser, removeUser, getToken, getHeader, errorhandler,logout, isAdmin };