package co.com.unir.msbooksordersmanagement.domain.model;

import co.com.unir.msbooksordersmanagement.domain.util.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "ORDER_DATE",nullable = false)
    private Instant orderDate;

    @Column(name = "DELIVERY_DATE")
    private Instant deliveryDate;

    @Column(name = "TOTAL_AMOUNT",nullable = false, precision = 20, scale = 2)
    @Digits(integer = 18, fraction = 2)
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal totalAmount;

    @Column(name = "ORDER_STATUS",length = 2)
    private String orderStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<ProductOrder> orderItems;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SHIPMENT_DETAILS_ID")
    private ShipmentDetails shipmentsDetails;


    @PrePersist
    private void prePersist(){
        this.orderStatus = OrderStatus.PAYMENT_PENDING;
        this.orderDate = Instant.now();
        this.totalAmount = (orderItems == null ? java.util.List.<ProductOrder>of() : orderItems).stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getNoUnits())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
