package com.bookstore.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.Author;
import com.bookstore.main.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthorService.class})
@ExtendWith(SpringExtension.class)
class AuthorServiceTest {
    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    void testAddAuthor() {
        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");
        when(this.authorRepository.save((Author) any())).thenReturn(author);
        assertSame(author, this.authorService.addAuthor("Name"));
        verify(this.authorRepository).save((Author) any());
        assertTrue(this.authorService.getAllAuthors().isEmpty());
    }

    @Test
    void testGetAuthor() {
        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");
        Optional<Author> ofResult = Optional.of(author);
        when(this.authorRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(author, this.authorService.getAuthor(123L));
        verify(this.authorRepository).findById((Long) any());
        assertTrue(this.authorService.getAllAuthors().isEmpty());
    }

    @Test
    void testGetAuthor2() {
        when(this.authorRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.authorService.getAuthor(123L));
        verify(this.authorRepository).findById((Long) any());
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");
        Optional<Author> ofResult = Optional.of(author);

        Author author1 = new Author();
        author1.setId(123L);
        author1.setAuthor("JaneDoe");
        when(this.authorRepository.save((Author) any())).thenReturn(author1);
        when(this.authorRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(author1, this.authorService.updateAuthor(123L, "Name"));
        verify(this.authorRepository).findById((Long) any());
        verify(this.authorRepository).save((Author) any());
        assertTrue(this.authorService.getAllAuthors().isEmpty());
    }

    @Test
    void testUpdateAuthor2() {
        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");
        when(this.authorRepository.save((Author) any())).thenReturn(author);
        when(this.authorRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.authorService.updateAuthor(123L, "Name"));
        verify(this.authorRepository).findById((Long) any());
    }

    @Test
    void testDeleteAuthor() {
        doNothing().when(this.authorRepository).deleteById((Long) any());
        this.authorService.deleteAuthor(123L);
        verify(this.authorRepository).deleteById((Long) any());
        assertTrue(this.authorService.getAllAuthors().isEmpty());
    }

    @Test
    void testCount() {
        when(this.authorRepository.count()).thenReturn(3L);
        assertEquals(3, this.authorService.count());
        verify(this.authorRepository).count();
        assertTrue(this.authorService.getAllAuthors().isEmpty());
    }

    @Test
    void testGetAllAuthors() {
        ArrayList<Author> authorList = new ArrayList<>();
        when(this.authorRepository.findAll()).thenReturn(authorList);
        List<Author> actualAllAuthors = this.authorService.getAllAuthors();
        assertSame(authorList, actualAllAuthors);
        assertTrue(actualAllAuthors.isEmpty());
        verify(this.authorRepository).findAll();
    }
}

