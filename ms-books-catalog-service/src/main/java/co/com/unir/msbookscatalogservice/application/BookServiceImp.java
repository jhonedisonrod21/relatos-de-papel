package co.com.unir.msbookscatalogservice.application;

import co.com.unir.msbookscatalogservice.domain.exception.BookNotFoundException;
import co.com.unir.msbookscatalogservice.domain.model.Book;
import co.com.unir.msbookscatalogservice.domain.repository.BookRepository;
import co.com.unir.msbookscatalogservice.infra.adapter.rest.mapper.BookMapper;
import co.com.unir.msbookscommon.DTOs.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService{

    private BookRepository repo;


    @Override
    public Book getBookById(Long id) throws Exception {
        Book bk = repo.getBookById(id);
        if (bk.isVisible())  {
            return bk;
        }else{
            throw new BookNotFoundException();
        }
    }

    @Override
    public List<Book> findInBooks(String filter) throws Exception {
        return repo.findInBooks(filter);
    }

    @Override
    public List<Book> findAllBooks() throws Exception {
        return repo.findAllBooks();
    }

    @Override
    public void deleteBook(Long id) throws Exception {
        repo.deleteBook(repo.getBookById(id));
    }

    @Override
    public Book saveBook(BookDTO book) {
        return repo.saveBook(BookMapper.INSTANCE.bookToEntity(book,new Book()));
    }

    @Override
    public Book updateBook(BookDTO book, Long id) throws Exception{
        return repo.saveBook(BookMapper.INSTANCE.bookToEntity(book,repo.getBookById(id)));
    }

    @Autowired
    public void setRepo(BookRepository repo) {
        this.repo = repo;
    }


}
