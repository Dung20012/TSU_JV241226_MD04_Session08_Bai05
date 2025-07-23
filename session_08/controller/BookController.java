package com.data.session_08.controller;

import com.data.session_08.model.entity.Book;
import com.data.session_08.model.entity.Category;
import com.data.session_08.service.BookService;
import com.data.session_08.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách sách có phân trang, tìm kiếm, sắp xếp
    @GetMapping
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "5") int size,
                            @RequestParam(defaultValue = "") String search,
                            @RequestParam(defaultValue = "ASC") String sort) {

        int offset = (page - 1) * size;
        List<Book> books = bookService.findAll(offset, size, search, sort);
        long totalBooks = bookService.countTotalBooks(search);
        int totalPages = (int) Math.ceil((double) totalBooks / size);

        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        model.addAttribute("sort", sort);

        return "/listBooks"; // View: /WEB-INF/views/book/listBooks.jsp
    }

    // Hiển thị form thêm sách
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        List<Category> categories = categoryService.findAll(1, 100, "");
        model.addAttribute("categories", categories);
        return "/addBook"; // View: /WEB-INF/views/book/addBook.jsp
    }

    // Xử lý thêm sách
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    // Hiển thị form sửa sách
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        List<Category> categories = categoryService.findAll(1, 100, "");
        model.addAttribute("categories", categories);
        return "/editBook"; // View: /WEB-INF/views/book/editBook.jsp
    }

    // Xử lý cập nhật sách
    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    // Xử lý xóa sách
    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
