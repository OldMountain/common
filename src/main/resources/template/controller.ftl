package ${controllerPackage};

import ${entityPackage}.${entity};
import com.nxd.ftt.entity.Result;
import ${servicePackage}.${service};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

/**
 * ${className}
 *
 * @author ${author}
 * @date ${date}
 */
@Controller
@RequestMapping("/${objectName}")
public class ${className} {

    @Autowired
    private ${service} ${service?uncap_first};


    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        List<${entity}> ${objectName}List = null;
        try {
            ${objectName}List = ${service?uncap_first}.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mv.addObject("${objectName}List", ${objectName}List);
        mv.setViewName("<#if listPackage??>${listPackage}</#if>${list}");
        return mv;
    }

    /**
     * 跳转到新增页面
     *
     * @return
     */
    @RequestMapping("/goAdd")
    public ModelAndView goAdd() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "save");
        mv.setViewName("<#if editPackage??>${editPackage}</#if>${edit}");
        return mv;
    }

    /**
     * 跳转到编辑页面
     *
     * @return
     */
    @RequestMapping("/goEdit")
    public ModelAndView goEdit(${primaryKey.dataType} ${primaryKey.filedName}) {
        ModelAndView mv = new ModelAndView();
        ${entity} ${entity?uncap_first} = null;
        try {
            ${entity?uncap_first} = ${service?uncap_first}.findById(${primaryKey.filedName});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mv.addObject("msg", "edit");
        mv.addObject("${entity?uncap_first}", ${entity?uncap_first});
        mv.setViewName("<#if editPackage??>${editPackage}</#if>${edit}");
        return mv;
    }

    /**
     * 保存
     *
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(${entity} ${entity?uncap_first}) {
        Result result = new Result();
        try {
            ${service?uncap_first}.save(${entity?uncap_first});
        } catch (SQLException e) {
            e.printStackTrace();
            result.setStatus(Const.FAIL);
        }
        return result;
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(${entity} ${entity?uncap_first}) {
        Result result = new Result();
        try {
            ${service?uncap_first}.modify(${entity?uncap_first});
        } catch (SQLException e) {
            e.printStackTrace();
            result.setStatus(Const.FAIL);
        }
        return result;
    }

}
