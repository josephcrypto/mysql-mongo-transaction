package cn.coding.com.mysqlmongotransaction.controller;

import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.enums.DbTypeEnum;
import cn.coding.com.mysqlmongotransaction.service.mongo.MongoUnitService;
import cn.coding.com.mysqlmongotransaction.service.mysql.MysqlUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "test/unit")
public class UnitController {

    @Autowired
    private MongoUnitService mongoUnitService;

    @Autowired
    private MysqlUnitService mysqlUnitService;

    @PostMapping("/bathSave/{dbType}/{rollBack}")
    public R bathSave(@PathVariable DbTypeEnum dbType, @PathVariable Boolean rollBack){
        switch (dbType) {
            case MONGO:
                return mongoUnitService.bathSave(rollBack);
            default:
                return mysqlUnitService.bathSave(rollBack);
        }

    }
}
