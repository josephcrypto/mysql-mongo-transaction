package cn.coding.com.mysqlmongotransaction.service.mysql.impl;

import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.common.RUtil;
import cn.coding.com.mysqlmongotransaction.common.SystemException;
import cn.coding.com.mysqlmongotransaction.entity.mysql.MysqlUnit;
import cn.coding.com.mysqlmongotransaction.enums.REnum;
import cn.coding.com.mysqlmongotransaction.repository.mysql.MysqlUnitRepository;
import cn.coding.com.mysqlmongotransaction.service.mysql.MysqlUnitService;
import cn.coding.com.mysqlmongotransaction.service.mysql.MysqlUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MysqlUnitServiceImpl implements MysqlUnitService {

    @Autowired
    private MysqlUnitRepository mysqlUnitRepository;

    @Autowired
    private MysqlUserService mysqlUserService;



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public R save(MysqlUnit unit) {
        MysqlUnit unitSave = mysqlUnitRepository.save(unit);
        log.info("Unit information is stored : UnitSave = " + unitSave);
        return RUtil.success("");
    }

    @Override
    @Transactional(value = "MYSQL_PLATFORM_TX_MANAGER", propagation = Propagation.REQUIRED,
    rollbackFor = {Exception.class})
    public R bathSave(Boolean rollBack) {
        try{
            for (int i = 0; i < 4; i++){
                MysqlUnit unit = new MysqlUnit();
                unit.setUnitId("00" + i);
                unit.setUnitName("unit" + i);
                mysqlUserService.bathSave(unit.getUnitId(), rollBack);
                save(unit);
            }
            return RUtil.success("");
        } catch (SystemException e) {
            log.error("Failed to save data: msg: {} ", e.getMessage());
            throw new SystemException(REnum.ERROR.getCode(), "Failed to save data Error: " + e.getMessage());
        }
    }
}
