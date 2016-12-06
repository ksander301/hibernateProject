package ru.lesson.hibernate.dao;

import ru.lesson.hibernate.model.Author;

import java.util.List;

public interface AuthorDAO {
    Author getAuthorById(Long id);
    void deleteAuthor(Long id);
    Author saveAuthor(Author author);
    List<Author> getAllPublisher();
    Author updateAuthor(Author author);
}
