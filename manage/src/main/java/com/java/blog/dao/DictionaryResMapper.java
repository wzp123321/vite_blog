package com.java.blog.dao;

import com.java.blog.dao.sqlProvider.DictionarySqlProvider;
import com.java.blog.vo.DictionaryVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface DictionaryResMapper {
    @InsertProvider(type=DictionarySqlProvider.class,method="getDictionaryInsertSql")
    int getDictionaryInsert(DictionaryVo dictionaryVo);

    @UpdateProvider(type=DictionarySqlProvider.class,method = "getDictionaryUpdate")
    int getDictionaryVoUpdate(DictionaryVo dictionaryVo);


    @Select({
            "select id,type,title,parent_id as parentId,parent_title as parentTitle,",
            "description,create_time as createTime,modify_time as modifyTime",
            "from zpwan_blog_dictionary where id= #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "parent_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "parent_title", property = "parent_title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "create_time", jdbcType = JdbcType.DATE),
            @Result(column = "modify_time", property = "modify_time", jdbcType = JdbcType.DATE)
    })
    DictionaryVo getDictionaryViInfoById(@Param("id") int id);

    @Delete("delete from zpwan_blog_dictionary where id = #{id,jdbcType=INTEGER}")
    int getDictionaryDeleteById(@Param("id") Integer id);

    @Select({
            "<script>",
            "select",
            "id,type ,title,parent_id as parentId,parent_title as parentTitle,description",
            "from zpwan_blog_dictionary",
            "where 1=1",
            "<if test = 'type != null'> and type = #{type}</if>",
            "limit #{skip},#{limit}",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "parent_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "parent_title", property = "parent_title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
    })
    List<DictionaryVo> getDictionaryList(@Param("type") Integer type, @Param("skip")int skip, @Param("limit")int limit);

    @Select({
            "<script>",
            "select count(id)",
            "from zpwan_blog_dictionary",
            "where 1=1",
            "<if test = 'type != null'> and type = #{type}</if>",
            "</script>"
    })
    int getDictionaryVoCount(@Param("type") Integer type);
}
