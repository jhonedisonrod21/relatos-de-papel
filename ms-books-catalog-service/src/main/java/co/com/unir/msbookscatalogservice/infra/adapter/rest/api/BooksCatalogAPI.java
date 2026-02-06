package co.com.unir.msbookscatalogservice.infra.adapter.rest.api;

import co.com.unir.msbookscommon.DTOs.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Validated
public interface BooksCatalogAPI {
    @GetMapping("/allBooks")
    @Operation(
            operationId = "Obtener lista todos los libros",
            description = "Operacion Get de lectura de datos",
            summary = "Se devuelve una lista de todos los libros almacenados en la base de datos.")
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    @ApiResponse(responseCode = "204",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    ResponseEntity<List<BookDTO>> getAllBooks(
            @RequestHeader Map<String, String> headers
    ) throws Exception;

    @GetMapping("/searchBooks")
    @Operation(
            operationId = "Buscar Libros",
            description = "Operacion Get de lectura de datos usando un query RSQL",
            summary = "Se devuelve una lista de todos los libros que cumplan con el criterio de busqueda.")
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    @ApiResponse(responseCode = "204",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    ResponseEntity<List<BookDTO>> searchBooks(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "filter", description = "Query de busqueda RSQL", example = "", required = false)
            @RequestParam(required = false) String filter
    )throws Exception;

    @GetMapping("/books/{bookId}")
    @Operation(
            operationId = "Buscar Libros",
            description = "Operacion Get de lectura de datos",
            summary = "Se devuelve el objeto encontrado que tenga el identificador especificado.")
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    @ApiResponse(responseCode = "404",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)),description = "No se ha encontrado el libro con el identificador indicado.")
    ResponseEntity<BookDTO>  getBookById(@PathVariable String bookId) throws Exception;

    @PatchMapping("/books/{bookId}")
    @Operation(
            operationId = "Modificar parcialmente un libro del catalogo",
            description = "Se modifica uno o muchos campos de un libro",
            summary = "Se modifica parcialmente un libro.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))))
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    @ApiResponse(responseCode = "404",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)),description = "No se ha encontrado el libro con el identificador indicado.")
    ResponseEntity<BookDTO>  patchBook(@PathVariable String bookId, @RequestBody BookDTO patchBook) throws Exception;

    @PutMapping("/books/{bookId}")
    @Operation(
            operationId = "Modificar totalmente un libro del catalogo",
            description = "Se modifica todos los campos de un libro",
            summary = "Se modifica un libro.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))))
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    @ApiResponse(responseCode = "404",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)),description = "No se ha encontrado el libro con el identificador indicado.")
    ResponseEntity<BookDTO>  updateBook(@PathVariable String bookId, @RequestBody @Valid BookDTO patchBook) throws Exception;

    @PostMapping("/books")
    @Operation(
            operationId = "Agregar un libro al catalogo",
            description = "Agregar un nuevo libro al catalogo",
            summary = "devuelve un objeto con la informacion guardada",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))))
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    @ApiResponse(responseCode = "400",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)),description = "Error de cliente")
    ResponseEntity<BookDTO>  addBook(@RequestBody @Valid BookDTO bookDTO);

    @DeleteMapping("/books/{bookId}")
    @Operation(
            operationId = "Eliminar Libros",
            description = "Operacion de eliminacion de libros",
            summary = "Se elimina el objeto asociado al identificador.")
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    @ApiResponse(responseCode = "404",content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)),description = "No se ha encontrado el libro con el identificador indicado.")
    ResponseEntity<Void> deleteBook(@PathVariable String bookId) throws Exception;
}
