package co.com.unir.msbooksordersmanagement.infra.adapter.rest.controller;

import co.com.unir.msbookscommon.DTOs.OrderDTO;
import co.com.unir.msbooksordersmanagement.application.OrdersManagementService;
import co.com.unir.msbooksordersmanagement.domain.model.Order;
import co.com.unir.msbooksordersmanagement.infra.adapter.rest.api.OrdersManagementAPI;
import co.com.unir.msbooksordersmanagement.infra.adapter.rest.mapper.OrdersMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Orders Controller", description = "")
public class OrdersManagementControllerImp implements OrdersManagementAPI {

    private OrdersMapper mapper;
    private OrdersManagementService ordersManagementService;


    @Override
    public ResponseEntity<List<OrderDTO>> searchOrders(String filter) throws Exception {
        return ResponseEntity.ok(mapper.orderListToDTO(ordersManagementService.findInOrders(filter)));
    }

    @Override
    public ResponseEntity<OrderDTO> getOrderById(String orderId) throws Exception {
        return ResponseEntity.ok(mapper.orderToDTO(ordersManagementService.getOrderById(Long.parseLong(orderId))));
    }

    @Override
    public ResponseEntity<OrderDTO> addOrder(OrderDTO orderDTO) throws Exception {

        return ResponseEntity.ok(mapper.orderToDTO(ordersManagementService.saveOrder(mapper.orderToEntity(orderDTO,new Order()))));
    }

    @Override
    public ResponseEntity<Void> deleteBook(String bookId) throws Exception {
        ordersManagementService.deleteOrder(Long.parseLong(bookId));
        return ResponseEntity.ok().build();
    }
    @Autowired
    public void setOrdersManagementService(OrdersManagementService ordersManagementService) {
        this.ordersManagementService = ordersManagementService;
    }
    @Autowired
    public void setMapper(OrdersMapper mapper) {
        this.mapper = mapper;
    }
}
