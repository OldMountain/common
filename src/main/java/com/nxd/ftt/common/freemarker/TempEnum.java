package com.nxd.ftt.common.freemarker;

public enum TempEnum {
    ENTITY("entity","Entity")
    ,DAO("dao","Dao")
    ,MAPPER("mapper","Mapper")
    ,SERVICE("service","Service")
    ,IMPL("impl","ServiceImpl")
    ,CONTROLLER("controller","Controller")
    ,JSP_LIST("list","list")
    ,JSP_EDIT("edit","edit")
    ,JSP_VIEW("view","view")
    ;
    private String key;
    private String value;


    TempEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValue(String key) {
        TempEnum[] values = TempEnum.values();
        for (TempEnum value : values) {
            if (value.getKey().equals(key)) {
                return value.getValue();
            }
        }
        return key;
    }
}
