package cn.coding.com.mysqlmongotransaction.repository.mysql;

import cn.coding.com.mysqlmongotransaction.entity.mysql.MysqlUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlUserRepository extends JpaRepository<MysqlUser, String> {
}
