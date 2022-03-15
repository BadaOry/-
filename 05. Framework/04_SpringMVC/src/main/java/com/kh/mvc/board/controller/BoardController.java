package com.kh.mvc.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.FileProcess;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
// ▼ 메소드의 리턴 타입이 void 일 경우,
//  Mapping URL 을 유추해서 View 를 찾아줌	
//	@GetMapping("board/list")
//	public void list() {
//	}
	
	
	@GetMapping("/list")
//	public String list(
	public ModelAndView list(ModelAndView model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int count) {
//		int listCount = 0;
		PageInfo pageInfo = null;
		List<Board> list = null;
		
		log.info("page number : {}", page);
		
//		listCount = service.getBoardCount();
		pageInfo = new PageInfo(page, 10, service.getBoardCount(), count);
		list = service.getBoardList(pageInfo);
		 
		model.addObject("pageInfo", pageInfo);
		model.addObject("list", list);
		model.setViewName("board/list");
		
//		return "board/list";
		return model;
	}
	
	@GetMapping("/write")
	public String write() {
		
		return "board/write";
	}

	@PostMapping("/write")
	public ModelAndView write(ModelAndView model, 
//			HttpServletRequest request,
			@SessionAttribute(name = "loginMember") Member loginMember,
			@ModelAttribute Board board, @RequestParam("upfile") MultipartFile upfile) {
//		    @ModelAttribute Board board, @RequestParam("upfile") MultipartFile[] upfile) {
		
		int result = 0;
		
		log.info(board.toString());
		// ▼ 파일을 업로드 안하면 빈 문자열, 업로드 하면 "파일명" 출력하는 코드
		log.info("Upfile Name : {}", upfile.getOriginalFilename());
		// ▼ 파일을 업로드 안하면 true, 업로드 하면 false 출력하는 코드
		log.info("Upfile is Empty : {}", upfile.isEmpty());
		// ▼ 첨부파일을 2개 이상 쓰는 경우, 잘 가져오는지 콘솔에 찍어보는 코드
//		System.out.println(upfile[0].getOriginalFilename());
//		System.out.println(upfile[0].isEmpty());
//		System.out.println(upfile[1].getOriginalFilename());
//		System.out.println(upfile[1].isEmpty());
		
		
		// 1. 파일을 업로드 했는지 확인 후 파일을 저장 
		if (upfile != null && !upfile.isEmpty()) {
			// ▼ 실제 파일을 저장하는 로직 작성
			String location = null;
			String renamedFileName = null;
			
//			String location = request.getRealPath("resources");
//			String location = request.getSession().getServletContext().getRealPath("resources/upload/board");
			try {
				location = resourceLoader.getResource("resouces/upload/board").getFile().getPath();
				renamedFileName = FileProcess.save(upfile, location);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			System.out.println(location);
			
	
			// ▼ renamedFileName 이 잘 넘어오면 
			//   board 객체에 originalFileName 과 renamedFileName 을 저장하는 코드
			//   ▷ originalFileName, renamedFileName 은 DB 에 잘 저장할 것
			if(renamedFileName != null) {
				board.setOriginalFileName(upfile.getOriginalFilename());
				board.setRenamedFileName(renamedFileName);				
			}
		}  
	
		// 2. 작성한 게시글 데이터를 데이터 베이스에 저장
		board.setWriterNo(loginMember.getNo());
		result = service.save(board);
	
		if(result > 0) {
			model.addObject("msg", "게시글이 정상적으로 등록되었습니다.");
			model.addObject("location", "/board/list");
		} else {
			model.addObject("msg", "게시글 등록에 실패했습니다.");			
			model.addObject("location", "/board/write");
		}
		
//		model.setViewName("/board/write");
		model.setViewName("/common/msg");
		
		return model;
	}
	
	@GetMapping("/view")
	public ModelAndView  view(ModelAndView model,
			@RequestParam("no") int no) {
		
//		System.out.println(no);
		
		Board board = service.findBoardByNo(no);
		
		model.addObject("board", board);
		model.setViewName("board/view");
		
		return model;
	}
	
	/*
	  HttpEntity
	  	- SpringFramework 에서 제공하는 클래스
	  	- HTTP 요청 또는 응답에 해당하는 HTTP Header 와 HTTP Body 를 포함하는 클래스
	  	
	  ResponseEntity
	  	- HttpEntity 를 상속하는 클래스로, HTTP 응답 데이터를 포함하는 클래스
	  	- 개발자가 직접 HTTP Body, Header, Status 를 세팅해서 반환할 수 있음
	  	- 컨트롤러에서 ResponseEntity 를 리턴하게 되면
	  	  View 의 정보가 아닌 HTTP 정보를 반환하게 됨
	  	  
	  @ResponseBody 와의 차이점 
	  	- @ResponseBody 에서 header 값을 변경해야할 경우에는
	  	  파라미터로 Response 객체를 받아서, 이 객체에서 header 를 변경해야 함
	  	- ResponseEntity 에서는 객체를 생성한 뒤 객체에서 header 값을 변경시키면 됨
	  
	 */
	@GetMapping("/fileDown") 
	public ResponseEntity<Resource> fileDown(
			@RequestHeader(name = "user-agent") String userAgent,
			@RequestParam("oname") String oname, @RequestParam("rname") String rname) {
		String downName = null;
	
		try {
		// ▼ Resource 객체를 사용해 저장된 파일의 경로를 가져오는 코드
		Resource resource = resourceLoader.getResource("resources/upload/board/" + rname);
		// ▼ 브라우저별로 파일명 인코딩하기 위한 코드
		boolean isMSIE = userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Trident") != -1;
		
//		// ▼ ﻿일단 oanme, rname 콘솔에 찍어보는 코드
//		System.out.println(oname);
//		System.out.println(rname);
		
			if(isMSIE) {
				downName = URLEncoder.encode(oname, "UTF-8").replaceAll("\\+", "%20");
				
			} else {
				downName = new String(oname.getBytes("UTF-8"), "ISO-8859-1");			
			}
		
			return ResponseEntity.ok() // ▼ Spring 이 제공하는 HttpHeaders & MediaType 클래스를 사용해 상수값으로 conten_type 을 쉽게 가져옴
								 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
								 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + downName +"\"")
								 .body(resource);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			// ▼ 500 에러 주는 코드
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/update")
	public ModelAndView update(@SessionAttribute("loginMember")Member loginMember,
			ModelAndView model, @RequestParam("no") int no) {
		
		Board board = service.findBoardByNo(no);
		
		// ▼ 본인의 게시글에만 수정할 수 있도록 하는 if 문
		if (loginMember.getNo() == board.getWriterNo()) {
			model.addObject("board", board);
			model.setViewName("board/update");	
		} else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/board/list");
			model.setViewName("common/msg");		
		}
		
		return model;
	}
	
	@PostMapping("/update")
	public ModelAndView update (ModelAndView model, 
			@SessionAttribute("loginMember")Member loginMember,
			@ModelAttribute Board board, MultipartFile upfile) {
		
		int result;
		
//		System.out.println(board);

		// ▼ 본인의 게시글에만 수정할 수 있도록 하는 if 문
		if (loginMember.getId().equals(board.getWriterId())) {
			// ▼ 원래 올렸던 파일이 존재하는지 확인해주는 if 문
			if (upfile != null && !upfile.isEmpty()) {
				String renamedFileName = null;
				String location = null;
				
				try {
					// ▼ resourceLoader 통해서 물리적 위치 가져오는 코드
					location = resourceLoader.getResource("resources/upload/board").getFile().getPath();
					// ▼ FileProcess 가 save() 메소드 실행 후 가져오는 결과를 담아주는 코드
					renamedFileName = FileProcess.save(upfile, location);
					
					// ▼ 이미 renamedFileName 이 존재하면 delete 하는 if문
					if(board.getRenamedFileName() != null) {
						// ▼ 이전에 업로드 된 첨부파일을 삭제하는 로직
						FileProcess.delete(location + "/" + board.getRenamedFileName());
					}
			
					// ▼ save() 의 update 를 하고온 결과물인 renamedFileName 이 존재하면
					//   새로운 oname, rname 을 set 해주는 if 문 
					if(renamedFileName != null) {
						board.setOriginalFileName(upfile.getOriginalFilename());
						board.setRenamedFileName(renamedFileName);
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
			result = service.save(board);			
			
			if(result > 0) {
				model.addObject("msg", "게시글이 정상적으로 수정되었습니다.");
				model.addObject("location", "/board/view?no=" + board.getNo());
			} else {
				model.addObject("msg", "게시글 수정을 실패하였습니다.");
				model.addObject("location", "/board/update?no=" + board.getNo());
			}
		} else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/board/list");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView model,
			@SessionAttribute("loginMember")Member loginMember,
			@RequestParam("no") int no) {
		
		int result = 0;
		Board board = service.findBoardByNo(no);
		
		// ▼ service 가 가져온 writerNo 와 loginMember 의 no 가 일치하는지 확인하는 if 문
		if(board.getWriterNo() == loginMember.getNo()) {
			result = service.delete(board.getNo());
			
			if(result > 0) {
				model.addObject("msg", "게시글 삭제 성공");
				model.addObject("location", "/board/list");
			} else {
				model.addObject("msg", "게시글 삭제 실패");
				model.addObject("location", "/board/view?no=" + board.getNo());
			}
			 
		} else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/board/list");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}
}
