package com.java.blog.dao;

import com.java.blog.vo.AdminVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface AdminResMapper {
    @Select({
            "select",
            "id,name, salt, password",
            "from zpwan_blog_admin",
            "where name = #{name,jdbcType = VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR)
    })
    AdminVo getAdminVoInfoVByName(@Param(value = "name") String name);

    @Select({
            "select",
            "name, salt, password",
            "from zpwan_blog_admin",
            "where id = #{id,jdbcType = INTEGER}"
    })
    @Results({
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR)
    })
    AdminVo getAdminVoInfoById(@Param(value = "id") Integer id);

    @Insert({
            "insert into zpwan_blog_admin",
            "(name,salt,password)",
            "values(#{name},#{salt},#{password})"
    })
    int getAdminBoInsert(@Param(value = "name") String name, @Param(value = "salt") String salt, @Param(value = "password") String password);

    @Select({
            "select id, name, create_time as createTime, modify_time as modifyTime",
            "from zpwan_blog_admin",
            "order by create_time desc  limit #{skip},#{limit}"
    })
    @Results({
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "create_time", jdbcType = JdbcType.DATE),
            @Result(column = "modify_time", property = "modify_time", jdbcType = JdbcType.DATE)
    })
    List<AdminVo> getAdminVoList(@Param(value = "skip") Integer skip, @Param(value = "limit") Integer limit);

    @Delete({
            "delete from zpwan_blog_admin",
            "where name = #{name}"
    })
    int getAdminVoDelete(@Param("name") String name);

    @Update({
            "update zpwan_blog_admin",
            "set name = #{name} , password = #{password},modify_time = #{modifyTime}",
            "where id = #{id}"
    })
    int getAdminVoUpdate(@Param("id") Integer id, @Param("name") String name, @Param("password") String password, @Param("modifyTime") Date modifyTime);

    @Select({
            "select count(*) from zpwan_blog_admin"
    })
    int getAdminVoTotal();
}
