package ${daoPackage};

import ${entityPackage}.${entity};
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${className}
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${className} {

    /**
     * 查询
     * @param ${entity?uncap_first}
     * @return
     */
    List<${entity}> list(${entity} ${entity?uncap_first});

    /**
     * 查询
     * @param ${primaryKey.filedName}
     * @return
     */
    ${entity} findById(${primaryKey.dataType} ${primaryKey.filedName});

    /**
    * 批量删除
    * @param ${primaryKey.filedName}
    * @return
    */
    int batchDelete(${primaryKey.dataType}[] ${primaryKey.filedName});

    /**
    * 插入
    * @param ${entity?uncap_first}
    * @return
    */
    int insert(${entity} ${entity?uncap_first});

    /**
    * 更新
    * @param ${entity?uncap_first}
    * @return
    */
    int update(${entity} ${entity?uncap_first});

    /**
    * 根据${primaryKey.filedName}删除
    * @param ${primaryKey.filedName}
    * @return
    */
    int delete(${primaryKey.dataType} ${primaryKey.filedName});
}