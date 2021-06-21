package com.java.blog.controller;

import com.java.blog.service.EnumManageService;
import com.java.blog.vo.EnumVo;
import com.java.blog.vo.ReturnResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/blogManage/enum")
public class EnumController {
    @Autowired
    private EnumManageService enumManageService;

    /**
     * 新增枚举
     *
     * @param code
     * @param title
     * @return
     */
    @PostMapping("/add")
    public ReturnResultVo getEnumVoInsert(
            @RequestParam(value = "code", required = true) int code,
            @RequestParam(value = "title", required = true) String title
    ) {
        boolean flag = enumManageService.getEnumVoInsert(code, title);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/list")
    public ReturnResultVo getEnumVoList() {
        ModelMap modelMap = enumManageService.getEnumVoList();
        return new ReturnResultVo(200, "SUCCESS", modelMap);
    }

    /**
     * 修改枚举
     *
     * @param id
     * @param code
     * @param title
     * @return
     */
    @PostMapping("/update")
    public ReturnResultVo getEnumVoUpdate(
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "code", required = true) int code,
            @RequestParam(value = "title", required = true) String title
    ) {
        boolean flag = enumManageService.getEnumVoUpdate(id, code, title);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 删除枚举
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ReturnResultVo getEnumVoDelete(
            @RequestParam(value = "id", required = true) int id
    ) {
        boolean flag = enumManageService.getEnumVoDelete(id);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }
}
