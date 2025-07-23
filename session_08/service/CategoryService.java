package com.data.session_08.service;

import com.data.session_08.model.entity.Category;
import com.data.session_08.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy danh sách phân trang và tìm kiếm
    public List<Category> findAll(int page, int size, String searchName) {
        int offset = (page - 1) * size;
        return categoryRepository.getAllCategories(offset, size, searchName);
    }

    // Đếm tổng số phần tử phục vụ phân trang
    public long countTotalElement(String searchName) {
        return categoryRepository.countTotalElement(searchName);
    }

    // Lưu danh mục mới
    public boolean saveCategory(Category category) {
        return categoryRepository.saveCategory(category);
    }

    // Cập nhật danh mục
    public boolean updateCategory(Long id, Category category) {
        return categoryRepository.updateCategory(category);
    }

    // Tìm danh mục theo ID
    public Category findById(long id) {
        return categoryRepository.getCategoryById(id);
    }

    // Xóa danh mục theo ID
    public boolean deleteCategoryById(long id) {
        return categoryRepository.deleteCategoryById(id);
    }
}
