package ru.kata.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
