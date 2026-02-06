package co.com.unir.msbooksordersmanagement.domain.repository;

import co.com.unir.msbooksordersmanagement.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
interface OrdersRepositoryJPA extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>, QuerydslPredicateExecutor<Order> {
}
