package com.java.blog.controller;

import com.java.blog.service.DictionaryService;
import com.java.blog.vo.ReturnResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/blogManage/dic")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 新增
     *
     * @param type
     * @param title
     * @param parentId
     * @param parentTitle
     * @param description
     * @return
     */
    @PostMapping("/add")
    public ReturnResultVo getDictionaryAdd(
            @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "parentId", required = false) Integer parentId,
            @RequestParam(value = "parentTitle", required = false) String parentTitle,
            @RequestParam(value = "description", required = false) String description
    ) {
        boolean flag = dictionaryService.getDictionaryInsert(type, title, description, parentId, parentTitle);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ReturnResultVo getDictionaryDelete(
            @RequestParam(value = "id", required = true) Integer id
    ) {
        boolean flag = dictionaryService.getDictionaryVoDelete(id);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 更新
     *
     * @param id
     * @param type
     * @param title
     * @param parentId
     * @param parentTitle
     * @param description
     * @return
     */
    @PostMapping("/update")
    public ReturnResultVo getDictionaryUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "parentId", required = false) Integer parentId,
            @RequestParam(value = "parentTitle", required = false) String parentTitle,
            @RequestParam(value = "description", required = false) String description
    ) {
        boolean flag = dictionaryService.getDictionaryUpdate(id, type, title, description, parentId, parentTitle);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 列表
     *
     * @param type
     * @param parentId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    public ReturnResultVo getDictionaryList(
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "parentId", required = false) Integer parentId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {
        ModelMap modelMap = dictionaryService.getDictionaryList(type, parentId, page, limit);
        return new ReturnResultVo(200, "SUCCESS", modelMap);
    }
}
