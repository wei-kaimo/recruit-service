package com.system.recruit.common.config;

/**
 * 响应消息枚举，参考HTTP状态码的语义
 */
public enum ResultMsg {
	SOS00000000("不能为空"),
	SOS00000001("手机号码不能为空"),
	SOS00000002("查询时间不能为空"),
	SOS00000003("查询时间格式错误"),
	SOS00000004("渠道编号不能为空"),
	SOS00000005("用户编号不能为空"),
	SOS00000006("上传文件不能为空"),
	SOS00000007("内容管理平台异常"),
	SOS00000008("用户位置不能为空"),
	SOS00000009("查询条件至少为2个字符"),
	SOS00000010("该用户为非渠道用户"),
	SOS00000011("渠道业务信息不存在"),
	SOS00000012("潮品或者服务类型不能为空"),
	SOS00000013("联系人不能为空"),
	SOS00000014("联系方式不能为空"),
	SOS00000015("商品编号不能为空"),
	SOS00000016("组合编号不能为空"),
	SOS00000017("组合数量不能为空"),
	SOS00000018("操作类型不能为空"),
	;

    public String msg;

    ResultMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return msg;
    }
}
