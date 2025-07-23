package com.data.session_08.service;

import com.data.session_08.model.entity.Book;
import com.data.session_08.repository.BookRepository;
import com.data.session_08.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceImpl {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(int offset, int limit, String search, String sort) {
        return bookRepository.getAllBooks(offset, limit, search, sort);
    }

    public long countTotalBooks(String search) {
        return bookRepository.countTotalBooks(search);
    }

    public Boolean addBook(Book book) {
        // có thể thêm logic kiểm tra nghiệp vụ tại đây nếu cần
        return bookRepository.addBook(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            bookRepository.updateBook(existingBook);
        }
        return existingBook;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.saveBook(book);
    }
}
