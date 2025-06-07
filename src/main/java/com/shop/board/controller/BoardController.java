package com.shop.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.attach.vo.AttachVO;
import com.shop.board.service.BoardService;
import com.shop.board.vo.BoardVO;
import com.shop.util.MessageMethod;
import com.shop.util.MessageUtils;

@Controller
public class BoardController extends MessageUtils {

    @Autowired
    private BoardService boardService;
    
    //리뷰페이지
    @GetMapping(value = "/openReviewBoard.do")
    public String openBoardList(Model model) {
        List<BoardVO> reviewList = boardService.selectReviewList();
        model.addAttribute("reviewList", reviewList);
        return "shop/reviewBoard";
    }
    
    // 게시글보기
    @GetMapping(value = "/openReviewDetail.do")
    public String openBoardDetail(@RequestParam(value = "postNumber", required = false) Integer postNumber, Model model) {
        if (postNumber == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/openReviewBoard.do";
        }

        BoardVO board = boardService.getBoardDetail(postNumber);
        if (board == null || board.getPostDeleteDate() != null) {
            // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/openReviewBoard.do";
        }

        model.addAttribute("board", board);

        AttachVO attach = boardService.getAttachDetail(postNumber);
        model.addAttribute("attach", attach);

        return "shop/boardview";
    }

    // 게시글등록 페이지
    @GetMapping(value = "/shop/boardwrite.do")
    public String openBoardWrite(@RequestParam(value = "postNumber", required = false) Integer postNumber,
            @RequestParam(value = "boardNumber", required = false) Integer boardNumber, Model model) {

        model.addAttribute("boardNumber", boardNumber);

        if (postNumber == null) {
            BoardVO board = new BoardVO();
            board.setBoardNumber(boardNumber);
            model.addAttribute("board", board);
        } else {
            BoardVO board = boardService.getBoardDetail(postNumber);
            if (board == null) {
                return "redirect:/openReviewBoard.do";
            }
            model.addAttribute("board", board);
        }
        return "shop/boardwrite";
    }

    // 게시글 등록
    @PostMapping(value = "/shop/register.do")
    public String registerBoard(final BoardVO params, final MultipartFile[] files, Model model,
            HttpServletRequest request, HttpSession session) {
        try {
            // 세션값 userID params 입력
            String userID = null;
            if (session.getAttribute("userID") != null) {
                userID = (String) session.getAttribute("userID");
            }
            params.setUserID(userID);

            boolean isRegistered = boardService.registerBoard(params, files);
            if (isRegistered == false) {
                return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);

        } catch (Exception e) {
            e.printStackTrace();
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);
        }

        return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);
    }

    @PostMapping(value = "/shop/boarddelete.do")
    public String deleteBoard(@RequestParam(value = "postNumber", required = false) Integer postNumber, Model model) {

        if (postNumber == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);
        }

        try {
            boolean isDeleted = boardService.deleteBoard(postNumber);
            if (isDeleted == false) {
                return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);
        }

        return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/openReviewBoard.do", MessageMethod.GET, null, model);
    }
}