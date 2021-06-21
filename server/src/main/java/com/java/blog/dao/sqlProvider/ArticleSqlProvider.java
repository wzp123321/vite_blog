package com.java.blog.dao.sqlProvider;

import com.java.blog.vo.ArticleVo;
import org.apache.ibatis.jdbc.SQL;

public class ArticleSqlProvider {

    public String getArticleUpdateSql(ArticleVo articleVo) {
        SQL sql = new SQL();
        sql.UPDATE("zpwan_blog_article");

        if (articleVo.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }

        if (articleVo.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }

        if (articleVo.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }

        if (articleVo.getMd() != null) {
            sql.SET("md = #{md,jdbcType=VARCHAR}");
        }

        if (articleVo.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }

        if (articleVo.getTags() != null) {
            sql.SET("tags = #{tags,jdbcType=VARCHAR}");
        }

        if (articleVo.getFirstCatalogId() != null) {
            sql.SET("first_catalog_id = #{firstCatalogId,jdbcType=INTEGER}");
        }

        if (articleVo.getFirstCatalogName() != null) {
            sql.SET("first_catalog_name = #{firstCatalogName,jdbcType=VARCHAR}");
        }

        if (articleVo.getSecondCatalogId() != null) {
            sql.SET("second_catalog_id = #{secondCatalogId,jdbcType=INTEGER}");
        }

        if (articleVo.getSecondCatalogName() != null) {
            sql.SET("second_catalog_name = #{secondCatalogName,jdbcType=VARCHAR}");
        }

        if (articleVo.getImgUrl() != null) {
            sql.SET("imgUrl = #{imgUrl,jdbcType=VARCHAR}");
        }

        if (articleVo.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=DATE}");
        }

        sql.WHERE("id=#{id,jdbcType=VARCHAR}");

        return sql.toString();
    }
}
