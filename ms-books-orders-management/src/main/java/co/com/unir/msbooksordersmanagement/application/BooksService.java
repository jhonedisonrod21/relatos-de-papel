package co.com.unir.msbooksordersmanagement.application;

import co.com.unir.msbookscommon.DTOs.BookDTO;
import co.com.unir.msbooksordersmanagement.application.error.BooksServiceUnavailableException;
import co.com.unir.msbooksordersmanagement.application.error.ItemNotFoundException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@NoArgsConstructor
@Component
class BooksService {

    @Autowired
    RestTemplate booksTemplate;

    public static final String BOOKS_BASE_URL = "http://ms-books-gateway-server/ms-books-catalog-service/books";

    private ResponseEntity<BookDTO> getBookById(String bookId){
        return booksTemplate.getForEntity(BOOKS_BASE_URL+"/"+bookId,BookDTO.class);
    }

    public BookDTO getBook(String bookId) throws Exception {
        try {
            return booksTemplate.getForObject(BOOKS_BASE_URL + "/" + bookId, BookDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ItemNotFoundException("El Libro con Identificador " + bookId + " no fue encontrado");
        } catch (HttpClientErrorException e) {
            throw new BooksServiceUnavailableException("No fue posible consultar el catálogo de libros (HTTP " + e.getStatusCode().value() + ")");
        } catch (RestClientException e) {
            throw new BooksServiceUnavailableException("No fue posible consultar el catálogo de libros");
        }
    }

    public boolean bookInStock(String bookId,int demanded_amount) throws Exception{
        return (this.getBook(bookId).getStock() - demanded_amount) >= 0;
    }

}
