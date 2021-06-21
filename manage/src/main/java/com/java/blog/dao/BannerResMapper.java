package com.java.blog.dao;

import com.java.blog.vo.BannerVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface BannerResMapper {
    @Insert({
            "insert into zpwan_blog_banners",
            "(title,image_url,article_id) values(#{title,jdbcType=VARCHAR},#{imageUrl,jdbcType=VARCHAR},#{articleId,jdbcType=VARCHAR})"
    })
    int getBannerVoInsert(@Param("title") String title, @Param("imageUrl") String imageUrl, @Param("articleId") String articleId);

    @Update({
            "update zpwan_blog_banners",
            "set title= #{title,jdbcType=VARCHAR},image_url= #{imageUrl,jdbcType=VARCHAR},article_id= #{articleId,jdbcType=VARCHAR},status= #{status,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int getBannerVoUpdate(@Param("id") int id, @Param("title") String title, @Param("imageUrl") String imageUrl, @Param("articleId") String articleId, @Param("status") int status);

    @Select({
            "select id,image_url as imageUrl,title,article_id as articleId,state",
            "from zpwan_blog_banners where id= #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "image_url", property = "image_url", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "article_id", property = "article_id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "state", property = "state", jdbcType = JdbcType.INTEGER),
    })
    BannerVo getBannerVoById(@Param("id") int id);

    @Select({
            "<script>",
            "select",
            "id,image_url as imageUrl,title,article_id as articleId,state",
            "from zpwan_blog_banners",
            "where 1=1",
            "<if test = 'state != null'> and state = #{state}</if>",
            "limit #{skip},#{limit}",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "image_url", property = "image_url", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "article_id", property = "article_id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "state", property = "state", jdbcType = JdbcType.INTEGER),
    })
    List<BannerVo> getBannerVoList(@Param("skip") int skip, @Param("limit") int limit, @Param("state") Integer state);

    @Select({
            "<script>",
            "select count(*)",
            "from zpwan_blog_banners",
            "where 1=1",
            "<if test = 'state != null'> and state = #{state}</if>",
            "</script>"
    })
    int getBannerVoTotal(@Param("state") Integer state);
}
