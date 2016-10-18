package com.guangguanger.MyCRUD.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.guangguanger.MyCRUD.core.feature.orm.mybatis.Page;
import com.guangguanger.MyCRUD.web.dao.PermissionMapper;
import com.guangguanger.MyCRUD.web.dao.RoleMapper;
import com.guangguanger.MyCRUD.web.dao.UserMapper;
import com.guangguanger.MyCRUD.web.model.*;
import com.guangguanger.MyCRUD.web.security.RoleSign;
import com.guangguanger.MyCRUD.web.service.PermissionService;
import com.guangguanger.MyCRUD.web.service.RoleService;
import com.guangguanger.MyCRUD.web.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.*;

/**
 * 视图控制器,返回jsp视图给前端
 * 
 * @author Teng
 * @since 2015年8月12日 22:06
 **/
@Controller
@RequestMapping("/page")
public class PageController {

    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;
    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleService roleService;
    @Resource
    PermissionMapper permissionMapper;
    @Resource
    PermissionService permissionService;


    /**
     * 管理员页面只有具有管理权限的用户才能访问
     */
    @RequestMapping("/adminindex")
    @RequiresRoles(value = RoleSign.ADMIN)
    public String adminindex(HttpSession session,Model model) {
//        User u=(User)session.getAttribute("userInfo");
//        List<Permission> list =  userService.getPermission(u.getId());
//        model.addAttribute("list", list);
        return "adminindex";
    }

    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 控制跳转add页
     */
    @RequestMapping("/adduser")
    public String adduserrequestmapping() {
        return "user/adduser";
    }

    @RequestMapping("/addrole")
    public String addrolerequestmapping() {
        return "role/addrole";
    }

    @RequestMapping("/addauth")
    public String addaddauthrequestmapping() {
        return "auth/addpermission";
    }

    /**
     * 控制跳转edit页
     */
    @RequestMapping("/edituser")
    public String edituserrequestmapping() {
        return "user/edituser";
    }
    @RequestMapping("/editrole")
    public String editrolerequestmapping() {
        return "role/editrole";
    }
    @RequestMapping("/editauth")
    public String editauthrequestmapping() {
        return "auth/editauth";
    }

    /**
     * 控制manage页面
     */
    @RequestMapping("/manageuser")
    public String manageuserrequestmapping() {
        return "user/manageuser";
    }
    @RequestMapping("/managerole")
    public String managerolerequestmapping() {
        return "role/managerole";
    }
    @RequestMapping("/manageauth")
    public String manageauthrequestmapping() {
        return "auth/manageauth";
    }

    //控制器决定页面转向至datatable.jsp页面
    @RequestMapping(value = "/adduserpost", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUserPost(@RequestBody User user) {
        userMapper.insertSelective(user);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/addrolepost", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addRolePost(@RequestBody Role role) {
        System.out.print("roleadd method");
        roleMapper.insertSelective(role);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/addauthpost", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAuthPost(@RequestBody Permission permission) {
        System.out.print("authadd method");
        permissionMapper.insertSelective(permission);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/edituserpost", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editUserPost(@RequestBody User user) {
        user.setCreateTime(new Date());
        userMapper.updateByPrimaryKey(user);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/editrolepost", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editRolePost(@RequestBody Role role) {
        roleMapper.updateByPrimaryKey(role);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/editauthpost", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editAuthPost(@RequestBody Permission permission) {
        permissionMapper.updateByPrimaryKey(permission);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteUser(@PathVariable("id") Long id) {
        userMapper.deleteByPrimaryKey(id);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/deleterole/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteRole(@PathVariable("id") Long id) {
        roleMapper.deleteByPrimaryKey(id);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

    @RequestMapping(value = "/deleteauth/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteAuth(@PathVariable("id") Long id) {
        permissionMapper.deleteByPrimaryKey(id);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("success", "true");
        return map;
    }

//    后台生成数据，供前端ajax读取，前端见adduser.js
//    @RequestMapping(value = "/userinfo_post_data", method = RequestMethod.POST)
//    @ResponseBody
//    //public Map<String, Object> addUser_post_data(@RequestBody List<User> users) {
//    public Map<String, Object> addUser_post_data(@RequestBody User user) {
//        List<User> list = new ArrayList<User>();
//
//        User um = new User();
//        um.setId(1L);
//        um.setUsername("sss");
//        list.add(um);
//
//        user.setId(2L);
//        list.add(user);
//
//        Map<String, Object> map = new HashMap<String, Object>(list.size());
//        map.put("total",list.size());
//        map.put("data",list);
//        map.put("success", "true");
//
//        return map;
//    }


    @RequestMapping(value = "/datausertableajax", method = RequestMethod.POST)
    @ResponseBody
    public String datausertableAjax(@RequestParam String aoData) {
        JSONArray jsonarray = JSONArray.fromObject(aoData);       // 前端datatables参数

        String sEcho = null;
        int iDisplayStart = 0;                                    // 起始索引
        int iDisplayLength = 0;                                   // 每页显示的行数

        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);

            //m4.1.0 datatable
            if (obj.get("name").equals("draw"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("start"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("length"))
                iDisplayLength = obj.getInt("value");
        }

        UserExample example1 = new UserExample();
        int linenumber=userMapper.countByExample(example1);       // 实际的行数:数据表总共的行数

        //Page<User> page = new Page<>(2, 3); // 2:第2页，每页3个
        int pagenumber=iDisplayStart/iDisplayLength+1;
        Page<User> page = new Page<>(pagenumber, iDisplayLength); // pagenumber:第pagenumber页，每页iDisplayLength个
        UserExample example = new UserExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<User> users = userMapper.selectByExampleAndPage(page, example);

        //
        List<String[]> lst = new ArrayList<String[]>();
        for (User user : users) {
            String[] d=new String[6];
            d[0]=user.getId().toString();
            d[1]=(user.getUsername()==null)?"":user.getUsername();
            d[2]=(user.getPassword()==null)?"":user.getPassword();
            d[3]=(user.getCreateTime()==null)?"":user.getCreateTime().toString();
            d[4]=d[0];
            d[5]=d[0];

            lst.add(d);
        }

//        // 生成20条测试数据
//        List<String[]> lst = new ArrayList<String[]>();
//        for (int i = 0; i < 50; i++) {
//            String[] d = {Integer.toString(i), "col2_data" + i,"col3_data" + i,"co4_data" + i,"Edit","Delete"};
//            lst.add(d);
//        }

        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", linenumber);                  // 实际的行数:数据表总共的行数
        getObj.put("iTotalDisplayRecords",linenumber);            // 显示的行数,这个要和上面写的一样
        //getObj.put("aaData", lst.subList(iDisplayStart,iDisplayStart + iDisplayLength));//要以JSON格式返回
        getObj.put("aaData", lst);                                // 要以JSON格式返回
        return getObj.toString();
    }

    @RequestMapping(value = "/dataroletableajax", method = RequestMethod.POST)
    @ResponseBody
    public String dataroletableAjax(@RequestParam String aoData) {
        JSONArray jsonarray = JSONArray.fromObject(aoData);       // 前端datatables参数

        String sEcho = null;
        int iDisplayStart = 0;                                    // 起始索引
        int iDisplayLength = 0;                                   // 每页显示的行数

        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);

            //m4.1.0 datatable
            if (obj.get("name").equals("draw"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("start"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("length"))
                iDisplayLength = obj.getInt("value");
        }

        RoleExample example1 = new RoleExample();
        int linenumber=roleMapper.countByExample(example1);       // 实际的行数:数据表总共的行数

        //Page<User> page = new Page<>(2, 3); // 2:第2页，每页3个
        int pagenumber=iDisplayStart/iDisplayLength+1;
        Page<Role> page = new Page<>(pagenumber, iDisplayLength); // pagenumber:第pagenumber页，每页iDisplayLength个
        RoleExample example = new RoleExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<Role> roles = roleMapper.selectByExampleAndPage(page, example);

        //
        List<String[]> lst = new ArrayList<String[]>();
        for (Role role : roles) {
            String[] d=new String[6];
            d[0]=role.getId().toString();
            d[1]=(role.getRoleName()==null)?"":role.getRoleName();
            d[2]=(role.getRoleSign()==null)?"":role.getRoleSign();
            d[3]=(role.getDescription()==null)?"":role.getDescription().toString();
            d[4]=d[0];
            d[5]=d[0];

            lst.add(d);
        }

        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", linenumber);                  // 实际的行数:数据表总共的行数
        getObj.put("iTotalDisplayRecords",linenumber);            // 显示的行数,这个要和上面写的一样
        //getObj.put("aaData", lst.subList(iDisplayStart,iDisplayStart + iDisplayLength));//要以JSON格式返回
        getObj.put("aaData", lst);                                // 要以JSON格式返回
        return getObj.toString();
    }

    @RequestMapping(value = "/datapermissiontableajax", method = RequestMethod.POST)
    @ResponseBody
    public String datapermissiontableAjax(@RequestParam String aoData) {
        JSONArray jsonarray = JSONArray.fromObject(aoData);       // 前端datatables参数

        String sEcho = null;
        int iDisplayStart = 0;                                    // 起始索引
        int iDisplayLength = 0;                                   // 每页显示的行数

        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);

            //m4.1.0 datatable
            if (obj.get("name").equals("draw"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("start"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("length"))
                iDisplayLength = obj.getInt("value");
        }

        PermissionExample example1 = new PermissionExample();
        int linenumber=permissionMapper.countByExample(example1);       // 实际的行数:数据表总共的行数

        //Page<User> page = new Page<>(2, 3); // 2:第2页，每页3个
        int pagenumber=iDisplayStart/iDisplayLength+1;
        Page<Permission> page = new Page<>(pagenumber, iDisplayLength); // pagenumber:第pagenumber页，每页iDisplayLength个
        PermissionExample example = new PermissionExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<Permission> permissions = permissionMapper.selectByExampleAndPage(page, example);

        //
        List<String[]> lst = new ArrayList<String[]>();
        for (Permission permission : permissions) {
            String[] d=new String[7];
            d[0]=permission.getId().toString();
            d[1]=(permission.getPermissionName()==null)?"":permission.getPermissionName();
            d[2]=(permission.getPermissionSign()==null)?"":permission.getPermissionSign();
            d[3]=(permission.getDescription()==null)?"":permission.getDescription();
            d[4]=(permission.getUrl()==null)?"":permission.getUrl();
            d[5]=d[0];
            d[6]=d[0];
            lst.add(d);
        }

//        // 生成20条测试数据
//        List<String[]> lst = new ArrayList<String[]>();
//        for (int i = 0; i < 50; i++) {
//            String[] d = {Integer.toString(i), "col2_data" + i,"col3_data" + i,"co4_data" + i,"Edit","Delete"};
//            lst.add(d);
//        }

        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", linenumber);                  // 实际的行数:数据表总共的行数
        getObj.put("iTotalDisplayRecords",linenumber);            // 显示的行数,这个要和上面写的一样
        //getObj.put("aaData", lst.subList(iDisplayStart,iDisplayStart + iDisplayLength));//要以JSON格式返回
        getObj.put("aaData", lst);                                // 要以JSON格式返回
        return getObj.toString();
    }

    @RequestMapping("/setrole")
    public String setrole(Model model) {
        Page<Role> page = new Page<>(1, 100); // pagenumber:第pagenumber页，每页iDisplayLength个
        RoleExample example = new RoleExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<Role> roles = roleMapper.selectByExampleAndPage(page, example);
        model.addAttribute("list", roles);
        return "user/setrole";
    }

    @RequestMapping("/setauth")
    public String setauth(Model model) {
        Page<Permission> page =new Page<>(1,100);
        PermissionExample example =new PermissionExample();
        example.createCriteria().andIdGreaterThan(0L);
        List<Permission> list = permissionMapper.selectByExampleAndPage(page,example);
        model.addAttribute("list", list);
        return "role/setauth";
    }




    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}