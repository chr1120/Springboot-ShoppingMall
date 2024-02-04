package com.chr.ourhome.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.chr.ourhome.constant.ItemSellStatus;
import com.chr.ourhome.domain.item.Item;

import lombok.Data;

@Data
public class ItemFormDto {
	
	private Long id;
	
	private String itemName;
	
	private Integer price;
	
	private String itemDetail;
	
	private Integer stock;
	
	private ItemSellStatus itemSellStatus;
	
	private List<ItemImgDto> itemImgDtoList=new ArrayList<>();
	private List<Long> itemImgIds= new ArrayList<>();
	
	private static ModelMapper modelMapper=new ModelMapper();
	
	// DTO -> Entity
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}
	
	// Entity -> Dto
	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
}
