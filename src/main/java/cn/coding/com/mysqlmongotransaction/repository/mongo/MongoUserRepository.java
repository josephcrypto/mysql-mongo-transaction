package cn.coding.com.mysqlmongotransaction.repository.mongo;


import cn.coding.com.mysqlmongotransaction.entity.mongo.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<MongoUser, String> {

}
