package com.chr.ourhome.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {
	
	public String uploadFile(String uploadPath, String originalFileName,
												byte[] fileData) throws Exception {
	
		// UUID를 이용해서 파일명 새로 생성
		UUID uuid=UUID.randomUUID();
		String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName=uuid.toString()+extension;	// 파일명
		
		// 경로 + 파일명
		String fileUploadFullUrl=uploadPath+"/"+savedFileName;
		
		// FileOutputStream 객체를 이용하여 경로지정 후 파일 저장
		FileOutputStream fos=new FileOutputStream((fileUploadFullUrl));
		fos.write(fileData);
		fos.close();
		
		return savedFileName;
	}
	
	public void deleteFile(String filePath) throws Exception{
		
		File deleteFile=new File(filePath);
		
		if(deleteFile.exists()) {
			deleteFile.delete();
			log.info("파일을 삭제하였습니다.");
		} else {
			log.info("파일이 존재하지 않습니다.");
		}
	}
}
