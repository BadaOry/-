package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.FileRename;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/board/write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardService service = new BoardService();

    public WriteServlet() {

    }
    
    // ▼ get 요청이 오면 write.jsp 를 포워딩해서 열어주는 메소드 
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	String viewName = "/views/board/write.jsp";
    	
    	// ▼ 로그인이 되어야만 글을 쓸 수 있게 하는 로직
    	if(loginMember == null) {
    		// ▼ viewName 변수 덕분에 if 문 두 번 안써도 됨 ㅎㅎ
    		viewName = "/views/common/msg.jsp";
    		request.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
			request.setAttribute("location", "/");
    	}
    	request.getRequestDispatcher(viewName).forward(request, response);
	}


    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	
    	// ▼ 파일이 저장될 경로
    	String path = getServletContext().getRealPath("/resources/upload/board");
    	
    	// ▼ 파일의 최대 사이즈 지정 (10MB)
    	int maxSize = 10485760;
    	
    	// ▼ 문자에 대한 인코딩 설정
    	String encoding = "UTF-8";
    	
    	/*
    	 * 	DefaultFileRenamePolicy
    	 * 		- 업로드되는 파일에 대한 rename 처리에 사용됨
    	 * 		- 파일 명이 중복되는 경우, 중복되는 이름 뒤에 0 ~ 9999 붙임
    	 */
    	// ▼ HttpServletRequest reuqest, String SaveDirectory, Int maxFontSize, String encoding 을 매개값으로 줌
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    	
    	
    	// ▼ form 파라미터로 넘어온 값들 (파일에 대한 정보 X)
    	String title = mr.getParameter("title");
    	String writer = mr.getParameter("writer");
    	String content = mr.getParameter("content");

//    	// ▼ 이렇게 가져와서 찍으면, String 타입으로 파일 명을 가져옴
//    	String upfile = mr.getParameter("upfile");
    	// ▼ 파일에 대한 정보를 가져올 때
    	String fileSystemName = mr.getFilesystemName("upfile");       // ◀ 서버에 저장된 이름
    	String originalFileName = mr.getOriginalFileName("upfile");   // ◀ 원본 이름
    	
    	// [ 로그인 여부 확인 ]
    	//   : 로그인이 안된 사용자가 게시글 작성이 불가능하도록 체크하는 로직
    	HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	
    	
    	if(loginMember != null) {
    		// ▼ 로그인이 되었으면
    		//   게시글 작성 내용을 Board 객체의 한 개의 행에 저장해주는 로직
    		Board board = new Board();
    		
    		board.setWriterNo(loginMember.getNo());  // ◀ No 는 시퀀스가 만들어줌
    		board.setTitle(title);
    		board.setContent(content);
    		board.setOriginalFileName(originalFileName);
    		board.setRenamedFileName(fileSystemName);
    		
    		result = service.save(board);
    		
    		// ▼ 게시글 등록 성공/실패 + 메세지 로직 
    		if(result > 0) {
        		// [ 게시글 등록 완료 ]
        		request.setAttribute("msg", "게시글 등록 성공!");
    			request.setAttribute("location", "/board/list");
        	} else {
        		// [ 게시글 등록 실패 ]
        		request.setAttribute("msg", "!!!!!게시글 등록 실패!!!!!");
        		// ▼ 실패하면 다시 enroll.jsp 를 열 수 있도록 하는 코드
        		request.setAttribute("location", "/board/list");
        	}
    		
    		
    	} else {
    		request.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
			request.setAttribute("location", "/");
			
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
	}

}
