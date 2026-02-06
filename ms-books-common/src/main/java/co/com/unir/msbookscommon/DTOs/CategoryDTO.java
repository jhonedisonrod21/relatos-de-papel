package co.com.unir.msbookscommon.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryDTO {
    @NotNull
    @Min(value = 1, message = "category.categoryId debe ser mayor o igual a 1")
    @Schema(hidden = true)
    private Long categoryId;
    private String name;
}