package com.java.blog.dao;

import com.java.blog.vo.FileVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FileResMapper {

    @Insert({
            "insert into zpwan_blog_file",
            "(id,file_name,md5,url,length,extension)",
            "values(#{id,jdbcType = VARCHAR},#{fileName,jdbcType = VARCHAR},#{md5,jdbcType = VARCHAR},#{url,jdbcType = VARCHAR},#{length,jdbcType = INTEGER},#{extension,jdbcType = VARCHAR})"
    })
    int getFileVoInsert(@Param("id") String id, @Param("fileName") String fileName, @Param("md5") String md5,
                        @Param("url") String url, @Param("length") Long length, @Param("extension") String extension);

    @Select({
            "select id, file_name as fileName,md5,url,length,extension, create_time as createTime",
            "from zpwan_blog_file",
            "order by create_time desc  limit #{skip},#{limit}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "file_name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "md5", property = "md5", jdbcType = JdbcType.VARCHAR),
            @Result(column = "md5", property = "md5", jdbcType = JdbcType.VARCHAR),
            @Result(column = "length", property = "length", jdbcType = JdbcType.BIGINT),
            @Result(column = "extension", property = "extension", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "create_time", jdbcType = JdbcType.DATE)
    })
    List<FileVo> getFileVoList(@Param(value = "skip") Integer skip, @Param(value = "limit") Integer limit);

    @Select({
            "select count(*) from zpwan_blog_file"
    })
    int getFileVoTotal();
}
