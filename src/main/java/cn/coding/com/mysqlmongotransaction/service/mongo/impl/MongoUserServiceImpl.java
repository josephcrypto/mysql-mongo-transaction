package cn.coding.com.mysqlmongotransaction.service.mongo.impl;


import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.common.RUtil;
import cn.coding.com.mysqlmongotransaction.common.SystemException;
import cn.coding.com.mysqlmongotransaction.entity.mongo.MongoUser;
import cn.coding.com.mysqlmongotransaction.repository.mongo.MongoUserRepository;
import cn.coding.com.mysqlmongotransaction.service.mongo.MongoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MongoUserServiceImpl implements MongoUserService {

    @Autowired
    private MongoUserRepository mongoUserRepository;


    @Override
    public R save(MongoUser mongoUser) {
        MongoUser mongoUserSave = mongoUserRepository.save(mongoUser);
        log.info("User information is stored : testUserSave = " + mongoUserSave);
        return RUtil.success("");
    }

    @Override
    @Transactional(value = "MONGO_TRANSACTION_MANAGER", propagation = Propagation.REQUIRED)
    public R bathSave(String unitId, Boolean rollBack) {
        for (int i = 0; i <= 10; i++){
            //This section adds data normally, and test rollbacks then the row exception information
            if (unitId.equals("003") && rollBack) {
                throw new SystemException("Test rollBack deliberately thrown an Exception");
            }
            MongoUser user = new MongoUser();
            user.setUserId(unitId + "U0" + i);
            user.setUserName("user" + i);
            user.setUnitId(unitId);
            save(user);
        }
        return RUtil.success("");
    }
}
