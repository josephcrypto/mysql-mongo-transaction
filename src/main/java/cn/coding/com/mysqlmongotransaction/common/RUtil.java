package cn.coding.com.mysqlmongotransaction.common;


import cn.coding.com.mysqlmongotransaction.enums.REnum;

public class RUtil {

    public static R success(Object object){
        R r = new R();
        r.setCode(CommonConstants.SUCCESS);
        r.setMsg("ok");
        r.setData(object);
        return r;
    }

    public static R success(REnum rMsg) {
        R r = new R();
        r.setCode(CommonConstants.SUCCESS);
        r.setMsgCode(rMsg.getCode());
        r.setMsg(rMsg.getMessage());
        return r;
    }

    public static R error(REnum rMsg){
        R r = new R();
        r.setCode(CommonConstants.FAIL);
        r.setMsgCode(rMsg.getCode());
        r.setMsg(rMsg.getMessage());
        return r;
    }

    public static R error(Integer code, String msg){
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static R error(String msg){
        R r = new R();
        r.setCode(CommonConstants.FAIL);
        r.setMsg(msg);
        return r;
    }
}
