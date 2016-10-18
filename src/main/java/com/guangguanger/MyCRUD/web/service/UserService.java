package com.guangguanger.MyCRUD.web.service;

import com.guangguanger.MyCRUD.core.generic.GenericService;
import com.guangguanger.MyCRUD.web.model.Permission;
import com.guangguanger.MyCRUD.web.model.User;

import java.util.List;

/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserService extends GenericService<User, Long> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 用戶列表
     * @return List<User>
     */
    List<User> getUserlList();

    /**
     *删除关联表数据
     *
     */

    int deleteRelate(Long id);

}
