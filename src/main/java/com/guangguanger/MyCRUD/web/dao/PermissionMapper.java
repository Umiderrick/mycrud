package com.guangguanger.MyCRUD.web.dao;


import com.guangguanger.MyCRUD.core.feature.orm.mybatis.Page;
import com.guangguanger.MyCRUD.core.generic.GenericDao;
import com.guangguanger.MyCRUD.web.model.Permission;
import com.guangguanger.MyCRUD.web.model.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限 Dao 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:59:03
 **/
public interface PermissionMapper extends GenericDao<Permission, Long> {
    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);

    List<Permission> selectByExampleAndPage(Page<Permission> page, PermissionExample example);
}