package ru.kata.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
