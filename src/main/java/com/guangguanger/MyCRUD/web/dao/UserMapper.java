package com.guangguanger.MyCRUD.web.dao;


import com.guangguanger.MyCRUD.core.feature.orm.mybatis.Page;
import com.guangguanger.MyCRUD.core.generic.GenericDao;
import com.guangguanger.MyCRUD.web.model.Permission;
import com.guangguanger.MyCRUD.web.model.Role;
import com.guangguanger.MyCRUD.web.model.User;
import com.guangguanger.MyCRUD.web.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Dao接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:49:57
 **/
public interface UserMapper extends GenericDao<User, Long> {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用户登录验证查询
     * 
     * @param record
     * @return
     */
    User authentication(@Param("record") User record);

    /**
     * 分页条件查询
     * 
     * @param page
     * @param example
     * @return
     */
    List<User> selectByExampleAndPage(Page<User> page, UserExample example);

    /**
     * 根据偏移量查询用戶列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    int deleteRelate(@Param("offset") Long id);

    void addrole(Long uid,Long rid);

}