package cn.coding.com.mysqlmongotransaction.entity.mongo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "test_user")
public class MongoUser {

    public static final long serialVersionUID = 1L;

    private ObjectId id;

    private String userId;

    private String userName;

    private String unitId;

}
