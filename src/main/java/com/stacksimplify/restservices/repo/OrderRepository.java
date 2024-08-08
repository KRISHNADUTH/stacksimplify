package com.stacksimplify.restservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
