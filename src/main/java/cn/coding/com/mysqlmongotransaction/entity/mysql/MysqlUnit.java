package cn.coding.com.mysqlmongotransaction.entity.mysql;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mysql_unit")
public class MysqlUnit {

    public static final long serialVersionUID = 1L;

    @Id
    private String unitId;

    private String unitName;
}
