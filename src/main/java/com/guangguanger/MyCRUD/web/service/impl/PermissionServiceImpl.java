package com.guangguanger.MyCRUD.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.guangguanger.MyCRUD.core.generic.GenericServiceImpl;
import com.guangguanger.MyCRUD.web.model.Permission;
import org.springframework.stereotype.Service;
import com.guangguanger.MyCRUD.core.generic.GenericDao;
import com.guangguanger.MyCRUD.web.dao.PermissionMapper;
import com.guangguanger.MyCRUD.web.service.PermissionService;

/**
 * 权限Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午12:05:03
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public GenericDao<Permission, Long> getDao() {
        return permissionMapper;
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }


}
