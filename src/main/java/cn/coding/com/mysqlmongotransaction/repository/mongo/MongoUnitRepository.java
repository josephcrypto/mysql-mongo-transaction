package cn.coding.com.mysqlmongotransaction.repository.mongo;


import cn.coding.com.mysqlmongotransaction.entity.mongo.MongoUnit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUnitRepository extends MongoRepository<MongoUnit, String> {

}
