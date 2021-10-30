package cn.coding.com.mysqlmongotransaction.enums;

public enum DbTypeEnum {

    MONGO(0,"Mongo library"),
    MYSQL(1,"Main library");


    private Integer code;

    private String message;
    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }

    DbTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
