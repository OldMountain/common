package ${servicePackage};

import ${entityPackage}.${entity};
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * ${className}
 *
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface ${className} {


    /**
     * 查询列表
     * @return
     * @throws SQLException
     */
    List<${entity}> listAll() throws SQLException;

    /**
     * 根据条件查询列表
     * @param ${entity?uncap_first}
     * @return
     * @throws SQLException
     */
    List<${entity}> list(${entity} ${entity?uncap_first}) throws SQLException;

    /**
     * 根据${primaryKey.filedName}查询
     * @param ${primaryKey.filedName}
     * @return
     * @throws SQLException
     */
    ${entity} findById(${primaryKey.dataType} ${primaryKey.filedName}) throws SQLException;

    /**
     * 根据条件修改
     * @param ${entity?uncap_first}
     * @return
     * @throws SQLException
     */
    int modify(${entity} ${entity?uncap_first}) throws SQLException;

    /**
     * 保存
     * @param ${entity?uncap_first}
     * @return
     * @throws SQLException
     */
    int save(${entity} ${entity?uncap_first}) throws SQLException;

    /**
     * 根据${primaryKey.filedName}删除
     * @param ${primaryKey.filedName}
     * @return
     * @throws SQLException
     */
    int remove(${primaryKey.dataType} ${primaryKey.filedName}) throws SQLException;

    /**
     * 批量删除
     * @param ${primaryKey.filedName+"s"}
     * @return
     * @throws SQLException
     */
    int batchRemove(${primaryKey.dataType}[] ${primaryKey.filedName+"s"}) throws SQLException;
}