package co.com.unir.msbookscatalogservice.infra.adapter.rest.controller;

import co.com.unir.msbookscatalogservice.application.BookService;
import co.com.unir.msbookscatalogservice.infra.adapter.rest.api.BooksCatalogAPI;
import co.com.unir.msbookscatalogservice.infra.adapter.rest.mapper.BookMapper;
import co.com.unir.msbookscommon.DTOs.BookDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Books Controller", description = "")
class BooksCatalogControllerImp implements BooksCatalogAPI {

    private BookService service;

    @Override
    public ResponseEntity<List<BookDTO>> getAllBooks(Map<String, String> headers) throws Exception{
        return ResponseEntity.ok(BookMapper.INSTANCE.bookListToBookDTO(service.findAllBooks()));
    }

    @Override
    public ResponseEntity<List<BookDTO>> searchBooks(Map<String, String> headers, String filter) throws Exception{
        return ResponseEntity.ok(BookMapper.INSTANCE.bookListToBookDTO( service.findInBooks(filter)));
    }

    @Override
    public ResponseEntity<BookDTO> getBookById(String bookId) throws Exception {
        return ResponseEntity.ok(BookMapper.INSTANCE.bookToDTO(service.getBookById(Long.parseLong(bookId))));
    }

    @Override
    public ResponseEntity<BookDTO> patchBook(String bookId, BookDTO patchBook) throws Exception {
        return ResponseEntity.ok(BookMapper.INSTANCE.bookToDTO(service.updateBook(patchBook,Long.parseLong(bookId))));
    }

    @Override
    public ResponseEntity<BookDTO> updateBook(String bookId, BookDTO patchBook) throws Exception {
        return ResponseEntity.ok(BookMapper.INSTANCE.bookToDTO(service.updateBook(patchBook,Long.parseLong(bookId))));
    }

    @Override
    public ResponseEntity<BookDTO> addBook(BookDTO bookDTO) {
        return ResponseEntity.ok(BookMapper.INSTANCE.bookToDTO(service.saveBook(bookDTO)));
    }

    @Override
    public ResponseEntity<Void> deleteBook(String bookId) throws Exception {
        service.deleteBook(Long.parseLong(bookId));
        return ResponseEntity.ok().build();
    }


    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }
}
