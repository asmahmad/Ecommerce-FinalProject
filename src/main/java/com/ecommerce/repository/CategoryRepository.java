package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query("select c from Category c where c.is_activated = true and c.is_deleted = false")
	List<Category> findAllByActivated();
	
	 /*Customer*/
    @Query("select new com.ecommerce.dto.CategoryDto(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id " +
            " where c.is_activated = true and c.is_deleted = false group by c.id")
    List<CategoryDto> getCategoryAndProduct();

}
