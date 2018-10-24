package ${implPackage};

import ${daoPackage}.${dao};
import ${entityPackage}.${entity};
import ${servicePackage}.${service};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * ${className}
 *
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${className} implements ${service} {

    @Autowired
    private ${dao} ${dao?uncap_first};

    /**
     * 查询列表
     * @return
     */
    @Override
    public List<${entity}> listAll() {
       return ${dao?uncap_first}.list(new ${entity}());
    }

    /**
     * 根据条件查询列表
     * @param ${entity?uncap_first}
     * @return
     */
    @Override
    public List<${entity}> list(${entity} ${entity?uncap_first}) {
       return ${dao?uncap_first}.list(${entity?uncap_first});
    }

    /**
     * 根据${primaryKey.filedName}查询
     * @param ${primaryKey.filedName}
     * @return
     */
    @Override
    public ${entity} findById(${primaryKey.dataType} ${primaryKey.filedName}) {
       return ${dao?uncap_first}.findById(${primaryKey.filedName});
    }

    /**
     * 根据条件修改
     * @param ${entity?uncap_first}
     * @return
     */
    @Override
    public int modify(${entity} ${entity?uncap_first}) {
       return ${dao?uncap_first}.update(${entity?uncap_first});
    }

    /**
     * 保存
     * @param ${entity?uncap_first}
     * @return
     */
    @Override
    public int save(${entity} ${entity?uncap_first}) {
       return ${dao?uncap_first}.insert(${entity?uncap_first});
    }

    /**
     * 根据${primaryKey.filedName}删除
     * @param ${primaryKey.filedName}
     * @return
     */
    @Override
    public int remove(${primaryKey.dataType} ${primaryKey.filedName}) {
       return ${dao?uncap_first}.delete(${primaryKey.filedName});
    }

    /**
     * 批量删除
     * @param ${primaryKey.filedName+"s"}
     * @return
     */
    @Override
    public int batchRemove(${primaryKey.dataType}[] ${primaryKey.filedName+"s"}) {
       return ${dao?uncap_first}.batchDelete(${primaryKey.filedName+"s"});
    }
}
