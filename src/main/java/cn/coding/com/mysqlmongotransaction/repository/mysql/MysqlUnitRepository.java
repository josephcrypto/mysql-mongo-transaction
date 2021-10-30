package cn.coding.com.mysqlmongotransaction.repository.mysql;

import cn.coding.com.mysqlmongotransaction.entity.mysql.MysqlUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlUnitRepository extends JpaRepository<MysqlUnit, String> {
}
