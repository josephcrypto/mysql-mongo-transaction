package cn.coding.com.mysqlmongotransaction.enums;

public enum REnum {

    SUCCESS(0,"success"),
    ERROR(1,"fail");

    private Integer code;

    private String message;
    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }

    REnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
