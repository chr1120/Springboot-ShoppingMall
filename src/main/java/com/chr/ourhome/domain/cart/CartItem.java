package com.chr.ourhome.domain.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.chr.ourhome.config.BaseEntity;
import com.chr.ourhome.domain.item.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블을 생성
public class CartItem extends BaseEntity{
	
	@Id // Primary Key // entity에 대한 식별자
	@Column(name = "cart_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 번호 증가 전략이 DB를 따라간다.
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	private int count;
}
