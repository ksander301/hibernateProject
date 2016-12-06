package ru.lesson.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lesson.hibernate.dao.PublisherDAO;
import ru.lesson.hibernate.model.Book;
import ru.lesson.hibernate.model.Publisher;
import org.hibernate.SessionFactory;
import ru.lesson.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class PublisherDAOImpl implements PublisherDAO {
    private Session currentSession;


    public PublisherDAOImpl() {
        this.currentSession=this.openCurrentSession();
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


    public Publisher getPublisherById(Long id) {
        Publisher publisher = null;
        Session session;
        try {
            session = getCurrentSession();
            publisher = (Publisher) session.load(Publisher.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }
        return publisher;
    }

    public void deletePublisher(Long id) {
        Session session;
        Publisher publisher;
        try {
            session = getCurrentSession();
            session.beginTransaction();
            publisher = (Publisher) session.load(Publisher.class, id);
            session.delete(publisher);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }
    }

    public Publisher savePublisher(Publisher publisher) {
        Session session = null;

        try {

            session = getCurrentSession();
            session.beginTransaction();
            session.save(publisher);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }
        return publisher;
    }

    public List<Publisher> getAllPublisher() {
        Session session;
        List<Publisher> publishers = new ArrayList<Publisher>();
        try {
            session = getCurrentSession();
            publishers = session.createCriteria(Publisher.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }

        return publishers;
    }

    public Publisher updatePublisher(Publisher publisher) {
        Session session = null;
        try {
            session = getCurrentSession();
            session.beginTransaction();
            session.update(publisher);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.getCurrentSession().close();
        }
        return publisher;
    }


}
