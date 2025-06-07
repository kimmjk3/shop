package com.shop.product.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shop.attach.mapper.AttachMapper;
import com.shop.attach.vo.AttachVO;
import com.shop.product.mapper.ProductMapper;
import com.shop.product.vo.ProductVO;
import com.shop.util.FileUtils;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AttachMapper attachMapper;

    @Autowired
    private FileUtils fileUtils;

    // 상품등록,수정
    @Override
    public boolean registerProduct(ProductVO params) {
        int queryResult = 0;

        if (params.getProductNumber() == null) {
            queryResult = productMapper.insertProduct(params);
        } else {
            queryResult = productMapper.updateProduct(params);
        }
        return (queryResult == 1) ? true : false;
    }

    // 상품등록(파일)
    @Override
    public boolean registerProduct(ProductVO params, MultipartFile[] files) {
        int queryResult = 1;

        if (registerProduct(params) == false) {
            return false;
        }

        List<AttachVO> fileList = fileUtils.uploadFiles(files, params.getProductNumber());
        if (CollectionUtils.isEmpty(fileList) == false) {
            queryResult = attachMapper.insertAttach(fileList);
            if (queryResult < 1) {
                queryResult = 0;
            }
        }

        return (queryResult > 0);
    }

    // 상품상세보기
    @Override
    public ProductVO getProductDetail(Integer productNumber) {
        return productMapper.selectProductDetail(productNumber);
    }

    // 상품삭제
    @Override
    public boolean deleteProduct(Integer productNumber) {
        int queryResult = 0;

        ProductVO product = productMapper.selectProductDetail(productNumber);

        if (product != null && product.getProductDeleteDate() == null) {
            queryResult = productMapper.deleteProduct(productNumber);
            attachMapper.deleteAttach(productNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    // 상품리스트
    @Override
    public List<ProductVO> getProductList() {
        List<ProductVO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.selectProductList();
        }
        return productList;
    }

    // 첨부파일 상세보기
    @Override
    public AttachVO getAttachDetail(Integer productNumber) {
        return attachMapper.selectAttachDetail(productNumber);
    }

    // 상품판매리스트 조회
    @Override
    public List<ProductVO> getProductSellList() {
        List<ProductVO> productSellList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productSellList = productMapper.selectProductSellList();
        }

        return productSellList;
    }
    
    // 관심품목 등록
    @Override
    public boolean registerInterestItem(String userID, Integer productNumber) {
        if (productMapper.selectInterestItemCount(userID, productNumber) > 0) {
        	return false;	// 등록된 관심상품이 이미 있다면 false반환(이미 등록된 상품입니다.)
        }else {
        	productMapper.insertInterestItem(userID, productNumber);
        }
        return true;
    }

    // 관심상품 리스트
    @Override
    public List<ProductVO> getInterestItemList(String userID) {
        List<ProductVO> interestItemList = Collections.emptyList();

        int interestItemTotalCount = productMapper.selectInterestItemTotalCount(userID);

        if (interestItemTotalCount > 0) {
            interestItemList = productMapper.selectInterestItemList(userID);
        }
        return interestItemList;
    }

    // 관심상품 상제
    @Override
    public boolean deleteInterestItem(String userID, Integer productNumber) {
        int queryResult1 = 0;
        int queryResult2 = 0;

        // 등록된 관심상품이 있다면 해상당품 삭제
        queryResult1 = productMapper.selectInterestItemCount(userID, productNumber);
        if (queryResult1 == 1) {
            queryResult2 = productMapper.deleteInterestItem(userID, productNumber);
        }

        return (queryResult2 > 0) ? true : false;
    }
}
