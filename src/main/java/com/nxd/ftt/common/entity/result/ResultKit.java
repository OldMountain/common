package com.nxd.ftt.common.entity.result;


import com.nxd.ftt.common.enumtype.ResultEnum;

/**
 * ResultKit
 *
 * @author luhangqi
 * @date 2018/5/29
 */
public class ResultKit {

    public static Response success() {
        return new Response(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
    }

    public static <T> Response success(T data) {
        Response<T> response = new Response(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), data);
        return response;
    }

    public static Response fail() {
        return new Response<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getDesc());
    }

    public static Response fail(String message) {
        return new Response<>(ResultEnum.ERROR.getCode(), message);
    }

    public static Response error() {
        return new Response<>(ResultEnum.ERROR_SYSTEM.getCode(), ResultEnum.ERROR_SYSTEM.getDesc());
    }

    public static Response error(String message) {
        return new Response<>(ResultEnum.ERROR_SYSTEM.getCode(), message);
    }
}
