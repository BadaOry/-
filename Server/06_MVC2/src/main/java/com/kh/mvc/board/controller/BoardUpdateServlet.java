package com.kh.mvc.board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.FileRename;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardUpdateServlet() {

    }

    // [ 페이지를 요청하는 메소드 ]
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 체크 & 본인 게시글 수정 여부 확인 (직접 적용시켜보기)
		// ▼ 게시글내용 체크를 위해서 가져오는 Board 객체
		Board board = new Board();
		int no = Integer.parseInt(request.getParameter("no"));
		
		// ▼ Board 객체는 Service 에서 가져옴
		//    * Service는 한번 사용하고 나면 없어질 객체라서 필요할떄마다 만들어주는게 조금 더 효율적임
		board = new BoardService().findBoardByNo(no);
		
		// ▼ update.jsp 에 Board 객체를 넘겨서 정보를 주고, 페이지 포워딩 해주는 코드
		request.setAttribute("board", board);
		request.getRequestDispatcher("/views/board/update.jsp").forward(request, response);
	}

    
    // [ 실제 게시글 수정을 요청하는 메소드 ] 
    // : 첨부파일 처리 때문에 MultipartRequest 추가
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		Board board = null;
		String originalFileName = null;
		String renamedFileName = null;
    	String path = getServletContext().getRealPath("/resources/upload/board");
    	int maxSize = 10485760;
    	String encoding = "UTF-8";
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    	
    	board = new Board();
    	
    	board.setNo(Integer.parseInt(mr.getParameter("no")));
    	board.setTitle(mr.getParameter("title"));
    	board.setWriterId(mr.getParameter("writerId"));
    	board.setContent(mr.getParameter("content"));
    	// ▼ ▼ 기존에 사용자가 등록했던 파일의 oname 과 rname
    	board.setOriginalFileName(mr.getParameter("originalFileName"));
    	board.setRenamedFileName(mr.getParameter("renamedFileName"));
	
    	originalFileName = mr.getOriginalFileName("upfile");
    	renamedFileName = mr.getFilesystemName("upfile");
    	
    	// ▼ 원래는 파일 삭제해도 일정기간 보유하는데, 우리는 원래 파일 바로 삭제해 볼 것임
    	if(originalFileName != null && !originalFileName.equals("")) {
    		// ▼ 실제 파일이 저장되는 경로(path) 에 원래 파일의 rname 을 가져옴
    		File file = new File(path + "/" + board.getRenamedFileName());
    		
    		// ▼ 기존에 있던 파일이 있으면 지워주는 메소드 
    		if(file.exists()) {
    			file.delete();
    		}
    		
    		// ▼ 기존에 사용자가 등록했던 파일이 존재할 떄 
    		//   : 이번에 새로 올리는 파일이 있으면 oname 과 rname 으로 바꿀거고
    		//     아니면 원래 올려놨던 파일의 oname 과 rname을 쓸 거임
    		board.setOriginalFileName(originalFileName);
        	board.setRenamedFileName(renamedFileName);
    	}
    	
    	// ▼ 파일 명 세팅이 끝나면, 
    	//  Service 객체에게 save() 메소드로 저장 재 요청하면서 board 객체를 넘김
    	result = new BoardService().save(board);
    	
    	if(result > 0) {
    		request.setAttribute("msg", "게시글 수정 성공");
    	} else {
    		request.setAttribute("msg", "게시글 수정 실패");
    	}
    	
    	request.setAttribute("location", "/board/view?no=" + board.getNo());
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
