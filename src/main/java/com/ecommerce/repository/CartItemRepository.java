package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.domain.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	

}
