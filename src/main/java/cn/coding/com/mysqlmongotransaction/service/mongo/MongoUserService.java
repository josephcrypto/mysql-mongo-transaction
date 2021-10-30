package cn.coding.com.mysqlmongotransaction.service.mongo;


import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.entity.mongo.MongoUser;
import org.springframework.stereotype.Component;

@Component
public interface MongoUserService {

    R save(MongoUser user);

    R bathSave(String unitId, Boolean rollBack);
}
