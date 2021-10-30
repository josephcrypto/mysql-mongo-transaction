package cn.coding.com.mysqlmongotransaction.service.mongo.impl;


import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.common.RUtil;
import cn.coding.com.mysqlmongotransaction.common.SystemException;
import cn.coding.com.mysqlmongotransaction.entity.mongo.MongoUnit;
import cn.coding.com.mysqlmongotransaction.enums.REnum;
import cn.coding.com.mysqlmongotransaction.repository.mongo.MongoUnitRepository;
import cn.coding.com.mysqlmongotransaction.service.mongo.MongoUnitService;
import cn.coding.com.mysqlmongotransaction.service.mongo.MongoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MongoUnitServiceImpl implements MongoUnitService {

    @Autowired
    private MongoUnitRepository mongoUnitRepository;

    @Autowired
    private MongoUserService mongoUserService;


    @Override
    public R save(MongoUnit unit) {
        MongoUnit mongoUnitSave = mongoUnitRepository.save(unit);
        log.info("Unit information is stored : testUnitSave = "+ mongoUnitSave);
        return RUtil.success("");
    }

    @Override
    @Transactional(value = "MONGO_TRANSACTION_MANAGER")
    public R bathSave(Boolean rollBack) {
        try {
            for (int i = 0; i <= 4; i++){
                MongoUnit unit = new MongoUnit();
                unit.setUnitId("00" + i);
                unit.setUnitName("unit" + i);
                mongoUserService.bathSave(unit.getUnitId(), rollBack);
                save(unit);
            }
            return RUtil.success("");
        } catch (Exception e){
            log.error("Save data failed : msg {}", e.getMessage());
            throw new SystemException(REnum.ERROR.getCode(), "Save data Failed Error: "+ e.getMessage());
        }
    }
}
