package cn.coding.com.mysqlmongotransaction.common;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {

    public static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code = 0;

    @Getter
    @Setter
    private int msgCode = 0;

    @Getter
    @Setter
    private String msg = "success";

    @Getter
    @Setter
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(T data, int msgCode, String msg) {
        super();
        this.data = data;
        this.msgCode = msgCode;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = -1;
    }
}

//    @Data
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public class R<T> implements Serializable {
//
//    private Integer code;
//
//    private String msg;
//
//    private T data;
//
//    }

