package com.nxd.ftt.common.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Freemarker
 *
 * @author luhangqi
 * @date 2018/2/2
 */
public class Freemarker {

    private static String propPath = "template/template.properties";


    private static void main(String[] args) {
        try {
            generator(propPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void init(String propPath) throws IOException {
        //1.创建配置实例Cofiguration
        FMUtils.cfg = new Configuration(Configuration.VERSION_2_3_27);
        FMUtils.map.put("date", FMUtils.getNow1());
        //载入配置文件
        FMUtils.getProperties(propPath);
        FMUtils.cfg.setDirectoryForTemplateLoading(new File(FMUtils.getRootPath() + FMUtils.prop.getProperty("directoryForTemplate")));
        FMUtils.objectName = FMUtils.prop.getProperty("generator.name") != null && !"".equals(FMUtils.prop.getProperty("generator.name")) ? FMUtils.prop.getProperty("generator.name") : FMUtils.prop.getProperty("table");
        //别名，小写
        FMUtils.map.put("objectName", FMUtils.objectName);
        //数据库表名
        FMUtils.map.put("tableName", FMUtils.prop.getProperty("table"));
        //作者
        FMUtils.map.put("author", FMUtils.prop.getProperty("author"));
        //保存文件目录
        FMUtils.savePath(FMUtils.map);
//        map.put("dao", FMUtils.getFilePath("dao"));
//        map.put("mapper", FMUtils.getFilePath("mapper"));
//        map.put("service", FMUtils.getFilePath("service"));
//        map.put("imp", FMUtils.getFilePath("imp"));
//        map.put("jsp", FMUtils.getFilePath("jsp"));
    }


    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(FMUtils.prop.getProperty("driverClassName"));
        Connection con = DriverManager.getConnection(FMUtils.prop.getProperty("url"), FMUtils.prop.getProperty("username"), FMUtils.prop.getProperty("password"));
        return con;
    }

    private static void setData() throws SQLException, ClassNotFoundException {
        List<Table> list = new ArrayList<>();
        Connection con = getConnection();
        DatabaseMetaData data = con.getMetaData();
//        参数:catalog:目录名称，一般都为空.
//        参数：schema:数据库名，对于oracle来说就用户名
//        参数：tablename:表名称
//        参数：type :表的类型(TABLE | VIEW)
//        ResultSet tableResult = data.getTables(null, "mysql", prop.getProperty("table").toUpperCase(), new String[]{"TABLE"});
        ResultSet key = data.getPrimaryKeys(con.getCatalog(), con.getSchema(), FMUtils.prop.getProperty("table").toUpperCase());
        String keyName = "";
        while (key.next()) {
            Table table = new Table();
            table.setColumnName(key.getString("COLUMN_NAME"));
            keyName = table.getColumnName();
            FMUtils.map.put("primaryKey", table);
        }
        ResultSet result = data.getColumns(con.getCatalog(), con.getSchema(), FMUtils.prop.getProperty("table").toUpperCase(), "%");
        while (result.next()) {
            Table table = new Table();
            table.setColumnName(result.getString("COLUMN_NAME"));
            table.setRemarks(result.getString("REMARKS"));
            String dataType = result.getString("DATA_TYPE");
            table.setDataType(dataType);
            table.setType(result.getString("TYPE_NAME"));
            list.add(table);
            if (dataType.equals("Date")) {
                Table tableB = new Table();
                tableB.setColumnName(result.getString("COLUMN_NAME"));
                tableB.setFiledName(tableB.getFiledName() + "Str");
                tableB.setRemarks(result.getString("REMARKS"));
                tableB.setDataType("String");
                tableB.setType(result.getString("TYPE_NAME"));
                list.add(tableB);
            }
            if (keyName.equals(table.getColumnName())) {
                Table t = (Table) FMUtils.map.get("primaryKey");
                t.setDataType(dataType);
                FMUtils.map.put("primaryKey", t);
            }
        }
        FMUtils.map.put("table", list);
    }

    public static void generator(String propPath) throws ClassNotFoundException, TemplateException, SQLException, IOException {
        init(propPath);
        createEntity();
        createDao();
        createMapper();
        createService();
        createImpl();
        createController();
        createList();
        createEdit();
        createView();
    }


    /**
     * 创建 Entity.java
     *
     * @throws ClassNotFoundException
     * @throws TemplateException
     * @throws SQLException
     * @throws IOException
     */
    private static void createEntity() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        create(FMUtils.prop.getProperty("template.entity"), FMUtils.toUpperFristChar(FMUtils.objectName) + ".java", FMUtils.getPropPath("template.entity.path"));
    }


    /**
     * 创建 Dao.java
     *
     * @throws ClassNotFoundException
     * @throws TemplateException
     * @throws SQLException
     * @throws IOException
     */
    private static void createDao() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = TempEnum.DAO.getValue();
        create(FMUtils.prop.getProperty("template.dao"), FMUtils.toUpperFristChar(FMUtils.objectName) + name + ".java", FMUtils.getPropPath("template.dao.path"));
    }


    /**
     * 创建mapper.xml文件
     *
     * @throws ClassNotFoundException
     * @throws TemplateException
     * @throws SQLException
     * @throws IOException
     */
    private static void createMapper() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = TempEnum.MAPPER.getValue();
        create(FMUtils.prop.getProperty("template.mapper"), FMUtils.toUpperFristChar(FMUtils.objectName) + name + ".xml", FMUtils.getPropPath("template.mapper.path"));
    }


    /**
     * 创建Service.xml文件
     *
     * @throws ClassNotFoundException
     * @throws TemplateException
     * @throws SQLException
     * @throws IOException
     */
    private static void createService() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = TempEnum.SERVICE.getValue();
        create(FMUtils.prop.getProperty("template.service"), FMUtils.toUpperFristChar(FMUtils.objectName) + name + ".java", FMUtils.getPropPath("template.service.path"));
    }


    /**
     * 创建Impl.xml文件
     *
     * @throws ClassNotFoundException
     * @throws TemplateException
     * @throws SQLException
     * @throws IOException
     */
    private static void createImpl() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = TempEnum.IMPL.getValue();
        create(FMUtils.prop.getProperty("template.impl"), FMUtils.toUpperFristChar(FMUtils.objectName) + name + ".java", FMUtils.getPropPath("template.impl.path"));
    }


    /**
     * 创建Controller.xml文件
     *
     * @throws ClassNotFoundException
     * @throws TemplateException
     * @throws SQLException
     * @throws IOException
     */
    private static void createController() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = TempEnum.CONTROLLER.getValue();
        create(FMUtils.prop.getProperty("template.controller"), FMUtils.toUpperFristChar(FMUtils.objectName) + name + ".java", FMUtils.getPropPath("template.controller.path"));
    }


    /**
     * 创建list页面
     *
     * @throws ClassNotFoundException
     * @throws TemplateException
     * @throws SQLException
     * @throws IOException
     */
    private static void createList() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = FMUtils.removeExt(FMUtils.prop.getProperty("template.list"));
        create(FMUtils.prop.getProperty("template.list"), FMUtils.objectName + "_" + name + ".jsp", FMUtils.getPropPath("template.list.path"));
    }

    private static void createEdit() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = FMUtils.removeExt(FMUtils.prop.getProperty("template.edit"));
        create(FMUtils.prop.getProperty("template.edit"), FMUtils.objectName + "_" + name + ".jsp", FMUtils.getPropPath("template.edit.path"));
    }

    private static void createView() throws ClassNotFoundException, TemplateException, SQLException, IOException {
        String name = FMUtils.removeExt(FMUtils.prop.getProperty("template.view"));
        create(FMUtils.prop.getProperty("template.view"), FMUtils.objectName + "_" + name + ".jsp", FMUtils.getPropPath("template.view.path"));
    }

    /**
     * 根据模板生成文件
     *
     * @param temp     模板名
     * @param fileName 生成的文件名
     * @param path     生成文件路径
     * @throws IOException
     * @throws TemplateException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void create(String temp, String fileName, String path) {
        Template template;
        Writer out = null;
        try {
            template = FMUtils.cfg.getTemplate(temp);
            //建立数据模型（Map）
            setData();
            //获取输出流（指定到控制台（标准输出））
            File file = new File(path, fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            out = new OutputStreamWriter(new FileOutputStream(file));
            FMUtils.map.put("className", FMUtils.removeExt(fileName));
            template.process(FMUtils.map, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
