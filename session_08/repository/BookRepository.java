package com.data.session_08.repository;

import com.data.session_08.model.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Book> getAllBooks(int offset, int limit, String search, String sort) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM Book b WHERE (:search is null or b.title LIKE :search or b.author LIKE :search)";
            if ("asc".equalsIgnoreCase(sort)) {
                hql += " ORDER BY b.price ASC";
            } else if ("desc".equalsIgnoreCase(sort)) {
                hql += " ORDER BY b.price DESC";
            }

            return session.createQuery(hql, Book.class)
                    .setParameter("search", "%" + (search == null ? "" : search) + "%")
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } finally {
            session.close();
        }
    }

    public long countTotalBooks(String search) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "SELECT count(b) FROM Book b WHERE (:search is null or b.title LIKE :search or b.author LIKE :search)";
            return session.createQuery(hql, Long.class)
                    .setParameter("search", "%" + (search == null ? "" : search) + "%")
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public Boolean addBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    //  Tìm sách theo ID
    public Book getBookById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Book.class, id);
        } finally {
            session.close();
        }
    }

    //  Cập nhật sách
    public boolean updateBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    //  Xóa sách theo ID
    public boolean deleteBook(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            Book book = session.get(Book.class, id);
            if (book == null) return false;

            transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
    // Trả về danh sách tất cả sách (không phân trang, tìm kiếm)
    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Book", Book.class).list();
        } finally {
            session.close();
        }
    }

    // Lưu sách (trả về Book vừa lưu)
    public Book saveBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(book);
            tx.commit();
            return book;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
