package com.chr.ourhome.domain.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {
	// JPA query method
	List<Item> findByItemName(String itemName);
	
	@Query(value="SELECT * FROM Item i WHERE i.itemDetail" + 
	"LIKE %:itemDetail% ORDER BY i.price DESC", nativeQuery=true)
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
}
