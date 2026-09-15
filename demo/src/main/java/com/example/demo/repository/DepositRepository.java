package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DepositTransaction;

public interface DepositRepository extends JpaRepository<DepositTransaction, Long>{
    
}
