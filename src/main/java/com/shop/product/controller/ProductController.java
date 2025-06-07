package com.shop.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.attach.vo.AttachVO;
import com.shop.product.service.ProductService;
import com.shop.product.vo.ProductVO;
import com.shop.util.MessageMethod;
import com.shop.util.MessageUtils;

@Controller
public class ProductController extends MessageUtils {

    @Autowired
    private ProductService productService;

    // 상품 판매리스트 진입
    @GetMapping(value = "/shop/productlist.do")
    public String openSellList(Model model) {

        List<ProductVO> productSellList = productService.getProductSellList();
        model.addAttribute("productSellList", productSellList);

        return "shop/productlist";
    }

    // 상품구매 페이지 진입
    @GetMapping(value = "/shop/productpurchase.do")
    public String openSell(Model model, Integer productNumber) {

        if (productNumber == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 상품관리 페이지로 리다이렉트
            return "redirect:/shop/productmanagement.do";
        }

        ProductVO product = productService.getProductDetail(productNumber);
        if (product == null || product.getProductDeleteDate() != null) {
            // TODO => 없는 상품이거나, 이미 삭제된 상품이라는 메시지를 전달하고, 상품관리페이지로 리다이렉트
            return "redirect:/shop/productmanagement.do";
        }
        model.addAttribute("product", product);

        AttachVO attach = productService.getAttachDetail(productNumber);
        model.addAttribute("attach", attach);

        return "shop/productpurchase";
    }

    // 상품관리 페이지 진입
    @GetMapping(value = "/shop/productmanagement.do")
    public String openProductmanagement(Model model, HttpSession session) {
        List<ProductVO> productList = productService.getProductList();
        model.addAttribute("productList", productList);

        int userAuthority = (int) session.getAttribute("userAuthority");
        if (userAuthority != 0) {
            return showMessageWithRedirect("권한이 없습니다.", "/shop/index.do", MessageMethod.GET, null, model);
        }

        return "shop/productmanagement";
    }

    // 상품등록 페이지
    @GetMapping(value = "/shop/productregistration.do")
    public String openProductRegistration(
            @RequestParam(value = "productNumber", required = false) Integer productNumber, Model model,
            HttpSession session) {

        int userAuthority = (int) session.getAttribute("userAuthority");
        if (userAuthority != 0) {
            return showMessageWithRedirect("권한이 없습니다.", "/shop/index.do", MessageMethod.GET, null, model);
        }

        if (productNumber == null) {
            model.addAttribute("product", new ProductVO());
        } else {
            ProductVO product = productService.getProductDetail(productNumber);
            if (product == null) {
                return "redirect:/shop/productmanagement.do";
            }
            model.addAttribute("product", product);
        }

        return "shop/productregistration";
    }

    // 상품등록
    @PostMapping(value = "/shop/productregister.do")
    public String registerProduct(final ProductVO params, final MultipartFile[] files, Model model,
            HttpServletRequest request, HttpSession session) {
        try {
            // 세션값 userID params 입력
            String userID = null;
            if (session.getAttribute("userID") != null) {
                userID = (String) session.getAttribute("userID");
            }
            params.setUserID(userID);

            boolean isRegistered = productService.registerProduct(params, files);
            if (isRegistered == false) {
                return showMessageWithRedirect("제품 등록에 실패하였습니다.", "/shop/productmanagement.do", MessageMethod.GET, null,
                        model);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/shop/productmanagement.do", MessageMethod.GET, null,
                    model);

        } catch (Exception e) {
            e.printStackTrace();
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/productmanagement.do", MessageMethod.GET, null, model);
        }

        return showMessageWithRedirect("제품 등록이 완료되었습니다.", "/shop/productmanagement.do", MessageMethod.GET, null, model);
    }

    // 상품보기
    @GetMapping(value = "/shop/productview.do")
    public String openProductDetail(@RequestParam(value = "productNumber", required = false) Integer productNumber,
            Model model, HttpSession session) {

        int userAuthority = (int) session.getAttribute("userAuthority");
        if (userAuthority != 0) {
            return showMessageWithRedirect("권한이 없습니다.", "/shop/index.do", MessageMethod.GET, null, model);
        }

        if (productNumber == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 상품관리 페이지로 리다이렉트
            return "redirect:/shop/productmanagement.do";
        }

        ProductVO product = productService.getProductDetail(productNumber);
        if (product == null || product.getProductDeleteDate() != null) {
            // TODO => 없는 상품이거나, 이미 삭제된 상품이라는 메시지를 전달하고, 상품관리페이지로 리다이렉트
            return "redirect:/shop/productmanagement.do";
        }
        model.addAttribute("product", product);

        AttachVO attach = productService.getAttachDetail(productNumber);
        model.addAttribute("attach", attach);

        return "shop/productview";
    }

    // 상품삭제
    @PostMapping(value = "/shop/productdelete.do")
    public String deleteProduct(@RequestParam(value = "productNumber", required = false) Integer productNumber,
            Model model) {

        if (productNumber == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/shop/productmanagement.do", MessageMethod.GET, null, model);
        }

        try {
            boolean isDeleted = productService.deleteProduct(productNumber);
            if (isDeleted == false) {
                return showMessageWithRedirect("상품 삭제에 실패하였습니다.", "/shop/productmanagement.do", MessageMethod.GET, null,
                        model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/shop/productmanagement.do", MessageMethod.GET, null,
                    model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/productmanagement.do", MessageMethod.GET, null, model);
        }

        return showMessageWithRedirect("상품 삭제가 완료되었습니다.", "/shop/productmanagement.do", MessageMethod.GET, null, model);
    }
    
	// 관심상품 등록
	@RequestMapping(value = "/shop/interestitem.do")
	public String InterestItem(@RequestParam(value = "productNumber", required = false) Integer productNumber, Model model, HttpSession session) {
		
		boolean isRegistered = false;
		
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		try {
			isRegistered = productService.registerInterestItem(userID, productNumber);
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 오류가 발생하였습니다.", "/shop/productlist.do", MessageMethod.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/productlist.do", MessageMethod.GET, null, model);
		}
		if (isRegistered == false) {
			return showMessageWithRedirect("이미 관심등록된 상품입니다.", "/shop/productlist.do", MessageMethod.GET, null, model);
		}else {
			return showMessageWithRedirect("관심상품 등록 되었습니다.", "/shop/productlist.do", MessageMethod.GET, null, model);
		}
		
	}

	// 관심상품 리스트 페이지 이동
	@RequestMapping(value = "/shop/interestitemlist.do")
	public String openInterestitemlist(Model model, HttpSession session) {

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}

		List<ProductVO> interestItemList = productService.getInterestItemList(userID);
		model.addAttribute("interestItemList", interestItemList);

		return "shop/interestitemlist";
	}

	// 관심상품 삭제
	@RequestMapping(value = "/shop/interestitemdelete.do")
	public String deleteBoard(@RequestParam(value = "productNumber", required = false) Integer productNumber,
			Model model, HttpSession session) {

		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}

		if (userID == null && productNumber == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/shop/interestitemlist.do", MessageMethod.GET, null,
					model);
		}

		try {
			boolean isDeleted = productService.deleteInterestItem(userID, productNumber);
			if (isDeleted == false) {
				return showMessageWithRedirect("관심상품 삭제에 실패하였습니다.", "/shop/interestitemlist.do", MessageMethod.GET,
						null, model);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/shop/interestitemlist.do", MessageMethod.GET,
					null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/interestitemlist.do", MessageMethod.GET, null,
					model);
		}

		return showMessageWithRedirect("관심상품 삭제가 완료되었습니다.", "/shop/interestitemlist.do", MessageMethod.GET, null,
				model);
	}
}