package com.bookstore.main.services;

import com.bookstore.main.models.Author;
import com.bookstore.main.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

        @Autowired
        private AuthorRepository authorRepository;

        public Author addAuthor(String name) {
            Author author = new Author();
            author.setAuthor(name);
            return authorRepository.save(author);
        }

        public Author getAuthor(Long id) {
            return authorRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Author not found")
            );
        }

        public Author updateAuthor(Long id, String name) {
            Author author = getAuthor(id);
            author.setAuthor(name);
            return authorRepository.save(author);
        }

        public void deleteAuthor(Long id) {
            authorRepository.deleteById(id);
        }

        public int count(){
            return (int) authorRepository.count();
        }

        public List<Author> getAllAuthors(){
            return authorRepository.findAll();
        }

}
