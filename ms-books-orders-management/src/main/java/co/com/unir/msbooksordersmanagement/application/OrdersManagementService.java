package co.com.unir.msbooksordersmanagement.application;

import co.com.unir.msbooksordersmanagement.domain.model.Order;

import java.util.List;

public interface OrdersManagementService {

    Order saveOrder(Order newOrder) throws Exception;
    Order getOrderById(Long id) throws Exception;
    void deleteOrder(Long id) throws Exception;
    List<Order> findInOrders(String filter) throws Exception;

}
