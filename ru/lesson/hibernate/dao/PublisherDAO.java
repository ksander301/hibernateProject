package ru.lesson.hibernate.dao;

import ru.lesson.hibernate.model.Book;
import ru.lesson.hibernate.model.Publisher;

import java.util.List;
// add any comment
public interface PublisherDAO {
    Publisher getPublisherById(Long id);

    void deletePublisher(Long id);

    Publisher savePublisher(Publisher publisher);

    List<Publisher> getAllPublisher();

    Publisher updatePublisher(Publisher publisher);
}
