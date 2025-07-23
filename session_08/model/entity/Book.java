package com.data.session_08.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private double price;

    @ManyToOne
    //  khóa ngoại tham chiếu đến categories(CATEGORY_ID)
    @JoinColumn(name = "category_id")
    private Category category;
}
