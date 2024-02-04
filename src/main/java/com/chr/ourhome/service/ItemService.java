package com.chr.ourhome.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chr.ourhome.domain.item.Item;
import com.chr.ourhome.domain.item.ItemImg;
import com.chr.ourhome.domain.item.ItemImgRepository;
import com.chr.ourhome.domain.item.ItemRepository;
import com.chr.ourhome.web.dto.ItemFormDto;
import com.chr.ourhome.web.dto.ItemImgDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	private final ItemImgService itemImgService;
	private final ItemImgRepository itemImgRepository;
	
	public Long saveItem(ItemFormDto itemFormDto,
											List<MultipartFile> itemImgFileList) throws Exception {
		
		// 상품 등록 (1번)
		Item item=itemFormDto.createItem();
		itemRepository.save(item);
		
		// 이미지 등록	(2번, 순서 중요)
		for(int i=0; i<itemImgFileList.size(); i++) {
			ItemImg itemImg=new ItemImg();
			itemImg.setItem(item);
			if(i==0) {
				itemImg.setRepimgYn("Y");
			}else {
				itemImg.setRepimgYn("N");
			}
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		}
		return item.getId();
	}
	
	@Transactional(readOnly=true)
	public ItemFormDto getItemDtl(Long itemId) {
		
		// 상품 id를 기반으로 상품 이미지 엔티티 객체 가져옴
		List<ItemImg> itemImgList=itemImgRepository.findByItemIdOrderByIdAsc(itemId);
		
		// 상품 이미지 DTO 객체 담을 그릇 생성
		List<ItemImgDto> itemImgDtoList=new ArrayList<>();
		
		// 상품 이미지 엔티티 객체를 상품 이미지 DTO 객체로 변환
		for (ItemImg itemImg : itemImgList) {
			ItemImgDto itemImgDto=ItemImgDto.of(itemImg);
			itemImgDtoList.add(itemImgDto);
		}
		
		// 상품 id를 기반으로 상품 엔티티 객체 가져옴
		Item item=itemRepository.findById(itemId)
				.orElseThrow(EntityNotFoundException::new);
		
		// 상품 엔티티 객체를 상품 DTO 객체로 변환
		ItemFormDto itemFormDto=ItemFormDto.of(item);
		itemFormDto.setItemImgDtoList(itemImgDtoList);
		return itemFormDto;
	}
	
	public Long updateItem(ItemFormDto itemFormDto,
													List<MultipartFile> itemImgFileList) throws Exception {
		
		// 상품 수정
		Item item=itemRepository.findById(itemFormDto.getId())
						.orElseThrow(EntityNotFoundException::new);
		item.updateItem(itemFormDto);
		
		// 상품 이미지 수정
		List<Long> itemImgIds=itemFormDto.getItemImgIds();
		for(int i=0; i<itemImgFileList.size(); i++) {
			itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
		}
		return item.getId();
	}
}
