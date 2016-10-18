package com.guangguanger.MyCRUD.web.model;

/**
 * 权限模型
 * 
 * @author StarZou
 * @since 2014年7月17日 下午1:02:55
 **/
public class Permission {
    private Long id;

    private String permissionName;

    private String permissionSign;

    private String description;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionSign() {
        return permissionSign;
    }

    public void setPermissionSign(String permissionSign) {
        this.permissionSign = permissionSign == null ? null : permissionSign.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Permission [id=" + id + ", permissionName=" + permissionName + ", permissionSign=" + permissionSign + ", description=" + description + "]";
    }

}