package co.com.unir.msbooksordersmanagement.infra.adapter.rest.mapper;

import co.com.unir.msbookscommon.DTOs.OrderDTO;
import co.com.unir.msbookscommon.DTOs.ProductOrderDTO;
import co.com.unir.msbookscommon.DTOs.ShipmentDetailsDTO;
import co.com.unir.msbooksordersmanagement.domain.model.Order;
import co.com.unir.msbooksordersmanagement.domain.model.ProductOrder;
import co.com.unir.msbooksordersmanagement.domain.model.ShipmentDetails;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrdersMapper {

    OrderDTO orderToDTO(Order order);

    ProductOrderDTO productOrderToDTO(ProductOrder productOrder);

    ShipmentDetailsDTO shipmentDetailsToDTO(ShipmentDetails shipmentDetails);

    default List<OrderDTO> orderListToDTO(List<Order> ordersList){
        return ordersList.stream().map(this::orderToDTO).toList();
    }

    default List<ProductOrderDTO> productOrdersToDTO(List<ProductOrder> productOrders){
        return productOrders.stream().map(this::productOrderToDTO).toList();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order orderToEntity(OrderDTO orderDTO,@MappingTarget Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder productOrderToEntity(ProductOrderDTO productOrderDTO,@MappingTarget ProductOrder productOrder);

}
