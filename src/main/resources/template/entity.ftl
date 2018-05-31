package ${entityPackage};
import com.google.gson.Gson;
<#if datePackage??>${datePackage}</#if>

/**
 * ${className}
 *
 * @author ${author}
 * @date ${date}
 */
public class ${className} {
<#list table as row>

    /**
     * ${row.remarks}
     */
    private ${row.dataType} ${row.filedName};
</#list>

<#list table as row>

    /** ${row.remarks}
     *
     * @return ${row.filedName}
     */
    public ${row.dataType} get${row.filedName?cap_first}() {
        return ${row.filedName};
    }
</#list>

<#list table as row>

    /**
     * ${row.remarks}
     */
    public void set${row.filedName?cap_first}(${row.dataType} ${row.filedName}) {
        this.${row.filedName} = ${row.filedName};
    }
</#list>

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}