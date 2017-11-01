package com.xtt.common.dao.po;

/**
 * @ClassName com.xtt.common.dao.po.ApiRetInfoPO.java
 * @Description:接口服务返回的信息
 * @author zz
 * @version V1.0
 * @date 2017年10月24日 下午7:10:47
 */
public class ApiRetInfoPO {
    private String status; // 状态
    private String code; // 编码
    private String msg; // 消息
    private String token;// 日志跟踪使用

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
