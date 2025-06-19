package com.retailer.rewards.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.rewards.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
