package co.com.unir.msbookscatalogservice.domain.repository;

import co.com.unir.msbookscatalogservice.domain.exception.BookNotFoundException;
import co.com.unir.msbookscatalogservice.domain.exception.EmptyBookListException;
import co.com.unir.msbookscatalogservice.domain.model.Book;
import io.github.perplexhub.rsql.RSQLSupport;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
@NoArgsConstructor
class BookRepositoryImp implements BookRepository {

    private BookRepositoryJPA jpa;

    @Autowired
    public void setJpa(BookRepositoryJPA jpa){
        this.jpa = jpa;
    }

    @Override
    public Book getBookById(Long id) throws BookNotFoundException {
        return this.jpa.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> findInBooks(String filter) throws EmptyBookListException {
        String validFilter = filter.isEmpty()?"visible==true":filter+";visible==true";
        List<Book> resp = this.jpa.findAll(RSQLSupport.toSpecification(validFilter));
        if(!resp.isEmpty()){
            return resp;
        }else{
            throw new EmptyBookListException();
        }
    }

    @Override
    public List<Book> findAllBooks() throws EmptyBookListException {
        List<Book> resp = this.jpa.findAll(RSQLSupport.toSpecification("visible==true"));
        if(!resp.isEmpty()){
            return resp;
        }else{
            throw new EmptyBookListException();
        }
    }

    @Override
    public void deleteBook(Book book) {
        jpa.delete(book);
    }

    @Override
    public Book saveBook(Book book) {
        return jpa.save(book);
    }

}
