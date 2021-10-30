package cn.coding.com.mysqlmongotransaction.service.mysql;

import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.entity.mysql.MysqlUnit;

public interface MysqlUnitService {

    R save(MysqlUnit unit);

    R bathSave(Boolean rollBack);

}
