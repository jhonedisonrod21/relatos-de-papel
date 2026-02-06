package co.com.unir.msbookscommon.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ShipmentDetailsDTO {
    @Schema(hidden = true)
    private String shipmentDetailsId;
    @NotNull(message = "El campo shipmentsDetails.delivery_cellphone es obligatorio")
    private String delivery_cellphone;
    @NotNull(message = "El campo shipmentsDetails.delivery_address es obligatorio")
    private String delivery_address;
    @NotNull(message = "El campo shipmentsDetails.delivery_email es obligatorio")
    private String delivery_email;
    @NotNull(message = "El campo shipmentsDetails.delivery_name es obligatorio")
    private String delivery_name;
}
