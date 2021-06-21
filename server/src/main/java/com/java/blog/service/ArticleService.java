package com.java.blog.service;

import com.java.blog.common.exception.CommonOperationException;
import com.java.blog.dao.ArticleResMapper;
import com.java.blog.vo.ArticleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleResMapper articleResMapper;

    private Logger logger = LoggerFactory.getLogger(ArticleService.class);

    /**
     * 新增文章
     *
     * @param title
     * @param content
     * @param md
     * @param description
     * @param tags
     * @param firstCatalogId
     * @param firstCatalogName
     * @param secondCatalogId
     * @param secondCatalogName
     * @return
     */
    public boolean getArticleInsert(String title, String content, String md, String description, String imgUrl, String tags,
                                    Integer firstCatalogId, String firstCatalogName, Integer secondCatalogId, String secondCatalogName) {
        String id = UUID.randomUUID().toString();
        logger.info("新增文章" + id, title, content, md, description, imgUrl, firstCatalogId, firstCatalogName, secondCatalogId, secondCatalogName, tags);
        return articleResMapper.getArticleVoInsert(id, title, content, md, description, imgUrl, firstCatalogId, firstCatalogName, secondCatalogId, secondCatalogName, tags) > 0;
    }


    /**
     * 编辑文章
     *
     * @param id
     * @param title
     * @param content
     * @param md
     * @param description
     * @param imgUrl
     * @param tags
     * @param firstCatalogId
     * @param firstCatalogName
     * @param secondCatalogId
     * @param secondCatalogName
     * @return
     */
    public boolean getArticleUpdateById(String id,
                                        String title, String content, String md, String description, String imgUrl, String tags,
                                        Integer firstCatalogId, String firstCatalogName, Integer secondCatalogId, String secondCatalogName
    ) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(id);
        articleVo.setTitle(title);
        articleVo.setContent(content);
        articleVo.setMd(md);
        articleVo.setDescription(description);
        articleVo.setImgUrl(imgUrl);
        articleVo.setTags(tags);
        articleVo.setFirstCatalogId(firstCatalogId);
        articleVo.setFirstCatalogName(firstCatalogName);
        articleVo.setSecondCatalogId(secondCatalogId);
        articleVo.setFirstCatalogName(secondCatalogName);
        articleVo.setModifyTime(new Date());

        logger.info("编辑文章" + articleVo);
        return articleResMapper.getArticleUpdateById(articleVo) > 0;
    }

    /**
     * 获取文章列表
     *
     * @param page
     * @param limit
     * @param keyword
     * @param tags
     * @param firstCatalogId
     * @param secondCatalogId
     * @param status
     * @return
     */
    public ModelMap getArticleList(Integer page, Integer limit, String keyword, String tags, Integer firstCatalogId, Integer secondCatalogId, Integer status) {
        ModelMap modelMap = new ModelMap();
        int total = articleResMapper.getArticleCount(keyword, tags, firstCatalogId, secondCatalogId, status);
//        int total = 0;
        int skip = page > 1 ? (page - 1) * limit : 0;
        List<ArticleVo> articleVos = articleResMapper.getArticleVoList(skip, limit, keyword, tags, firstCatalogId, secondCatalogId, status);
        modelMap.put("list", articleVos);
        modelMap.put("total", total);

        logger.info("查询文章列表" + skip, limit, keyword, tags, firstCatalogId, secondCatalogId, status);
        return modelMap;
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    public ArticleVo getArticleInfoById(String id) {
        ArticleVo articleVo = articleResMapper.getArticleInfoById(id);
        if (articleVo == null) {
            throw new CommonOperationException("文章id不存在！");
        }

        logger.info("查询文章列表" + articleVo);
        return articleVo;
    }

    /**
     * 更新文章状态
     * @param id
     * @param status
     * @return
     */
    public boolean getArticleStatusUpdate(String id, Integer status) {
        ArticleVo articleVo = articleResMapper.getArticleInfoById(id);
        if (articleVo == null) {
            throw new CommonOperationException("文章id不存在！");
        }

        return articleResMapper.getArticleStatusUpdate(id, status) > 0;
    }
}
