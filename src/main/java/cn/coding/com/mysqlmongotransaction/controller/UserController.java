package cn.coding.com.mysqlmongotransaction.controller;

import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.enums.DbTypeEnum;
import cn.coding.com.mysqlmongotransaction.service.mongo.MongoUserService;
import cn.coding.com.mysqlmongotransaction.service.mysql.MysqlUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "test/user")
public class UserController {

    @Autowired
    private MongoUserService mongoUserService;

    @Autowired
    private MysqlUserService mysqlUserService;

    @PostMapping("/bathSave/{dbType}/{unitId}/{rollBack}")
    public R bathSave(@PathVariable DbTypeEnum dbType, @PathVariable String unitId, @PathVariable Boolean rollBack){
        switch (dbType) {
            case MONGO:
                return mongoUserService.bathSave(unitId, rollBack);
            default:
                return mysqlUserService.bathSave(unitId, rollBack);
        }
    }
}
