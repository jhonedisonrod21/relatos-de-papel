package co.com.unir.msbookscatalogservice.domain.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CATEGORIES")
public class Category {
    @Id
    private Long categoryId;
    private String name;
}
