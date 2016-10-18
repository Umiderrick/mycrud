package com.guangguanger.MyCRUD.web.dao;

import com.guangguanger.MyCRUD.core.feature.orm.mybatis.Page;
import com.guangguanger.MyCRUD.core.generic.GenericDao;
import com.guangguanger.MyCRUD.web.model.Permission;
import com.guangguanger.MyCRUD.web.model.Role;
import com.guangguanger.MyCRUD.web.model.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色Dao 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:55:59
 **/
public interface RoleMapper extends GenericDao<Role, Long> {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

    List<Role> selectByExampleAndPage(Page<Role> page, RoleExample example);

    void addPermission(Long rid,Long pid);

    List<Permission> getPermission(Long id);
}