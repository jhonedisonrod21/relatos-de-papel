package co.com.unir.msbookscatalogservice.domain.repository;

import co.com.unir.msbookscatalogservice.domain.exception.BookNotFoundException;
import co.com.unir.msbookscatalogservice.domain.exception.EmptyBookListException;
import co.com.unir.msbookscatalogservice.domain.model.Book;

import java.util.List;

public interface BookRepository {
    Book getBookById(Long id) throws BookNotFoundException;
    List<Book> findInBooks(String filter) throws EmptyBookListException;
    List<Book> findAllBooks() throws EmptyBookListException;
    void deleteBook(Book book);
    Book saveBook(Book book);
}
