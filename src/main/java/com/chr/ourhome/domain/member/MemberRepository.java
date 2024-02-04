package com.chr.ourhome.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동으로 된다.
public interface MemberRepository extends JpaRepository<Member, Integer> {
	// JPA query method
	Member findByEmail(String email);
}
