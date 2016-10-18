package com.guangguanger.MyCRUD.web.security;

/**
 * 角色标识配置类, <br>
 * 与 role_info 角色表中的 role_sign 字段 相对应 <br>
 * 使用:
 * 
 * <pre>
 * &#064;RequiresRoles(value = RoleSign.ADMIN)
 * public String admin() {
 *     return &quot;拥有admin角色,能访问&quot;;
 * }
 * </pre>
 * 
 * @author StarZou
 * @since 2014年6月17日 下午3:58:51
 **/
public class RoleSign {

    /**
     * 普通管理员 标识
     */
    public static final String ADMIN = "admin";

    /**
     * 超级管理员 标识
     */
    public static final String SUPER_ADMIN = "super_admin";

    /**
     * 用户 标识
     */
    public static final String USER = "user";

    /**
     * 客人 标识
     */
    public static final String GUEST = "guest";

    /**
     * 添加更多...
     */

}
