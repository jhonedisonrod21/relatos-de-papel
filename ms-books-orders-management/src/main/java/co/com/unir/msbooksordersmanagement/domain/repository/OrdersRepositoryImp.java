package co.com.unir.msbooksordersmanagement.domain.repository;

import co.com.unir.msbooksordersmanagement.domain.exception.OrderNotFoundException;
import co.com.unir.msbooksordersmanagement.domain.model.Order;
import io.github.perplexhub.rsql.RSQLSupport;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
class OrdersRepositoryImp implements OrdersRepository{

    private OrdersRepositoryJPA jpa;


    @Override
    public Order saveOrder(Order newOrder) {
        return this.jpa.save(newOrder);
    }

    @Override
    public Order getOrderById(Long id) throws Exception {
        return this.jpa.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public void deleteOrder(Long id) throws Exception{
        jpa.delete(this.jpa.findById(id).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public List<Order> searchInOrders(String filter) throws Exception{
        return this.jpa.findAll(RSQLSupport.toSpecification(filter));
    }

    @Autowired
    public void setJpa(OrdersRepositoryJPA jpa) {
        this.jpa = jpa;
    }
}
