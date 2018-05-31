package com.nxd.ftt.common.enumtype;

/**
 * 返回结果枚举
 * Created by XXL on 2016/5/24.
 */
public enum ResultEnum {



    /**
     * 200：成功
     */
    SUCCESS(200, "成功"),

    /**
     * ：未知错误
     */
    ERROR(500, "未知错误"),

    /**
     * ：空指针
     */
    ERROR_NULL(501, "空指针"),

    /**
     * ：类型错误
     */
    ERROR_CLASS(502, "类型错误"),

    /**
     * 503：参数错误
     */
    ERROR_PARAMETER(503, "参数错误"),

    /**
     * 504：系统错误
     */
    ERROR_SYSTEM(504, "系统错误"),

    /**
     * 505：服务器拒绝连接
     */
    ERROR_CONNECTION_DENIED(505, "服务器拒绝连接"),
    /**
     * ：逻辑错误
     */
    LOGICAL_ERROR(800, "逻辑错误"),

    /**
     * 805：验证用户名密码错误
     */
    ERROR_VERIFICATION(805, "验证用户名密码错误"),

    /**
     * 806：验证用户名密码错误
     */
    ERROR_EXISTENCE(806, "没找到该用户"),

    /**
     * 807：验证码不存在
     */
    ERROR_ISNOTEXISIENCECODE(807, "不存在验证码"),

    /**
     * 808：验证码不存在
     */
    ERROR_CODEOVERDUE(808, "验证码过期"),

    /**
     * 809：验证码错误
     */
    ERROR_CODEERROR(809, "验证码错误"),
    /**
     * 810：重复注册
     */
    ERROR_DUPLICATEREGISTRATION(810, "重复注册");
    /**
     * 编码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    /**
     *
     * Creates a new instance of RoomStatusEnum.
     *
     * @param p_code
     *            编码
     * @param p_desc
     *            描述
     */
    private ResultEnum(Integer p_code, String p_desc) {
        this.code = p_code;
        this.desc = p_desc;
    }

    /**
     * code.
     *
     * @return code 取得
     */
    public Integer getCode() {
        return code;
    }

    /**
     * desc.
     *
     * @return desc 取得
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 返回编码.
     *
     * @return 编码
     * @see Enum#toString()
     */
    @Override
    public String toString() {
        return String.valueOf(this.getCode());
//		return new Gson().toJson(this);
    }

    /**
     *
     * getEnumByCode:(根据编码找到对象). <br/>
     *
     * @param code
     *            枚举编码
     * @return 枚举对象
     */
    public static ResultEnum getEnumByCode(Integer code) {
        ResultEnum result = null;
        ResultEnum[] values = ResultEnum.values();
        for (ResultEnum value : values) {
            if (value.getCode().intValue()== code.intValue()) {
                result = value;
                break;
            }
        }
        return result;
    }
}
