package co.com.unir.msbookscatalogservice.domain.repository;

import co.com.unir.msbookscatalogservice.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryJPA extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>, QuerydslPredicateExecutor<Book> {

}
