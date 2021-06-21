package com.java.blog.dao;

import com.java.blog.vo.EnumVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface EnumResMapper {

    @Insert({
            "insert into zpwan_blog_enum",
            "(code,title) values(#{code,jdbcType = INTEGER},#{title,jdbcType= VARCHAR})"
    })
    int getEnumVoInsert(@Param("code") int code, @Param("title") String value);

    @Delete("delete from zpwan_blog_enum where id = #{id}")
    int getEnumVoDelete(@Param("id") int id);

    @Update({
            "update zpwan_blog_enum",
            "set code = #{code,jdbcType=INTEGER}, title= #{title,jdbcType=VARCHAR},modify_time = #{modifyTime,jdbcType=DATE}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int getEnumVoUpdate(@Param("id") int id, @Param("code") int code, @Param("title") String title, @Param("modifyTime") Date modifyTime);

    @Select({
            "select id,code,title,create_time as createTime,modify_time as modifyTime",
            "from zpwan_blog_enum"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "code", property = "code", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "create_time", jdbcType = JdbcType.DATE),
            @Result(column = "modify_time", property = "modify_time", jdbcType = JdbcType.DATE)
    })
    List<EnumVo> getEnumVoList();

    @Select("select id,code,title from zpwan_blog_enum where code = #{code,jdbcType=INTEGER}")
    EnumVo getEnumVoByCode(@Param("code") int code);
}
