package com.system.recruit.common.config;

public interface ErrorMsg {

    // 公共模块错误代码范围SOS00000000 - SOS00009999
    public static final String SOS00000000 = "系统内部错误";
    public static final String SOS00000001 = "参数不能为空";
    public static final String SOS00000002 = "参数长度不能超过{max}";
    public static final String SOS00000003 = "参数长度只能为{min} - {max}";
    public static final String SOS00000004 = "参数枚举值范围是[{flags}]";
    public static final String SOS00000005 = "参数不符合金额格式";
    public static final String SOS00000006 = "参数必须为整数";
    public static final String SOS00000007 = "参数取值范围是{min} - {max}";
    public static final String SOS00000008 = "参数列表长度必须大于{min}";
    public static final String SOS00000009 = "Token校验失败";
    public static final String SOS00000010 = "上传文件名为空";
    public static final String SOS00000011 = "Token已失效";
    public static final String SOS00000012 = "Token校验失败,渠道编号不匹配";
    public static final String SOS00000013 = "Token校验失败,用户编号不匹配";
    public static final String SOS00000014 = "参数长度必须为{min}";
    public static final String SOS00000015 = "系统异常，请联系系统管理员！";
    public static final String SOS00000016 = "组织编号不能为空！";
    public static final String SOS00000017 = "操作员编号不能为空！";
    public static final String SOS00000018 = "渠道编号不能为空！";
    public static final String SOS00000019 = "商品未上架查询条件不能为空！";
    public static final String SOS00000020 = "系统异常（ESB接口异常），请联系系统管理员！";
    public static final String SOS00000021 = "字典查询失败";
    public static final String SOS00000022 = "组织编号不存在";
    public static final String SOS00000023 = "用户token与系统不匹配";
    public static final String SOS00000024 = "客户经理登录失败";
    public static final String SOS00000025 = "渠道用户登录失败";
    public static final String SOS00000026 = "该用户非渠道老板，请确认!";
    public static final String SOS00000027 = "该用户未拥有该权限，请确认!";





    // 用户管理模块错误代码范围SOS00010000 - SOS00019999
    public static final String SOS00010000 = "用户登录短信验证失败";
    public static final String SOS00010001 = "该渠道用户不存在";
    public static final String SOS00010002 = "用户地址ID${1}不存在";
    public static final String SOS00010003 = "订单数量必须大于等于零";
    public static final String SOS00010004 = "用户编号不能为空";
    public static final String SOS00010005 = "云货架登录失败";
    public static final String SOS00010006 = "未签名的sign不能为空";
    public static final String SOS00010007 = "获取用户信息失败";
    public static final String SOS00010008 = "操作员登录失败";
    public static final String SOS00010009 = "该手机号用户在系统中不存在，请联系管理员";
    public static final String SOS00010010 = "取号获取token失败";
    public static final String SOS00010011 = "自动取号失败";
    public static final String SOS00010012 = "用户登出失败";
    public static final String SOS00010013 = "操作员信息获取失败";
    public static final String SOS00010014 = "操作员信息获取失败,操作员信息不存在";
    public static final String SOS00010015 = "操作员手机号变更，请重新登录";
    public static final String SOS00010016 = "新增操作员账号失败";
    public static final String SOS00010017 = "权限申请提交失败";
    public static final String SOS00010018 = "审批列表查询失败";
    public static final String SOS00010019 = "权限修改失败";
    public static final String SOS00010020 = "权限修改失败,原审批记录不存在";
    public static final String SOS00010021 = "登录校验失败";
    public static final String SOS00010022 = "管理平台角色未配置";
    public static final String SOS00010023 = "已存在该手机号的操作员，请勿重复提交";
    public static final String SOS00010024 = "暂时未开通这个权限";
    public static final String SOS00010025 = "权限审批中";






    // 渠道管理模块错误代码范围SOS00020000 - SOS00029999
    public static final String SOS00020000 = "渠道注册信息不存在";
    public static final String SOS00020001 = "渠道${1}不存在";
    public static final String SOS00020002 = "渠道${1}未注册";


    // 商品管理模块错误代码范围SOS00030000 - SOS00039999
    public static final String SOS00030000 = "商品${1}不存在";
    public static final String SOS00030001 = "添加原子产品${1}不存在";
    public static final String SOSOOO30002 = "商品编号不存在";
    public static final String SOSOOO30003 = "规格参数未配置";
    public static final String SOSOOO30004 = "商品${1}库存不足";


    // 订单管理模块错误代码范围SOS00040000 - SOS00049999
    public static final String SOS00040000 = "订单不存在，订单状态更新失败";//订单不存在，订单状态更新失败
    public static final String SOS00040001 = "意向单已失效";
    public static final String SOS00040002 = "意向单锁单超时";
    public static final String SOS00040003 = "当前订单状态错误,无法更新";
    public static final String SOS00040004 = "订单状态更新失败";
    public static final String SOS00040005 = "该意向单不存在或者已被其他商户锁定";
    public static final String SOS00040006 = "该意向单状态异常，无法更新";
    public static final String SOS00040007 = "该意向单状态更新失败";
    public static final String SOS00040008 = "取货码生成失败";
    public static final String SOS00040009 = "调用ERP下单接口失败";


	//消息推送错误代码范围SOS00050000 - SOS00059999
    public static final String SOS00050000 = "订单编号为空，消息推送处理结束";
    public static final String SOS00050001 = "订单经纬度为空，消息推送处理结束";
    public static final String SOS00050002 = "订单附近无渠道，消息推送处理结束";

	//elasticSearch错误代码范围SOS00060000 - SOS00069999
    public static final String SOS00060000 = "连接搜索库异常，搜索失败";
    public static final String SOS00060001 = "搜索无数据";

    //商品配置错误代码SOS00070000 - SOS00079999
    public static final String SOS00070000 ="图片上传失败";


  //地址管理错误代码SOS00080000 - SOS00089999
    public static final String SOS00080000 ="地址编号不能为空";
    public static final String SOS00080002 ="联系号码不能为空";
    public static final String SOS00080003 ="经度不能为空";
    public static final String SOS00080004 ="纬度不能为空";
    public static final String SOS00080005 ="详细信息不能为空";

    //店铺模块错误代码SOS00090000 - SOS00099999
    public static final String SOS00090000 ="品类查询失败";

    //取号模块错误代码SOS00100000 - SOS00019999
    public static final String SOS00100000 ="webId不能为空";
    public static final String SOS00100001 ="token不能为空";
    public static final String SOS00100002 ="userInformation不能为空";



}
