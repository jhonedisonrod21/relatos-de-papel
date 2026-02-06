package co.com.unir.msbooksordersmanagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "SHIPMENT_DETAILS")
public class ShipmentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentDetailsId;
    @Column(name = "DELIVERY_CELLPHONE",length = 200,nullable = false)
    private String delivery_cellphone;
    @Column(name = "DELIVERY_ADDRESS",length = 200,nullable = false)
    private String delivery_address;
    @Column(name = "DELIVERY_EMAIL",length = 200,nullable = false)
    private String delivery_email;
    @Column(name = "DELIVERY_NAME",length = 200,nullable = false)
    private String delivery_name;
}
