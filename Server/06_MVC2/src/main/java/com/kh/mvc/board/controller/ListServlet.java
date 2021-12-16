package com.kh.mvc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;


@WebServlet("/board/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService service = new BoardService();
	
    public ListServlet() {
      
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 게시글 리스트 조회
    	// 2. 페이징처리
    	int page = 0;
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	// ▼ 게시글을 여러개 담아올 것이므로 list 로 처리
    	List<Board> list = null;
    	
    	try {
    		// ▼ 최초로 페이지를 열었을 때 넘겨주는 값이 없어서 (=null) 에러남
    		//   ▷ try catch 로 null 에러시 page=1 값을 주어 
    		//      맨 처음 접속했을 때 1페이지를 열도록 함
    		//   * 아래와 같이삼항 연산자로 해도 됨 
    		//    page = Integer.parseInt(request.getParameter("page")) != null 
    		//           ? Integer.parseInt(request.getParameter("page")) : 1;
    		page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
    	
    	// ▼ Servlet 에서 비즈니스 로직을 처리하지 않고, Service 객체한테 처리를 위임할 것임	
    	listCount = service.getBoardCount();
    	// ▼ PageInfo 매개변수 생성자로 파라미터 값을 list.jsp에게 넘겨줘서 화면 생성하게 할 것임
    	pageInfo = new PageInfo(page, 10, listCount, 10);
    	// ▼ list 도 Service 객체에게서 가져올 것임
    	list = service.getBoardCount(pageInfo);
    	
//    	System.out.println(listCount);
    	System.out.println(list);
    	
    	
    	// ▼ list.jsp 에 pageInfo 의 데이터를 넘겨주는 중
    	request.setAttribute("pageInfo", pageInfo);
    	// ▼ list.jsp 에 pageInfo 의 데이터를 넘겨주는 중
    	request.setAttribute("list", list);
    	// ▼ list.jsp 페이지를 보여주기 위해 포워딩하는 코드
    	request.getRequestDispatcher("/views/board/list.jsp").forward(request, response);
	}

}
