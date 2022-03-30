package com.shop.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shop.domain.AttachDTO;
import com.shop.domain.ProductDTO;
import com.shop.mapper.AttachMapper;
import com.shop.mapper.ProductMapper;
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
    public boolean registerProduct(ProductDTO params) {
        int queryResult = 0;

        if (params.getProductNumber() == null) {
            queryResult = productMapper.insertProduct(params);
        } else {
            queryResult = productMapper.updateProduct(params);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean registerProduct(ProductDTO params, MultipartFile[] files) {
        int queryResult = 1;

        if (registerProduct(params) == false) {
            return false;
        }

        List<AttachDTO> fileList = fileUtils.uploadFiles(files, params.getProductNumber());
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
    public ProductDTO getProductDetail(Integer productNumber) {
        return productMapper.selectProductDetail(productNumber);
    }

    // 상품삭제
    @Override
    public boolean deleteProduct(Integer productNumber) {
        int queryResult = 0;

        ProductDTO product = productMapper.selectProductDetail(productNumber);

        if (product != null && product.getProductDeleteDate() == null) {
            queryResult = productMapper.deleteProduct(productNumber);
            attachMapper.deleteAttach(productNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    // 상품리스트
    @Override
    public List<ProductDTO> getProductList() {
        List<ProductDTO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.selectProductList();
        }
        return productList;
    }
}
