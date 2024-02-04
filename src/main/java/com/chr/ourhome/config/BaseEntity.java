package com.chr.ourhome.config;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(value= {AuditingEntityListener.class})
@MappedSuperclass
@Data
public abstract class BaseEntity extends BaseTimeEntity{
	
	@CreatedBy
	@Column(updatable=false)
	private String createdBy;
	
	@LastModifiedBy
	private String modifiedBy;
}
