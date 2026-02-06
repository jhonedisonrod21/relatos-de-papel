package co.com.unir.msbooksordersmanagement.application;


import co.com.unir.msbooksordersmanagement.application.error.NotEnoughStockException;
import co.com.unir.msbooksordersmanagement.domain.model.Order;
import co.com.unir.msbooksordersmanagement.domain.model.ProductOrder;
import co.com.unir.msbooksordersmanagement.domain.repository.OrdersRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OrdersManagementServiceImp implements OrdersManagementService{

    private OrdersRepository repo;
    private BooksService booksService;

    @SneakyThrows
    @Override
    public Order saveOrder(Order newOrder) throws Exception{
        for (var item : newOrder.getOrderItems()) {
            if(!productInStock(item)){
                throw new NotEnoughStockException("El item con id "+item.getBookId()+" no cuenta con suficiente stock");
            }
        }
        return this.repo.saveOrder(newOrder);
    }

    @Override
    public Order getOrderById(Long id) throws Exception {
        return this.repo.getOrderById(id);
    }

    @Override
    public void deleteOrder(Long id) throws Exception{
        this.repo.deleteOrder(id);
    }

    @Override
    public List<Order> findInOrders(String filter) throws Exception{
        return this.repo.searchInOrders(filter);
    }

    @Autowired
    public void setRepo(OrdersRepository repo) {
        this.repo = repo;
    }
    @Autowired
    public void setBooksService(BooksService booksService) {
        this.booksService = booksService;
    }

    private boolean productInStock(ProductOrder productOrder) throws Exception {
        return this.booksService.bookInStock(productOrder.getBookId(),productOrder.getNoUnits());
    }
}
