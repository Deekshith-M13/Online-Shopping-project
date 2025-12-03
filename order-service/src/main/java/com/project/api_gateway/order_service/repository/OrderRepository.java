package com.project.api_gateway.order_service.repository;

import com.project.api_gateway.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}