package ru.lesson.hibernate.dao;


import ru.lesson.hibernate.model.Book;

import java.util.List;
// add any comment
public interface BookDAO {

    Book getBookById(Long id);
    List<Book> getAllBook();
    void deleteBook(Long id);
    Book addBook(Book book);
    Book updateBook(Book book);
}
