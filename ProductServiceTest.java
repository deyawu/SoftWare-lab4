package com.example.springweb.service;

import com.example.springweb.dao.HelloProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/*
 * @ClassName ProductServiceTest
 * @author: WuDeya
 * @Date: Created in 2019/11/19 9:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void productTest() {
        HashMap<String, String> param = new HashMap<>();
        param.put("appId", "1");
        param.put("appName", "量子速读app");
        param.put("rangeApplication", "基础共性工业APP");
        param.put("businessLink", "研发设计工业APP");
        param.put("knowledgeType", "业务信息化类");
        param.put("testFile", "www.pan.baidu.com/xxxx");
        productService.InsertProduct(param);
        List<HelloProduct> productList = productService.getProductList();
        assert(!productList.isEmpty());
        assertEquals(productService.getOne(param.get("appId")).getAppName(), "量子速读app");
        assertEquals(productService.getOne(param.get("appId")).getBusinessLink(), "研发设计工业APP");

        param.replace("appName", "手机定位app");
        param.replace("rangeApplication", "行业通用工业APP");
        productService.UpdateByID(param);
        productList = productService.getProductList();
        assert(!productList.isEmpty());
        assertEquals(productService.getOne(param.get("appId")).getAppName(), "手机定位app");
        assertEquals(productService.getOne(param.get("appId")).getRangeApplication(), "行业通用工业APP");

        productService.DeleteByID(param.get("appId"));
        productList = productService.getProductList();
        assert(productList.isEmpty());
    }

}
