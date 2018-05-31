<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackage + "."}${dao}">
    <resultMap id="BaseResultMap" type="${entityPackage + "."}${entity}">
      <#list table as row>
        <result column="${row.columnName}" property="${row.filedName}"/>
      </#list>
    </resultMap>
    <select id="select" parameterType="${entityPackage + "."}${entity}" resultMap="BaseResultMap">
        select
        <trim suffixOverrides=",">
          <#list table as row>
            ${row.columnName},
          </#list>
        </trim>
        from ${tableName}
        <where>
            <#list table as row>
            <if test="${row.filedName} != null and ${row.filedName} !=''">
                and ${row.columnName} = ${"#{" + row.filedName + "}"}
            </if>
            </#list>
        </where>
    </select>
    <select id="selectById" parameterType="${entityPackage + "."}${entity}" resultMap="BaseResultMap">
        select
        <trim suffixOverrides=",">
          <#list table as row>
            ${row.columnName},
          </#list>
        </trim>
        from ${tableName}
        where ${primaryKey.columnName} = ${"#{" + primaryKey.filedName + "}"}
    </select>
    <delete id="batchDelete" parameterType="java.lang.String">
        delete from ${tableName}
        where ${primaryKey.columnName} in
        <foreach item="item" index="index" collection="array" open="(" separator="," close=")">
        ${"#"+"{item}"}
        </foreach>
    </delete>
    <insert id="insert" parameterType="${entityPackage + "."}${entity}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list table as row>
                <#if row.dataType != 'Date'>
            <if test="${row.filedName} != null and ${row.filedName} !=''">
                ${row.columnName},
            </if>
                </#if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list table as row>
                <#if row.dataType != 'Date'>
            <if test="${row.filedName} != null and ${row.filedName} !=''">
                ${"#{" + row.filedName + "}"},
            </if>
                </#if>
            </#list>
        </trim>
    </insert>
    <update id="update" parameterType="${entityPackage + "."}${entity}">
        update ${tableName}
        set
        <trim suffixOverrides=",">
            <#list table as row>
                <#if row.dataType != 'Date'>
            <if test="${row.filedName} != null and ${row.filedName} !=''">
                ${row.columnName} = ${"#{" + row.filedName + "}"},
            </if>
                </#if>
            </#list>
        </trim>
        where ${primaryKey.columnName} = ${"#{" + primaryKey.filedName + "}"}
    </update>
    <delete id="delete" parameterType="${entityPackage + "."}${entity}">
        delete from ${tableName}
        where ${primaryKey.columnName} = ${"#{" + primaryKey.filedName + "}"}
    </delete>
</mapper>