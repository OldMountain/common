package com.nxd.ftt.common.enumtype;

import com.nxd.ftt.common.util.DateUtils;

/**
 * ConstellationEnum
 *
 * @author luhangqi
 * @date 2018/10/25
 */
public enum ConstellationEnum {
    /**
     * 序号，名称，起止日期,描述
     */
    SHUI_PING(1, "水瓶座", "01-20", "02-18")
    ,SHUANG_YU(2, "双鱼座", "02-19", "03-20")
    ,BAI_YANG(3, "白羊座", "03-21", "04-19")
    ,JIN_NIU(4, "金牛座", "04-20", "05-20")
    ,SHUANG_ZI(5, "双子座", "05-21", "06-20")
    ,JU_XIE(6, "巨蟹座", "06-21", "07-22")
    ,SHI_ZI(7, "狮子座", "07-23", "08-22")
    ,CHU_NV(8, "处女座", "08-23", "09-22")
    ,TIAN_CHENG(9, "天秤座", "09-23", "10-22")
    ,TIAN_XIE(10, "天蝎座", "10-23", "11-21")
    ,SHE_SHOU(11, "射手座", "11-22", "12-21")
    ,MO_JIE(12, "摩羯座", "12-22", "01-19")
    ;

    private int index;
    private String name;
    private String startTime;
    private String endTime;

    ConstellationEnum(int index, String name, String startTime, String endTime) {
        this.index = index;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static String getName(String birthDay) {
        ConstellationEnum[] constellationEnums = ConstellationEnum.values();
        for (ConstellationEnum constellationEnum : constellationEnums) {
            if (DateUtils.parse(birthDay) >= DateUtils.parse(constellationEnum.startTime) && DateUtils.parse(birthDay) <= DateUtils.parse(constellationEnum.endTime)) {
                return constellationEnum.name;
            }
            if (DateUtils.parse(constellationEnum.endTime) < DateUtils.parse(constellationEnum.startTime)) {
                boolean before = DateUtils.parse(birthDay) >= DateUtils.parse("01-01") && DateUtils.parse(birthDay) <= DateUtils.parse(constellationEnum.startTime);
                boolean after = DateUtils.parse(birthDay) >= DateUtils.parse(constellationEnum.endTime) && DateUtils.parse(birthDay) <= DateUtils.parse("12-31");
                if (before || after) {
                    return constellationEnum.name;
                }
            }
        }
        return "";
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
