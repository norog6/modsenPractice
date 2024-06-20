package com.modsen.practise.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    @Column(name = "quantity_of_products")
    private int quantityOfProducts;

}
