package com.java.blog.dao;

import com.java.blog.dao.sqlProvider.ArticleSqlProvider;
import com.java.blog.vo.ArticleVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ArticleResMapper {

    @Insert({
            "insert into zpwan_blog_article",
            "(id,title,content,md,description,imgUrl,first_catalog_id,first_catalog_name,second_catalog_id,second_catalog_name,tags)",
            "values(#{id,jdbcType=VARCHAR},#{title,jdbcType=VARCHAR},#{content,jdbcType=VARCHAR},#{md,jdbcType=VARCHAR},#{description,jdbcType=VARCHAR},#{imgUrl,jdbcType=VARCHAR},#{firstCatalogId,jdbcType=INTEGER},",
            "#{firstCatalogName,jdbcType=VARCHAR},#{secondCatalogId,jdbcType=INTEGER},#{secondCatalogName,jdbcType=VARCHAR},#{tags,jdbcType=VARCHAR})"
    })
    int getArticleVoInsert(@Param("id") String id, @Param("title") String title, @Param("content") String content, @Param("md") String md, @Param("description") String description, @Param("imgUrl") String imgUrl,
                           @Param("firstCatalogId") Integer firstCatalogId, @Param("firstCatalogName") String firstCatalogName, @Param("secondCatalogId") Integer secondCatalogId,
                           @Param("secondCatalogName") String secondCatalogName, @Param("tags") String tags);


    @Select({
            "<script>",
            "select",
            "id,title,content,md,description,imgUrl ,tags,first_catalog_id as firstCatalogId,first_catalog_name as firstCatalogName,",
            "second_catalog_id as secondCatalogId,second_catalog_name as secondCatalogName,status, create_time as createTime,modify_time as modifyTime",
            "from zpwan_blog_article",
            "where 1=1",
            "<if test = 'keyword != null and keyword != \"\"'> and ( title like concat('%',#{keyword, jdbcType=VARCHAR},'%') or content like concat('%',#{keyword, jdbcType=VARCHAR},'%')or description like concat('%',#{keyword, jdbcType=VARCHAR},'%'))</if>",
            "<if test = 'tags != null and tags != \"\"'> and tags like concat('%',#{tags, jdbcType=VARCHAR},'%')</if>",
            "<if test = 'firstCatalogId != null'> and first_catalog_id = #{firstCatalogId}</if>",
            "<if test = 'secondCatalogId != null'> and second_catalog_id = #{secondCatalogId}</if>",
            "<if test = 'status != null'> and status = #{status}</if>",
            "limit #{skip},#{limit}",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "md", property = "md", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tags", property = "tags", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "imgUrl", property = "imgUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "first_catalog_id", property = "first_catalog_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "first_catalog_name", property = "first_catalog_name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "second_catalog_id", property = "second_catalog_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "second_catalog_name", property = "second_catalog_name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "create_time", jdbcType = JdbcType.DATE),
            @Result(column = "modify_time", property = "modify_time", jdbcType = JdbcType.DATE),
    })
    List<ArticleVo> getArticleVoList(@Param("skip") Integer skip, @Param("limit") Integer limit, @Param("keyword") String keyword,
                                     @Param("tags") String tags, @Param("firstCatalogId") Integer firstCatalogId,
                                     @Param("secondCatalogId") Integer secondCatalogId, @Param("status") Integer status);

    @Select({
            "<script>",
            "select count(id)",
            "from zpwan_blog_article",
            "where 1=1",
            "<if test = 'keyword != null and keyword != \"\"'> and ( title like concat('%',#{keyword, jdbcType=VARCHAR},'%') or content like concat('%',#{keyword, jdbcType=VARCHAR},'%')or description like concat('%',#{keyword, jdbcType=VARCHAR},'%'))</if>",
            "<if test = 'tags != null and tags != \"\"'> and tags like concat('%',#{tags, jdbcType=VARCHAR},'%')</if>",
            "<if test = 'firstCatalogId != null'> and first_catalog_id = #{firstCatalogId}</if>",
            "<if test = 'secondCatalogId != null'> and second_catalog_id = #{secondCatalogId}</if>",
            "<if test = 'status != null'> and status = #{status}</if>",
            "</script>"
    })
    int getArticleCount(@Param("keyword") String keyword, @Param("tags") String tags, @Param("firstCatalogId") Integer firstCatalogId,
                        @Param("secondCatalogId") Integer secondCatalogId, @Param("status") Integer status);


    @Select({
            "select",
            "id,title,content,md,description,imgUrl ,tags,first_catalog_id as firstCatalogId,first_catalog_name as firstCatalogName,",
            "second_catalog_id as secondCatalogId,second_catalog_name as secondCatalogName,status, create_time as createTime,modify_time as modifyTime",
            "from zpwan_blog_article",
            "where id = #{id,jdbcType=VARCHAR}",
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "md", property = "md", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tags", property = "tags", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "imgUrl", property = "imgUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "first_catalog_id", property = "first_catalog_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "first_catalog_name", property = "first_catalog_name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "second_catalog_id", property = "second_catalog_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "second_catalog_name", property = "second_catalog_name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "create_time", jdbcType = JdbcType.DATE),
            @Result(column = "modify_time", property = "modify_time", jdbcType = JdbcType.DATE),
    })
    ArticleVo getArticleInfoById(@Param("id") String id);

    @UpdateProvider(type = ArticleSqlProvider.class, method = "getArticleUpdateSql")
    int getArticleUpdateById(ArticleVo articleVo);

    @Update("update zpwan_blog_article set status = #{status,jdbcType=INTEGER} where id = #{id,jdbcType=VARCHAR}")
    int getArticleStatusUpdate(@Param("id") String id, @Param("status") Integer status);
}
