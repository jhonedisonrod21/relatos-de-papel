package co.com.unir.msbookscommon.DTOs;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@Getter
@Setter
public class ProductOrderDTO {
    @Schema(hidden = true)
    private String productOrderId;
    @NotNull(message = "El campo bookId es obligatorio")
    private String bookId;
    @NotNull
    @Min(value = 1, message = "El campo noUnits debe ser mayor o igual a 1")
    private Integer noUnits;
    @NotNull
    private BigDecimal unitPrice;
}
