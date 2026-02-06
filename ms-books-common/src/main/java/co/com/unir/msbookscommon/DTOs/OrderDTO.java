package co.com.unir.msbookscommon.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    @Schema(hidden = true)
    private String orderId;
    @Schema(hidden = true)
    private Instant orderDate;
    @Schema(hidden = true)
    private Instant deliveryDate;
    @Schema(hidden = true)
    private Integer totalAmount;
    @NotEmpty(message = "La lista orderItems debe tener almenos un item")
    private List<ProductOrderDTO> orderItems;
    @Valid
    @NotNull(message = "El campo shipmentsDetails es obligatorio")
    private ShipmentDetailsDTO shipmentsDetails;
}
