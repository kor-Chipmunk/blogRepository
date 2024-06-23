package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<SecureCard, Long> {
}
