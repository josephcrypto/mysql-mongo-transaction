package cn.coding.com.mysqlmongotransaction.entity.mongo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "test_unit")
public class MongoUnit {

    public static final long serialVersionUID = 1L;

    private ObjectId id;

    private String unitId;

    private String unitName;

}
