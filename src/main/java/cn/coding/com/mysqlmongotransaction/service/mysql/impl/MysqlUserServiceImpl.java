package cn.coding.com.mysqlmongotransaction.service.mysql.impl;

import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.common.RUtil;
import cn.coding.com.mysqlmongotransaction.common.SystemException;
import cn.coding.com.mysqlmongotransaction.entity.mysql.MysqlUser;
import cn.coding.com.mysqlmongotransaction.repository.mysql.MysqlUserRepository;
import cn.coding.com.mysqlmongotransaction.service.mysql.MysqlUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MysqlUserServiceImpl implements MysqlUserService {

    @Autowired
    private MysqlUserRepository mysqlUserRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public R save(MysqlUser user) {
        MysqlUser userSave = mysqlUserRepository.save(user);
        log.info("User information is stored : userSave {}", userSave);
        return RUtil.success("");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public R bathSave(String unitId, Boolean rollBack) {
        for (int i = 0; i <= 10; i++){
            //This section adds data normally, and test rollbacks then the row exception information
            if (unitId.equals("003") && rollBack) {
                throw new SystemException("Test rollBack deliberately thrown Exceptions");
            }
            MysqlUser user = new MysqlUser();
            user.setUserId(unitId + "U0" + i);
            user.setUserName("user" + i);
            user.setUnitId(unitId);
            save(user);
        }
        return RUtil.success("");
    }
}
