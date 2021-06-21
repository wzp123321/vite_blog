package com.java.blog.dao.sqlProvider;

import com.java.blog.vo.DictionaryVo;
import org.apache.ibatis.jdbc.SQL;

public class DictionarySqlProvider {

    public String getDictionaryInsertSql(DictionaryVo dictionaryVo){
        SQL sql = new SQL();
        sql.INSERT_INTO("zpwan_blog_dictionary");

        if (dictionaryVo.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }


        if (dictionaryVo.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }


        if (dictionaryVo.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=VARCHAR}");
        }


        if (dictionaryVo.getParentTitle() != null) {
            sql.VALUES("parent_title", "#{parentTitle,jdbcType=VARCHAR}");
        }


        if (dictionaryVo.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String getDictionaryUpdate(DictionaryVo dictionaryVo){
        System.out.println(dictionaryVo);
        SQL sql = new SQL();
        sql.UPDATE("zpwan_blog_dictionary");

        if (dictionaryVo.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }

        if (dictionaryVo.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }

        if (dictionaryVo.getParentTitle() != null) {
            sql.SET("parent_title = #{parentTitle,jdbcType=VARCHAR}");
        }

        if (dictionaryVo.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=VARCHAR}");
        }

        if (dictionaryVo.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        System.out.println(sql);
        if (dictionaryVo.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id=#{id,jdbcType=INTEGER}");
        return sql.toString();
    }
}
