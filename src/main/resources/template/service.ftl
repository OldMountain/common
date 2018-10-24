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
     */
    List<${entity}> listAll();

    /**
     * 根据条件查询列表
     * @param ${entity?uncap_first}
     * @return
     */
    List<${entity}> list(${entity} ${entity?uncap_first});

    /**
     * 根据${primaryKey.filedName}查询
     * @param ${primaryKey.filedName}
     * @return
     */
    ${entity} findById(${primaryKey.dataType} ${primaryKey.filedName});

    /**
     * 根据条件修改
     * @param ${entity?uncap_first}
     * @return
     */
    int modify(${entity} ${entity?uncap_first});

    /**
     * 保存
     * @param ${entity?uncap_first}
     * @return
     */
    int save(${entity} ${entity?uncap_first});

    /**
     * 根据${primaryKey.filedName}删除
     * @param ${primaryKey.filedName}
     * @return
     */
    int remove(${primaryKey.dataType} ${primaryKey.filedName});

    /**
     * 批量删除
     * @param ${primaryKey.filedName+"s"}
     * @return
     */
    int batchRemove(${primaryKey.dataType}[] ${primaryKey.filedName+"s"});
}