package ru.lesson.hibernate.dao.impl;

import org.hibernate.Session;
import ru.lesson.hibernate.dao.AuthorDAO;
import ru.lesson.hibernate.model.Author;
import ru.lesson.hibernate.model.Publisher;
import ru.lesson.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    private Session currentSession;

    public AuthorDAOImpl() {
        this.currentSession = this.openCurrentSession();
    }

    private Session openCurrentSession() {
        return HibernateUtil.getSessionFactory().openSession();

    }

    public void closeCurrentSession() {
        this.currentSession.close();
    }

    public Session getCurrentSession() {
        if (this.currentSession.equals(null) || !this.currentSession.isOpen())
            this.currentSession = this.openCurrentSession();
        return currentSession;


    }

    public Author getAuthorById(Long id) {
        Session session;
        Author author = null;
        try {
            session = getCurrentSession();
            author = (Author) session.load(Author.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }
        return author;

    }

    public void deleteAuthor(Long id) {
        Session session;
        Author author;
        try {
            session = getCurrentSession();
            session.beginTransaction();
            author = (Author) session.load(Author.class, id);
            session.delete(author);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }

    }

    public Author saveAuthor(Author author) {
        Session session;
        try {
            session = getCurrentSession();
            session.beginTransaction();
            session.save(author);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }
        return author;
    }

    public List<Author> getAllPublisher() {
        Session session;
        List<Author> authors = new ArrayList<Author>();
        try {
            session = getCurrentSession();
            authors = session.createCriteria(Author.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }
        return authors;
    }

    public Author updateAuthor(Author author) {
        Author author1;
        Session session;
        try {

            session = getCurrentSession();
            session.beginTransaction();
            session.update(author);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }

        return author;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.currentSession.isOpen())
            this.currentSession.close();
    }
}
