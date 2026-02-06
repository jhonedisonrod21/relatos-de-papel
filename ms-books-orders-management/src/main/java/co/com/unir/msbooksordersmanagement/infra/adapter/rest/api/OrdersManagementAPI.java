package co.com.unir.msbooksordersmanagement.infra.adapter.rest.api;

import co.com.unir.msbookscommon.DTOs.OrderDTO;
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

@Validated
public interface OrdersManagementAPI {

    @GetMapping("/searchOrders")
    @Operation(
            operationId = "Buscar pedidos",
            description = "Operacion Get de lectura de datos usando un query RSQL",
            summary = "Se devuelve una lista de todos los pedidos que cumplan con el criterio de busqueda estipulado en el filtro RSQL.")
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    @ApiResponse(responseCode = "204",content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    ResponseEntity<List<OrderDTO>> searchOrders(            
            @Parameter(name = "filter", description = "Query de busqueda RSQL", example = "", required = false)
            @RequestParam(required = false) String filter
    )throws Exception;


    @GetMapping("/orders/{orderId}")
    @Operation(
            operationId = "Buscar pedidos",
            description = "Operacion Get de lectura de datos",
            summary = "Se devuelve el objeto encontrado que tenga el identificador especificado.")
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "404",content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),description = "No se ha encontrado el pedido con el identificador especificado.")
    ResponseEntity<OrderDTO>  getOrderById(@PathVariable String orderId) throws Exception;


    @PostMapping("/orders")
    @Operation(
            operationId = "Iniciar un pedido",
            description = "Se agrega un nuevo pedido para su procesamiento",
            summary = "devuelve un objeto con la informacion guardada",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del pedido a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))))
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "400",content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),description = "Error de cliente")
    ResponseEntity<OrderDTO>  addOrder(@RequestBody @Valid OrderDTO orderDTO) throws Exception;


    @DeleteMapping("/orders/{bookId}")
    @Operation(
            operationId = "Eliminar Pedido",
            description = "Operacion de eliminacion de pedidos",
            summary = "Se elimina el objeto asociado al identificador.")
    @ApiResponse(responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "404",content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),description = "No se ha encontrado el libro con el identificador indicado.")
    ResponseEntity<Void> deleteBook(@PathVariable String bookId) throws Exception;
}
