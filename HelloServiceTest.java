package com.example.springweb;

import com.example.springweb.dao.HelloProduct;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    HelloService helloService;


    @Test
    public void testUsers() {
        HashMap<String, String> param = new HashMap<>();
        param.put("id", "1");
        param.put("name", "wudeya");
        param.put("password", "123");
        helloService.InsertUser(param);
        List<HelloUser> users = helloService.getUserList();
        assert(!users.isEmpty());

        assertEquals(helloService.getOne(param.get("id")).getName(), "wudeya");
        param.replace("name", "wdy");
        helloService.UpdateByID(param);
        users = helloService.getUserList();
        assert(!users.isEmpty());
        assertEquals(helloService.getOne(param.get("id")).getName(), "wdy");

        helloService.DeleteByID(param.get("id"));
        users = helloService.getUserList();
        assert (users.isEmpty());
    }
}
