package co.com.unir.msbooksordersmanagement.domain.repository;

import co.com.unir.msbooksordersmanagement.domain.exception.OrderNotFoundException;
import co.com.unir.msbooksordersmanagement.domain.model.Order;

import java.util.List;

public interface OrdersRepository {
    Order saveOrder(Order newOrder);
    Order getOrderById(Long id) throws Exception;
    void deleteOrder(Long id) throws Exception;
    List<Order> searchInOrders(String filter) throws Exception;
}
