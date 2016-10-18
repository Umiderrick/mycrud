package com.guangguanger.MyCRUD.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.guangguanger.MyCRUD.core.util.CookieUtils;
import com.guangguanger.MyCRUD.web.dao.UserMapper;
import com.guangguanger.MyCRUD.web.model.Permission;
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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping(value = "/user")
public class UserController {

    // (teng：访问数据库：（1）定义接口userService，byName自动注入userServiceimpl实现接口的类
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录
     * 
     * @param user
     * @param result
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request,HttpServletResponse response) {
        try {
            Subject subject = SecurityUtils.getSubject();

            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "login";
            }

            // 身份验证：
            // 登录成功后跳转到shiro配置（applicationContext-shiro.xml）的successUrl中
            // <property name="successUrl" value="/rest/index"/>
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));

            // 验证成功：在Session中保存用户信息
            // (teng：访问数据库：（2）访问实现接口类的方法selectByUsername
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            request.getSession().setAttribute("userInfo", authUserInfo);

            // 记住我：如果“记住我”选中，将选项、用户名和密码分别存入cookie，用于再次登录时免于输入
            String isRember=request.getParameter("remember");     //“记住我”选项

            //
            if (isRember!=null && isRember.equals("1")==true)
            {
                Map<String,String> map=new HashMap<String,String>();
                map.put("rmbUser","true");
                map.put("userName",user.getUsername());
                map.put("passWord",user.getPassword());
                CookieUtils.addCookies(response,map,"/",60*60*24*7);
            }
            else
            {
                Map<String,String> map=new HashMap<String,String>();
                map.put("rmbUser","false");
                map.put("userName","");
                map.put("passWord","");
                CookieUtils.addCookies(response,map,"/",60*60*24*7);
            }

            //权限跳转：（1）根据用户不同权限，跳转到不同页面，首次尝试登录
            if (subject.hasRole(RoleSign.ADMIN))
            {
                return "redirect:/rest/page/adminindex";
            }
        }
        catch (AuthenticationException e) {
            // 验证失败：删除cookies
            Map<String,String> map=new HashMap<String,String>();
            map.put("userName","");
            map.put("passWord","");
            CookieUtils.addCookies(response,map,"/",60*60*24*7);
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
        return "redirect:/";
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
     * 注册页 register-form
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(HttpServletRequest request,HttpServletResponse response) {
        User user = new User();
        user.setUsername(request.getParameter("rusername"));
        user.setPassword(request.getParameter("passwords"));
        user.setCreateTime(new Date());
        userMapper.insertSelective(user);
        return "login";
    }

    /**
     * 后台检查前端字段的合法性，合法返回true，非法返回false
     */
    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkUser(HttpServletRequest request,HttpServletResponse response) {
        String username = request.getParameter("rusername");
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);    // 设置where条件：username=...
        final List<User> list = userMapper.selectByExample(example);
        return list.size() == 0;
    }

    /**
     *用戶列表查詢
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> list = userService.getUserlList();
        model.addAttribute("list", list);
        return null;
    }

    /**
     *用戶刪除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @RequiresPermissions(value = PermissionSign.USER_DELETE)
    public void delete(@PathVariable("id") Long id) {
       // userService.deleteRelate(id);
        userService.delete(id);
    }

    /*
     *根据用户选择对应权限
     */
    @RequestMapping(value = "/adminindex",method = RequestMethod.GET)

    public String filterpermission(User user,Model model){

        return null;
    }


}
