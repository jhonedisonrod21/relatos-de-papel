package co.com.unir.msbookscatalogservice.application;

import co.com.unir.msbookscatalogservice.domain.model.Book;
import co.com.unir.msbookscommon.DTOs.BookDTO;

import java.util.List;

public interface BookService {
    Book getBookById(Long id) throws Exception;
    List<Book> findInBooks(String filter) throws Exception;
    List<Book> findAllBooks() throws Exception;
    void deleteBook(Long Id) throws Exception;
    Book saveBook(BookDTO book);
    Book updateBook(BookDTO book,Long id) throws Exception;
}
