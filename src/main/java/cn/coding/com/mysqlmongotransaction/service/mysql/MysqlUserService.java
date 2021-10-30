package cn.coding.com.mysqlmongotransaction.service.mysql;

import cn.coding.com.mysqlmongotransaction.common.R;
import cn.coding.com.mysqlmongotransaction.entity.mysql.MysqlUser;
import org.springframework.stereotype.Component;

@Component
public interface MysqlUserService {

    R save(MysqlUser user);

    R bathSave(String unitId, Boolean rollBack);
}
