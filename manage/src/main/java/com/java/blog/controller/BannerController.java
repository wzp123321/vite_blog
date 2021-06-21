package com.java.blog.controller;

import com.java.blog.service.BannerManageService;
import com.java.blog.vo.BannerVo;
import com.java.blog.vo.ReturnResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/blogManage/banner")
public class BannerController {

    @Autowired
    private BannerManageService bannerManageService;

    /**
     * 编辑
     *
     * @param title
     * @param imageUrl
     * @param articleId
     * @return
     */
    @PostMapping("/add")
    public ReturnResultVo getBannerVoInsert(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "imageUrl", required = true) String imageUrl,
            @RequestParam(value = "articleId", required = true) String articleId
    ) {
        boolean flag = bannerManageService.getBannerVoInsert(title, imageUrl, articleId);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 更新
     *
     * @param id
     * @param title
     * @param imageUrl
     * @param articleId
     * @param state
     * @return
     */
    @PostMapping("/update")
    public ReturnResultVo getBannerVoIn(
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "imageUrl", required = true) String imageUrl,
            @RequestParam(value = "articleId", required = true) String articleId,
            @RequestParam(value = "state", required = true) int state
    ) {
        boolean flag = bannerManageService.getBannerVoUpdate(id, title, imageUrl, articleId, state);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 列表
     *
     * @param page
     * @param limit
     * @param state
     * @return
     */
    @GetMapping("list")
    public ReturnResultVo getBannerList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "state", required = false) Integer state
    ) {
        ModelMap modelMap = bannerManageService.getBannerVoList(page, limit, state);
        return new ReturnResultVo(200, "SUCCESS", modelMap);
    }

    /**
     * 詳情
     * @param id
     * @return
     */
    @GetMapping("getById")
    public ReturnResultVo getBannerVoByIdd(
            @RequestParam(value = "id", required = true) int id
    ) {
        BannerVo bannerVo = bannerManageService.getBannerVoInfoById(id);
        return new ReturnResultVo(200, "SUCCESS", bannerVo);
    }
}
