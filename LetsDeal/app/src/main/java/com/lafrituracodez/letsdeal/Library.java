package com.example.joansquintero.myapplication;

public class Library {

    private String book_title;
    private String book_info;
    private int book_imageRes;
    private String book_price;

    public Library(String bookTitle, String title, String info, String price, int resourceId) {
        this.book_title = title;
        this.book_info = info;
        this.book_price = price;
    }

    public Library(String title, String info, String price, int imgRes) {
        this.book_title = title;
        this.book_info = info;
        this.book_price = price;
        book_imageRes = imgRes;
    }

    public String getTitle() {
        return book_title;
    }

    public String getInfo() {
        return book_info;
    }

    public int getImageId() {
        return book_imageRes;
    }

    public String getPrice() { return book_price; }

}
