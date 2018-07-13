package com.framework.base; 
/**
 * 
 * 接口或类的说明: <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2018年3月9日 下午5:29:08 <br>
 * ==========================
 *
 */
public enum BaseResultCode {
	//枚举常量定义的同时指定状态码
    SUCCESS(0),
    ERROR(-1),
    NOT_AUTH(2);

    private int code; //状态码值

    BaseResultCode(int code){ //非public构造方法
        this.code=code;
    }

     @Override
    public String toString(){
        return String.valueOf(code);
    }
}
