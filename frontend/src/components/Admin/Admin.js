import NavBar from "./Navbar/Navbar";
import Dashboard from "./Dashboard";
// import Users from "./Users";
// import Categories from "./Categories";
import Books from "./Books";
import React from "react";
import Forms from "./Forms";
import publicService from "../../service/publicService";
const Admin = () => {
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
    })

  }, []);

  return (
    <>
      <NavBar />
      <Dashboard
        totalAuthor={author.length}
        totalCategory={categories.length}
        totalBooks={books.length}
      />
      
      
      <Books  authors={author} categories={categories} books={books} setBooks={setBooks} language={language} />
      <Forms authors={author} categories={categories} books={books} language={language}/>
    </>
  );
};

export default Admin;
