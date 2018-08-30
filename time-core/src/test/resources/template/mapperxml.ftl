<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperTargetPackage}.${entityName}Mapper">
    <sql id="baseColumn">
        <#list field as f>
            <#if f_index!=(field?size-1)>
                a.${f},
            <#else>
                a.${f}
            </#if>
        </#list>
    </sql>

    <resultMap id="baseResultMap" type="${entityPackage}.${entityName}">
        <id column="id" property="id"/>
        <#list resultMapList as list>
        <result property="${list.property}" column="${list.column}" jdbcType="${list.jdbcType}"/>
        </#list>
    </resultMap>

    <!-- #添加 -->
    <insert id="insert">
        <selectKey keyProperty="id" resultType="integer" order="AFTER">
            select last_insert_id()
        </selectKey>
        insert into ${tableName}(
        <#list field as f>
        <#if f=="id" || f=="update_time">
            <#continue>
        </#if>
        <#if f_index!=(field?size-1)>
         ${f},
        <#else>
         ${f}
        </#if>
        </#list>
        ) values(
        <#list field as f>
          <#if f=="id" || f=="update_time">
        <#continue>
          </#if>
          <#if f_index!=(field?size-1)>
         ${r'#{'}${f}${r'}'},
           <#else>
         ${r'#{'}${f}${r'}'}
        </#if>
        </#list>
        )
    </insert>

    <!-- 查询 -->
    <select id="selectBySearch"  resultMap="baseResultMap">
        select
        <include refid="baseColumn"/>
        from ${tableName} a
        ORDER BY a.id DESC
    </select>

    <!-- 修改 -->
    <update id="update">
        update
        ${tableName}
        set
        <#list resultMapList as list>
            <#if list.column=="id" || list.column=="create_time" || list.column=="update_time">
                <#continue>
            </#if>
            <if test="${list.column}!=null">
                ${list.column}=${r'#{'}${list.property}${r'}'},
            </if>
        </#list>
        update_time=${r'#{'}updateTime${r'}'}
        where id=${r'#{'}id${r'}'}
    </update>

</mapper>