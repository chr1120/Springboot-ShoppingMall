package com.chr.ourhome.domain.orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.chr.ourhome.config.BaseEntity;
import com.chr.ourhome.constant.OrderStatus;
import com.chr.ourhome.domain.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name="orders_id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	private LocalDateTime ordersDate; // 주문일
	
	@Enumerated(EnumType.STRING)
	private OrderStatus ordersStatus; // 주문 상태
	
	@OneToMany(mappedBy="orders")	// order_item 테이블의 orders 필드에 매핑
	private List<OrderItem> orderItems=new ArrayList<>();
}
