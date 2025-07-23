package com.data.session_08.service.impl;

import com.data.session_08.model.entity.Book;

import java.util.List;

public interface BookServiceImpl {
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book getBookById(Long id);
    List<Book> findAll(); // nếu chưa có
    Book saveBook(Book book); // nếu chưa có
}
