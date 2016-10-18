<%--
  Created by IntelliJ IDEA.
  User: umiderrick
  Date: 2016/7/20
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="portlet box purple">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-gift"></i>修改权限
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" id="authform2" class="form-horizontal">
            <div class="form-body">
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    You have some form errors. Please check below.
                </div>
                <div class="alert alert-success display-hide">
                    <button class="close" data-close="alert"></button>
                    Your form validation is successful!
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">编号<span class="required">
                                            * </span>
                    </label>
                    <div class="col-md-4">
                        <input type="text" name="id" data-required="1" class="form-control" value="<%=request.getParameter("number1")%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">权限名称<span class="required">
                                            * </span>
                    </label>
                    <div class="col-md-4">
                        <input type="text" name="permissionName" data-required="1" class="form-control" value="<%=request.getParameter("number2")%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">权限标识<span class="required">
                                            * </span>
                    </label>
                    <div class="col-md-4">
                        <input type="text" name="permissionSign" data-required="1" class="form-control"/>
                    </div>a
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">中文描述<span class="required">
                                            * </span>
                    </label>
                    <div class="col-md-4">
                        <input type="text" name="description" data-required="1" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-offset-3 col-md-9">
                        <button type="button" class="btn green" id="loginBut2">保存</button>
                        <button type="button" class="btn default">取消</button>
                    </div>
                </div>
            </div>
        </form>
        <!-- END FORM-->
    </div>
</div>

<script type="text/javascript" src="app/js/editauth.js"></script>