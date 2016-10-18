package com.guangguanger.MyCRUD.web.controller;

import com.guangguanger.MyCRUD.core.util.CookieUtils;
import com.guangguanger.MyCRUD.web.dao.UserMapper;
import com.guangguanger.MyCRUD.web.model.User;
import com.guangguanger.MyCRUD.web.model.UserExample;
import com.guangguanger.MyCRUD.web.security.PermissionSign;
import com.guangguanger.MyCRUD.web.security.RoleSign;
import com.guangguanger.MyCRUD.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 * 
 * @author Teng
 * @since 2015年8月26日 下午14:09:00
 **/
@Controller
@RequestMapping(value = "/admin")
public class AdminUserController {

    // (teng：访问数据库：（1）定义接口userService，byName自动注入userServiceimpl实现接口的类
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    /**
     * 后台检查前端字段的合法性，合法返回true，非法返回false
     */
    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkUser(HttpServletRequest request,HttpServletResponse response) {
        String username = request.getParameter("username");

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);    // 设置where条件：username=...
        final List<User> list = userMapper.selectByExample(example);

        return list.size() == 0;
    }


    /**
     * 用户登出
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";

    }

    /**
     *   （teng 不同登录页面：（1）Shiro下不同登录页面，浏览器中直接输入地址可访问
     *
     */
    @RequestMapping("/adminlogin")
    public String login() {
        return "login";
    }

    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
        return "拥有admin角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }
}
