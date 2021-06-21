package com.java.blog.controller;

import com.java.blog.service.ArticleService;
import com.java.blog.vo.ArticleVo;
import com.java.blog.vo.ReturnResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/blogManage/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 新增文章
     *
     * @param title
     * @param content
     * @param md
     * @param tags
     * @param firstCatalogId
     * @param firstCatalogName
     * @param secondCatalogId
     * @param secondCatalogName
     * @param description
     * @return
     */
    @PostMapping(value = "/add")
    public ReturnResultVo getArticleInsert(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "md", required = true) String md,
            @RequestParam(value = "tags", required = true) String tags,
            @RequestParam(value = "imgUrl", required = true) String imgUrl,
            @RequestParam(value = "firstCatalogId", required = true) Integer firstCatalogId,
            @RequestParam(value = "firstCatalogName", required = true) String firstCatalogName,
            @RequestParam(value = "secondCatalogId", required = true) Integer secondCatalogId,
            @RequestParam(value = "secondCatalogName", required = true) String secondCatalogName,
            @RequestParam(value = "description", required = false) String description

    ) {
        boolean flag = articleService.getArticleInsert(title, content, md, description, imgUrl, tags, firstCatalogId, firstCatalogName, secondCatalogId, secondCatalogName);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 编辑文章
     *
     * @param id
     * @param title
     * @param content
     * @param md
     * @param tags
     * @param imgUrl
     * @param firstCatalogId
     * @param firstCatalogName
     * @param secondCatalogId
     * @param secondCatalogName
     * @param description
     * @return
     */
    @PostMapping(value = "/update")
    public ReturnResultVo getArticleUpdate(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "md", required = true) String md,
            @RequestParam(value = "tags", required = true) String tags,
            @RequestParam(value = "imgUrl", required = true) String imgUrl,
            @RequestParam(value = "firstCatalogId", required = true) Integer firstCatalogId,
            @RequestParam(value = "firstCatalogName", required = true) String firstCatalogName,
            @RequestParam(value = "secondCatalogId", required = true) Integer secondCatalogId,
            @RequestParam(value = "secondCatalogName", required = true) String secondCatalogName,
            @RequestParam(value = "description", required = false) String description

    ) {
        boolean flag = articleService.getArticleUpdateById(id, title, content, md, description, imgUrl, tags, firstCatalogId, firstCatalogName, secondCatalogId, secondCatalogName);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 查询文章列表
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
    @GetMapping(value = "/list")
    public ReturnResultVo getArticleList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "firstCatalogId", required = false) Integer firstCatalogId,
            @RequestParam(value = "secondCatalogId", required = false) Integer secondCatalogId,
            @RequestParam(value = "status", required = false) Integer status
    ) {
        ModelMap modelMap = articleService.getArticleList(page, limit, keyword, tags, firstCatalogId, secondCatalogId, status);
        return new ReturnResultVo(200, "SUCCESS", modelMap);
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    @ApiOperation("获取文章详情")
    @GetMapping("/getById")
    public ReturnResultVo getArticleById(
            @RequestParam(value = "id", required = true) String id
    ) {
        ArticleVo articleVo = articleService.getArticleInfoById(id);
        return new ReturnResultVo(200, "SUCCESS", articleVo);
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    @ApiOperation("修改文章状态")
    @GetMapping("/updateStatus")
    public ReturnResultVo getArticleUpdateStatus(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "status", required = true) Integer status
    ) {
        boolean flag = articleService.getArticleStatusUpdate(id, status);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }
}
