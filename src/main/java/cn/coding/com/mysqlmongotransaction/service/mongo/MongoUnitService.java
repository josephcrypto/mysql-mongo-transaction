package cn.coding.com.mysqlmongotransaction.service.mongo;


import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.entity.mongo.MongoUnit;

public interface MongoUnitService {

    R save(MongoUnit unit);

    R bathSave(Boolean rollBack);
}
