package com.nxd.ftt.common.freemarker;

import java.sql.Types;

/**
 * DataType
 *
 * @author luhangqi
 * @date 2018/2/5
 */
public class DataType {

    public static String getDataType(int type) {
        String dataType = "";
        switch (type) {
//            case Types.CHAR:
//            case Types.VARCHAR:
//            case Types.LONGNVARCHAR:
//            case Types.DECIMAL:
//            case Types.NUMERIC:
//                dataType = "String";
//                break;
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                dataType = "byte[]";
                break;
            case Types.BIT:
                dataType = "boolean";
                break;
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.BIGINT:
            case Types.INTEGER:
                dataType = "Integer";
                break;
            case Types.REAL:
                dataType = "float";
                break;
            case Types.FLOAT:
            case Types.DOUBLE:
                dataType = "double";
                break;
            case Types.DATE:
            case Types.TIME:
            case Types.TIMESTAMP:
                dataType = "Date";
                break;
            default:
                dataType = "String";
        }
        return dataType;
    }
}
