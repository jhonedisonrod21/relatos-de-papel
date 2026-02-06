package co.com.unir.msbookscommon.DTOs;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    @Schema(hidden = true)
    private Long bookId;
    @NotBlank(message = "El campo author es obligatorio")
    private String author;
    @NotBlank(message = "El campo title es obligatorio")
    private String title;
    @NotBlank(message = "El campo isbnCode es obligatorio")
    private String isbnCode;
    @NotNull(message = "El campo publishingDate es obligatorio")
    private Instant publishingDate;
    @PositiveOrZero(message = "stock debe ser mayor o igual a 0")
    private int stock;
    @Schema(hidden = true, description = "Opcional: se calcula en backend")
    private int rating;
    @NotBlank(message = "El campo description es obligatorio")
    private String description;
    @NotNull(message = "El campo category es obligatorio")
    @Valid
    private CategoryDTO category;
    @NotNull(message = "El campo unitPrice es obligatorio")
    private BigDecimal unitPrice;
}
