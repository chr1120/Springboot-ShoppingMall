package com.chr.ourhome.domain.orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Entity
public class OrderItem extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name="orders_item_id")
	private Long id;
	
	// 연관 관계가 맺어진 Entity의 fetch 전략을 모두 LAZY로 설정!
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orders_id")
	private Orders orders;
	
	private int ordersPrice;	// 주문가격
	private int count;	// 수량
	
}
