package com.retailer.rewards.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.rewards.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
