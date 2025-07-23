package com.data.session_08.service;

import com.data.session_08.model.entity.Category;

public class Main {
    Category category = Category
            .builder()
            .id(1L)
            .cateName("Đồ gia dụng")
            .description("ngon bổ rẻ")
            .status(true)
            .build();
}
