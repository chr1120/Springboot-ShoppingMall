package com.chr.ourhome.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.chr.ourhome.config.BaseEntity;
import com.chr.ourhome.constant.ItemSellStatus;
import com.chr.ourhome.handler.ex.OutOfStockException;
import com.chr.ourhome.web.dto.ItemFormDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Item extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "item_id")
    private Long id;	// 상품 코드
	
	@Column(nullable = false, length = 50)
    private String itemName;		// 상품명

    @Lob
    @Column(nullable = false)
    private String itemDetail;	// 상품 상세 설명

    @Column(nullable = false)
    private int price;	// 상품 가격
    
    @Column(nullable = false)
    private int stock;	// 재고 수량
    
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;		// 상품 판매 상태
    
    public void removeStock(int stock) {
    	
    	int restStock=this.stock-stock;
    	if(restStock<0) {
    		throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량: " + this.stock+ ")");
    	}
    	this.stock=restStock;
    }
    
    public void addStock(int stock) {
    	this.stock+=stock;
    }
    
    public void updateItem(ItemFormDto itemFormDto) {
    	this.itemName=itemFormDto.getItemName();
    	this.price=itemFormDto.getPrice();
    	this.stock=itemFormDto.getStock();
    	this.itemDetail=itemFormDto.getItemDetail();
    	this.itemSellStatus=itemFormDto.getItemSellStatus();
    }
}
