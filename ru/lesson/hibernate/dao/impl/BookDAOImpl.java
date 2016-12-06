package ru.lesson.hibernate.dao.impl;

import org.hibernate.Session;
import ru.lesson.hibernate.dao.BookDAO;
import ru.lesson.hibernate.model.Book;
import ru.lesson.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    public Book getBookById(Long id) {
        Session session = null;
        Book book = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            book = (Book) session.load(Book.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!session.equals(null) && session.isOpen()) {
                session.close();
            }
        }
        return book;
    }

    public List<Book> getAllBook() {
        Session session = null;
        List<Book> books = new ArrayList<Book>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            books = session.createCriteria(Book.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return books;

    }

    public void deleteBook(Long id) {
        Session session = null;
        Book book = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            book = (Book) session.load(Book.class, id);
            session.delete(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Book addBook(Book book) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return book;
    }

    public Book updateBook(Book book) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
       return book;

    }
}
