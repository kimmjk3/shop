package com.shop.controller;

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

import com.shop.constant.Method;
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

    @PostMapping(value = "/shop/productregister.do")
    public String registerProduct(final ProductDTO params, Model model, HttpServletRequest request,
            HttpSession session) {
        try {
            // 세션값 userID params 입력
            String userID = null;
            if (session.getAttribute("userID") != null) {
                userID = (String) session.getAttribute("userID");
            }
            params.setUserID(userID);

            boolean isRegistered = productService.registerProduct(params);
            if (isRegistered == false) {
                return showMessageWithRedirect("제품 등록에 실패하였습니다.", "/shop/productmanagement.do", Method.GET, null,
                        model);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/shop/productmanagement.do", Method.GET, null,
                    model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/productmanagement.do", Method.GET, null, model);
        }

        return showMessageWithRedirect("제품 등록이 완료되었습니다.", "/shop/productmanagement.do", Method.GET, null, model);
    }

    @GetMapping(value = "/shop/productregistration.do")
    public String openProductRegistration(
            @RequestParam(value = "productNumber", required = false) Integer productNumber, Model model) {
        if (productNumber == null) {
            model.addAttribute("product", new ProductDTO());
        } else {
            ProductDTO product = productService.getProductDetail(productNumber);
            if (product == null) {
                return "redirect:/shop/productmanagement.do";
            }
            model.addAttribute("product", product);
        }

        return "shop/productregistration";
    }

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

    @GetMapping(value = "/shop/productview.do")
    public String openProductDetail(@RequestParam(value = "productNumber", required = false) Integer productNumber,
            Model model) {
        if (productNumber == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/shop/list.do";
        }

        ProductDTO product = productService.getProductDetail(productNumber);
        if (product == null || product.getProductDeleteDate() != null) {
            // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/shop/list.do";
        }
        model.addAttribute("product", product);

        return "shop/productview";
    }

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