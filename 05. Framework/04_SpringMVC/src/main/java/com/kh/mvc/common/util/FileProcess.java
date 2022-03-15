package com.kh.mvc.common.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileProcess {
	//      ▼ static 의 장점 : new 해서 객체 안만들고도 클래스. 로 바로 사용 가능
	public static String save(MultipartFile upfile, String location) {
		String renamedFileName = null;
		String originalFileName = upfile.getOriginalFilename();

		// ▼ originalFileName 과 location 이 잘 넘어오는지 확인해보는 코드
		log.info("UpFile Name : {} ", originalFileName);
		log.info("location : {} ", location);
		
		// ▼ location 이 실제로 존재하지 않는 경우, 폴더를 생성하는 로직
		File folder = new File(location);
		
		if (!folder.exists()) {
			folder.mkdirs();
		}

		renamedFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"))
				+ originalFileName.substring(originalFileName.lastIndexOf("."));
		
		// ▼ 업로드한 파일 데이터를, 지정한 파일에 저장하는 코드
		try {
			// ▼ transferTo 메소드를 사용해,
			//   upfile 의 데이터를 File 객체에 설정한 파일 경로에 전송하는 코드
			upfile.transferTo(new File(location + "/" + renamedFileName));
		} catch (IllegalStateException | IOException e) {
			log.error("파일 전송 에러");
			e.printStackTrace();
		} 
		
		return renamedFileName;
	}

	public static void delete(String location) {
		log.info("location : {}", location);
		
		File file = new File(location);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
}