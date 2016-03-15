package dao;

import java.util.List;

import entities.Book;

public interface BookService {
    public List<Book> getAvailableBooks();
    public Book getBook(int id);
    public List<Book> getAll();
}