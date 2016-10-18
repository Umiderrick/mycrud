package com.guangguanger.MyCRUD.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.guangguanger.MyCRUD.web.dao.RoleMapper;
import org.springframework.stereotype.Service;
import com.guangguanger.MyCRUD.core.generic.GenericDao;
import com.guangguanger.MyCRUD.core.generic.GenericServiceImpl;
import com.guangguanger.MyCRUD.web.model.Role;
import com.guangguanger.MyCRUD.web.service.RoleService;

/**
 * 角色Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public GenericDao<Role, Long> getDao() {
        return roleMapper;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }


}
