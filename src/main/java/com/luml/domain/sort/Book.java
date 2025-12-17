package com.luml.domain.sort;

import lombok.Data;

import java.util.Calendar;

/**
 * @author luml
 * @description
 * @date 2025/12/17
 */
@Data
public class Book {
    int id;
    String title;
    double price;
    Calendar publishDate;

    public Book(int id, String title, double price, Calendar publishDate) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return title + " (价格: " + price + ", 出版时间: " + publishDate.getTime() + ")";
    }

}
