package com.guangguanger.MyCRUD.test.dao;

import java.util.List;
import javax.annotation.Resource;

import com.guangguanger.MyCRUD.core.feature.test.TestSupport;
import com.guangguanger.MyCRUD.web.dao.UserMapper;
import com.guangguanger.MyCRUD.web.model.Role;
import com.guangguanger.MyCRUD.web.model.UserExample;
import org.junit.Test;
import com.guangguanger.MyCRUD.core.feature.orm.mybatis.Page;
import com.guangguanger.MyCRUD.web.model.User;

public class UserMapperTest extends TestSupport {
    @Resource
    private UserMapper userMapper;

    @Test
    public void test_selectByExampleAndPage() {
        start();
        Page<User> page = new Page<>(2, 3); // 2:第二页，每页3个
        UserExample example = new UserExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<User> users = userMapper.selectByExampleAndPage(page, example);
        for (User user : users) {
            System.err.println(user);
        }
        end();
    }

    @Test
    public void test_() {
        String username="aaa";

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);    // 设置where条件：username=...
        final List<User> list = userMapper.selectByExample(example);

        if (list.size() == 0) {
            System.err.println("true");
        } else {
            System.err.println("false");
        }
    }
    @Test
    public  void test2(){
        userMapper.addrole(Long.parseLong("100000"),Long.parseLong("1"));
    }
}
