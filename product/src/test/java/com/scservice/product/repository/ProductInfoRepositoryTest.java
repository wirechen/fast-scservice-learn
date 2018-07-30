package com.scservice.product.repository;

import com.scservice.product.ProductApplicationTests;
import com.scservice.product.dataobject.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ProductInfoRepositoryTest extends ProductApplicationTests{

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void findByProductIdIn() throws Exception {
        List<Integer> productIdList = Arrays.asList(1, 2);
        List<ProductInfo> byProductIdIn = repository.findByProductIdIn(productIdList);
        System.out.println(byProductIdIn);
        Assert.assertNotNull(byProductIdIn);
    }

}