package com.chr.ourhome.domain.cart;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.chr.ourhome.config.BaseEntity;
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
public class Cart extends BaseEntity{

	@Id // Primary Key // entity에 대한 식별자
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 번호 증가 전략이 DB를 따라간다.
	@Column(name = "cart_id")
    private Long id;
	
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;
	
}
