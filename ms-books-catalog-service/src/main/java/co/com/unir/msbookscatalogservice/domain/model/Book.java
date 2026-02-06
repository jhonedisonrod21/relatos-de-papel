package co.com.unir.msbookscatalogservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(name = "AUTHOR",length = 200,nullable = false)
    private String author;
    @Column(name = "TITLE",length = 200,nullable = false)
    private String title;
    @Column(name = "ISBN",length = 200,nullable = false)
    private String isbnCode;
    @Column(name = "PUBLISHING_DATE",nullable = false)
    private Date publishingDate;
    @Column(name = "VISIBLE",nullable = false)
    private boolean visible;
    @Column(name = "STOCK",nullable = false)
    private int stock;
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "UPDATED_AT", nullable = false)
    private Instant updatedAt;
    @Column(name = "POSITIVE_REVIEWS", nullable = false)
    private Long positiveReviews;
    @Column(name = "NEGATIVE_REVIEWS", nullable = false)
    private Long negativeReviews;
    @Column(name = "DESCRIPTION",length = 500)
    private String description;
    @Column(name = "UNIT_PRICE",nullable = false, precision = 20, scale = 2)
    @Digits(integer = 18, fraction = 2)
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = Instant.now();
    }

    @PrePersist
    private void onCreate(){
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.positiveReviews = 0L;
        this.negativeReviews = 0L;
        this.visible = true;
    }
}
