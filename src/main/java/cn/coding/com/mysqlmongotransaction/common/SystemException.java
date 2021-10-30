package cn.coding.com.mysqlmongotransaction.common;


import cn.coding.com.mysqlmongotransaction.enums.REnum;

public class SystemException extends RuntimeException{

    private Integer code;

    public SystemException(REnum rEnum){
        super(rEnum.getMessage());

        this.code = rEnum.getCode();
    }

    public SystemException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public SystemException(String message){
        super(message);
        this.code = CommonConstants.FAIL;
    }
}
