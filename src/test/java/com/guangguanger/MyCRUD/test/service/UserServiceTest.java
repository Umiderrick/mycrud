package com.guangguanger.MyCRUD.test.service;

import java.util.Date;
import javax.annotation.Resource;

import com.guangguanger.MyCRUD.core.util.ApplicationUtils;
import com.guangguanger.MyCRUD.core.feature.test.TestSupport;
import com.guangguanger.MyCRUD.web.model.User;
import com.guangguanger.MyCRUD.web.service.UserService;
import org.junit.Test;

public class UserServiceTest extends TestSupport {

    @Resource
    private UserService userService;

    @Test
    public void test_insert() {
        start();
        User model = new User();
        model.setUsername("aa");
        model.setPassword(ApplicationUtils.sha256Hex("123456"));
        model.setCreateTime(new Date());
        userService.insert(model);
        end();
    }

//    @Test
    public void test_10insert() {
        for (int i = 0; i < 10; i++) {
            User model = new User();
            model.setUsername("starzou" + i);
            model.setPassword(ApplicationUtils.sha256Hex("123456"));
            model.setCreateTime(new Date());
            userService.insert(model);
        }
    }

    @Test
    public void test_selectByUsername(){
        start();
        String username="44";
        final User authUserInfo = userService.selectByUsername(username);
        System.err.printf(authUserInfo.getUsername());
        end();
    }

}
