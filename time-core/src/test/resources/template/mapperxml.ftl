<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperTargetPackage}.${entityName}Mapper">
    <sql id="baseColumn">
        a.id,
        a.name,
        a.code,
        a.icon,
        a.resource_type,
        a.level,
        a.url,
        a.parent_id,
        a.priority,
        a.path
    </sql>

    <resultMap id="baseResultMap" type="${entityPackage}.${entityName}">
        <id column="id" property="id"/>
        <result property="name" column="name" jdbcType="VARCHAR"/>
        <result property="code" column="code" jdbcType="VARCHAR"/>
        <result property="icon" column="icon" jdbcType="VARCHAR"/>
        <result property="resourceType" column="resource_type" jdbcType="VARCHAR"/>
        <result property="level" column="level" jdbcType="INTEGER"/>
        <result property="url" column="url" jdbcType="VARCHAR"/>
        <result property="createTime" column="create_time" jdbcType="INTEGER"/>
        <result property="priority" column="priority" jdbcType="INTEGER"/>
        <result property="path" column="path" jdbcType="VARCHAR"/>
    </resultMap>
</mapper>