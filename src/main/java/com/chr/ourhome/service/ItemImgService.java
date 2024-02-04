package com.chr.ourhome.service;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.chr.ourhome.domain.item.ItemImg;
import com.chr.ourhome.domain.item.ItemImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
	
	@Value("${itemImgLocation}")
	private String itemImgLocation;
	
	private final ItemImgRepository itemImgRepository;
	private final FileService fileService;
	
	public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
		
		String oriImgName=itemImgFile.getOriginalFilename();
		String imgName="";
		String imgUrl="";
		
		// 파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName=fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl="/images/item/"+imgName;
		}
		
		// 상품 이미지 정보 저장
		itemImg.updateItemImg(oriImgName, imgName, imgUrl);
		itemImgRepository.save(itemImg);
	}
	
	public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
		
		// 상품 이미지를 수정했다면
		if (!itemImgFile.isEmpty()) {
			ItemImg savedItemImg=itemImgRepository.findById(itemImgId)
					.orElseThrow(EntityNotFoundException::new);
			
			// 기존 이미지 파일이 존재한다면 삭제
			if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
				String oriImgName=itemImgFile.getOriginalFilename();
				String imgName=fileService.uploadFile(itemImgLocation, 
						oriImgName, itemImgFile.getBytes());
				String imgUrl="/images/item/"+imgName;
				savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
			}
		}
	}
}
