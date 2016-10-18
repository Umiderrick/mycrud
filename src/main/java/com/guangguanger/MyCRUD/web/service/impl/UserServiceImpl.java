package com.guangguanger.MyCRUD.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.guangguanger.MyCRUD.core.generic.GenericDao;
import com.guangguanger.MyCRUD.core.generic.GenericServiceImpl;
import com.guangguanger.MyCRUD.web.dao.UserMapper;
import com.guangguanger.MyCRUD.web.model.Permission;
import com.guangguanger.MyCRUD.web.model.User;
import com.guangguanger.MyCRUD.web.model.UserExample;
import com.guangguanger.MyCRUD.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User model) {
        return userMapper.insertSelective(model);
    }

    @Override
    public int update(User model) {
        return userMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User authentication(User user) {
        return userMapper.authentication(user);
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<User, Long> getDao() {
        return userMapper;
    }

    // (teng：访问数据库：（3）设置where条件
    // (teng：访问数据库：（5）通过mybatis访问
    @Override
    public User selectByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username); // （3）设置where条件：username=...
        final List<User> list = userMapper.selectByExample(example);
        return list.get(0);
    }
    @Override
    public List<User> getUserlList() {
        return userMapper.queryAll(0, 1000);
    }

    @Override
    public int deleteRelate(Long id) {
        return userMapper.deleteRelate(id);
    }

}
