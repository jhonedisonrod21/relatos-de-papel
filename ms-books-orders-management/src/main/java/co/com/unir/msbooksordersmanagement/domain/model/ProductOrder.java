package co.com.unir.msbooksordersmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PRODUCT_ORDERS")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOrderId;
    @Column(name = "BOOK_ID",length = 200,nullable = false)
    private String bookId;
    @Column(name = "NO_UNITS",nullable = false)
    private Integer noUnits;
    @Column(name = "UNIT_PRICE",nullable = false, precision = 20, scale = 2)
    @Digits(integer = 18, fraction = 2)
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal unitPrice;
}
