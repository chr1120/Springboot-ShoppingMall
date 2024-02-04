package com.chr.ourhome.web.dto;

import org.modelmapper.ModelMapper;

import com.chr.ourhome.domain.item.ItemImg;

import lombok.Data;

@Data
public class ItemImgDto {

	private Long id;
	private String imgName;
	private String oriImgName;
	private String imgUrl;
	private String repImgYn;
	
	private static ModelMapper modelMapper=new ModelMapper();
	
	// ItemImg 엔티티 객체를 ItemImgDto 객체로 변환.
	public static ItemImgDto of(ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDto.class);
	}
}
