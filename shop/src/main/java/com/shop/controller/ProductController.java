package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.domain.ProductDTO;
import com.shop.service.ProductService;
import com.shop.util.UiUtils;

@Controller
public class ProductController extends UiUtils {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/shop/productmanagement.do")
    public String openProductList(Model model) {
        List<ProductDTO> productList = productService.getProductList();
        model.addAttribute("productList", productList);

        return "shop/productmanagement";
    }

    /*
     * @GetMapping(value = "/shop/write.do") public String
     * openBoardWrite(@RequestParam(value = "postNumber", required = false) Integer
     * postNumber, Model model) { if (postNumber == null) {
     * model.addAttribute("board", new BoardDTO()); } else { BoardDTO board =
     * boardService.getBoardDetail(postNumber); if (board == null) { return
     * "redirect:/shop/list.do"; } model.addAttribute("board", board); }
     * 
     * return "shop/write"; }
     */

    /*
     * @PostMapping(value = "/shop/register.do") public String registerBoard(final
     * BoardDTO params, Model model, HttpServletRequest request, HttpSession
     * session) { try {
     * 
     * // 세션값 userID params 입력 String userID = null; if
     * (session.getAttribute("userID") != null) { userID = (String)
     * session.getAttribute("userID"); } params.setUserID(userID);
     * 
     * boolean isRegistered = boardService.registerBoard(params); if (isRegistered
     * == false) { return showMessageWithRedirect("게시글 등록에 실패하였습니다.",
     * "/shop/list.do", Method.GET, null, model); } } catch (DataAccessException e)
     * { return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.",
     * "/shop/list.do", Method.GET, null, model);
     * 
     * } catch (Exception e) { return showMessageWithRedirect("시스템에 문제가 발생하였습니다.",
     * "/shop/list.do", Method.GET, null, model); }
     * 
     * return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/shop/list.do",
     * Method.GET, null, model); }
     */

    /*
     * @GetMapping(value = "/shop/view.do") public String
     * openBoardDetail(@RequestParam(value = "postNumber", required = false) Integer
     * postNumber, Model model) { if (postNumber == null) { // TODO => 올바르지 않은 접근이라는
     * 메시지를 전달하고, 게시글 리스트로 리다이렉트 return "redirect:/shop/list.do"; }
     * 
     * BoardDTO board = boardService.getBoardDetail(postNumber); if (board == null
     * || board.getPostDeleteDate() != null) { // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는
     * 메시지를 전달하고, 게시글 리스트로 리다이렉트 return "redirect:/shop/list.do"; }
     * model.addAttribute("board", board);
     * 
     * return "shop/view"; }
     */

    /*
     * @PostMapping(value = "/shop/delete.do") public String
     * deleteBoard(@RequestParam(value = "postNumber", required = false) Integer
     * postNumber, Model model) { System.out.println("/shop/delete.do 실행됨");
     * 
     * if (postNumber == null) { return showMessageWithRedirect("올바르지 않은 접근입니다.",
     * "/shop/list.do", Method.GET, null, model); }
     * 
     * try { boolean isDeleted = boardService.deleteBoard(postNumber); if (isDeleted
     * == false) { return showMessageWithRedirect("게시글 삭제에 실패하였습니다.",
     * "/shop/list.do", Method.GET, null, model); } } catch (DataAccessException e)
     * { return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.",
     * "/shop/list.do", Method.GET, null, model);
     * 
     * } catch (Exception e) { return showMessageWithRedirect("시스템에 문제가 발생하였습니다.",
     * "/shop/list.do", Method.GET, null, model); }
     * 
     * return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/shop/list.do",
     * Method.GET, null, model); }
     */
}