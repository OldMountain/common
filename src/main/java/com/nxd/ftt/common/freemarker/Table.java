package com.nxd.ftt.common.freemarker;

/**
 * Table
 *
 * @author luhangqi
 * @date 2018/2/5
 */
public class Table {
    private String columnName;
    private String filedName;
    private String dataType;
    private String type;
    private String remarks;

    public String getColumnName() {
        return columnName;
    }

    public String getFiledName() {
        return filedName;
    }

    public String getDataType() {
        return dataType;
    }

    public String getType() {
        return type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        String[] cols = columnName.split("_");
        StringBuilder builder = new StringBuilder();
        builder.append(cols[0].toLowerCase());
        for (int i = 1; i < cols.length; i++) {
            builder.append(FMUtils.toUpperFristChar(cols[i].toLowerCase()));
        }
        this.filedName = builder.toString();
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public void setDataType(String dataType) {
        this.dataType = DataType.getDataType(Integer.parseInt(dataType));
        if (this.dataType.equals("Date")) {
            FMUtils.map.put("datePackage", "import java.util.Date;");
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
